package ru.spbifuture.account.server.controller;

import com.sun.istack.NotNull;
import org.springframework.web.bind.annotation.RestController;
import ru.spbifuture.account.server.businesslogic.AccountService;

@RestController
public class AccountRestAPI implements AccountAPI {

    private final AccountService accountService;

    public AccountRestAPI(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Long getAmount(Integer id) {
        return accountService.getAmount(id);
    }

    @Override
    public void addAmount(@NotNull Integer id, @NotNull Long value) {
        accountService.addAmount(id, value);
    }
}
