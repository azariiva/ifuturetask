package ru.spbifuture.account.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RequestMapping("/api/account")
public interface AccountAPI {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Long getAmount(@PathVariable("id") @NotNull @Min(1) Integer id);

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addAmount(@PathVariable("id") @NotNull @Min(1) Integer id,
                   @RequestParam("value") @NotNull @Min(1)  Long value);
}
