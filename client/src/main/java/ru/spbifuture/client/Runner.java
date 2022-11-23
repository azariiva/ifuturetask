package ru.spbifuture.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.spbifuture.client.businesslogic.AccountService;
import ru.spbifuture.client.config.TestConfigurationProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

@Component
public class Runner implements CommandLineRunner {

    private final TestConfigurationProperties configurationProperties;
    private final AccountService accountService;
    private final Random random;

    public Runner(TestConfigurationProperties configurationProperties, AccountService accountService) {
        this.configurationProperties = configurationProperties;
        this.accountService = accountService;
        random = new Random();
    }

    @Override
    public void run(String... args) throws InterruptedException {
        List<Integer> readers = new ArrayList<>(configurationProperties.getIdList());
        var rcount = configurationProperties.getrCount();
        if (rcount < 0) {
            rcount = 0;
        } else if (rcount > readers.size()) {
            rcount = readers.size();
        }
        Collections.shuffle(readers, random);
        readers = readers.subList(0, rcount);

        List<Integer> writers = new ArrayList<>(configurationProperties.getIdList());
        var wcount = configurationProperties.getwCount();
        if (wcount < 0) {
            wcount = 0;
        } else if (wcount > writers.size()) {
            wcount = writers.size();
        }
        Collections.shuffle(writers, random);
        writers = writers.subList(0, wcount);

        var lock = new CountDownLatch(wcount + rcount);
        readers.forEach(id -> accountService.getAmount(id).whenComplete((receivedAmount, exception) -> {
            if (exception != null) {
                System.out.printf("Account(id=%d): %s\n", id, exception.getMessage());
            } else {
                System.out.printf("Account(id=%d): receive amount=%d\n", id, receivedAmount);
            }
            lock.countDown();
        }));
        writers.forEach(id -> {
            var increment = random.nextLong(-1, 10);
            accountService.addAmount(id, increment).whenComplete((__, exception) -> {
                if (exception != null) {
                    System.out.printf("Account(id=%d): %s\n", id, exception.getMessage());
                } else {
                    System.out.printf("Account(id=%d): increase amount value=%d\n", id, increment);
                }
                lock.countDown();
            });
        });
        lock.await();
    }
}
