package ru.spbifuture.client.businesslogic;

import java.util.concurrent.CompletableFuture;

public interface AccountService {

    CompletableFuture<Long> getAmount(Integer id);

    CompletableFuture<Void> addAmount(Integer id, Long value);
}
