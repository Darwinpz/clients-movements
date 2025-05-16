package com.dpilaloa.api.clients.movements.controller;

import com.dpilaloa.api.clients.movements.service.AccountService;
import com.dpilaloa.api.clients.movements.service.models.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController implements AccountsApi{

    private final AccountService accountService;

    @DeleteMapping("/{number}")
    @Override
    public Mono<ResponseEntity<Void>> deleteAccount(@PathVariable Integer number, ServerWebExchange exchange) {
        return accountService.deleteAccount(number)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{number}")
    @Override
    public Mono<ResponseEntity<Account>> getAccountByNumber(Integer number, ServerWebExchange exchange) {
        return accountService.getAccountByNumber(number)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Override
    public Mono<ResponseEntity<Flux<Account>>> getAccounts(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok().body(accountService.getAccounts()));
    }

    @PostMapping
    @Override
    public Mono<ResponseEntity<Void>> postAccount(@RequestBody Account account, ServerWebExchange exchange) {
        return accountService.postAccount(account)
                .map(ResponseEntity::ok);
    }

    @PatchMapping("/{number}")
    @Override
    public Mono<ResponseEntity<Void>> patchAccount(@PathVariable Integer number, @RequestBody Boolean state, ServerWebExchange exchange) {
        return accountService.patchAccount(number,state)
                .flatMap(clientUpdated -> Mono.just(ResponseEntity.ok().build()));
    }


}
