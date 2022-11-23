package ru.spbifuture.account.server.controller;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/micrometer")
public class MicrometerAPI {

    private final MeterRegistry registry;

    public MicrometerAPI(MeterRegistry registry) {
        this.registry = registry;
    }

    @GetMapping("/account_amount_get")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, Object> getAccountGetAmountMetrics() {
        var timer = registry.find("account.amount.get").timer();
        if (timer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var count = timer.count();
        var totalTime = timer.totalTime(TimeUnit.SECONDS);
        return Map.of(
                "count", count,
                "totalTime", totalTime,
                "rps", count / totalTime
        );
    }

    @GetMapping("/account_amount_add")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, Object> getAccountAddAmountMetrics() {
        var timer = registry.find("account.amount.add").timer();
        if (timer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var count = timer.count();
        var totalTime = timer.totalTime(TimeUnit.SECONDS);
        return Map.of(
                "count", count,
                "totalTime", totalTime,
                "rps", count / totalTime
        );
    }

    @DeleteMapping("/account_amount_get")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountGetAmountMetrics() {
        registry.getMeters().stream()
                .filter(m -> m.getId().getName().equals("account.amount.get"))
                .findFirst().ifPresent(registry::remove);
    }

    @DeleteMapping("/account_amount_add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountAddAmountMetrics() {
        registry.getMeters().stream()
                .filter(m -> m.getId().getName().equals("account.amount.add"))
                .findFirst().ifPresent(registry::remove);
    }
}
