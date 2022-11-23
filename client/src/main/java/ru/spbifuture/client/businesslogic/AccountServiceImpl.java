package ru.spbifuture.client.businesslogic;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class AccountServiceImpl implements AccountService {

    private final RestTemplate restTemplate;

    public AccountServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    @Override
    public CompletableFuture<Long> getAmount(Integer id) {
        return CompletableFuture.completedFuture(restTemplate.getForObject(String.format("/account/%d", id), Long.class));
    }

    @Async
    @Override
    public CompletableFuture<Void> addAmount(Integer id, Long value) {
        return CompletableFuture.completedFuture(restTemplate.postForObject(String.format("/account/%d", id), value, Void.class));
    }
}
