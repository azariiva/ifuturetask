package ru.spbifuture.account.server.controller;

import com.sun.istack.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/account")
public interface AccountAPI {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Long getAmount(@PathVariable("id") @NotNull Integer id);

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addAmount(@PathVariable("id") @NotNull Integer id,
                   @RequestParam("value") @NotNull Long value);
}
