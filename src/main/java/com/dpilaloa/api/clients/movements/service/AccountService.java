package com.dpilaloa.api.clients.movements.service;

import com.dpilaloa.api.clients.movements.service.models.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;


public interface AccountService {

    Mono<Void> postAccount(Account account);
    Flux<Account> getAccounts();
    Mono<Account> getAccountByNumber(Integer number);
    Mono<Account> patchAccount(Integer number, Boolean state);
    Mono<Void> deleteAccount(Integer number);
    Mono<Void> updateAccountBalance(Integer number, BigDecimal balance);
    Flux<Account> getAccountsByClientId(UUID id);
}
