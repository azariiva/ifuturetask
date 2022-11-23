package ru.spbifuture.account.server.controller;

import io.micrometer.core.annotation.Timed;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.spbifuture.account.server.businesslogic.AccountService;

import javax.validation.ConstraintViolationException;

@RestController
@Validated
public class AccountRestAPI implements AccountAPI {

    private final AccountService accountService;

    public AccountRestAPI(AccountService accountService) {
        this.accountService = accountService;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String constraintViolationExceptionHandler(ConstraintViolationException exception) {
        return exception.getMessage();
    }

    @Timed("account.amount.get")
    @Override
    public Long getAmount(Integer id) {
        return accountService.getAmount(id);
    }

    @Timed("account.amount.add")
    @Override
    public void addAmount(Integer id, Long value) {
        accountService.addAmount(id, value);
    }
}
