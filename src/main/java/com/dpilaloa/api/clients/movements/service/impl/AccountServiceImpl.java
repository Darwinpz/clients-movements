package com.dpilaloa.api.clients.movements.service.impl;

import com.dpilaloa.api.clients.movements.domain.models.AccountEntity;
import com.dpilaloa.api.clients.movements.repository.AccountRepository;
import com.dpilaloa.api.clients.movements.service.AccountService;
import com.dpilaloa.api.clients.movements.service.mapper.AccountMapper;
import com.dpilaloa.api.clients.movements.service.models.Account;
import com.dpilaloa.api.clients.movements.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Mono<Void> postAccount(Account account) {

        return getAccountsByClientId(account.getClient())
                .collectList()
                .flatMap(accounts -> {
                    boolean hasSavingsAccount = accounts.stream()
                            .anyMatch(acc -> acc.getType().equalsIgnoreCase(Constants.SAVINGS));
                    boolean hasCurrentAccount = accounts.stream()
                            .anyMatch(acc -> acc.getType().equalsIgnoreCase(Constants.CHECKING));

                    if (account.getType().equalsIgnoreCase(Constants.SAVINGS)) {
                        if (hasSavingsAccount) {
                            return Mono.error(new ServerWebInputException(Constants.SAVINGS_ACCOUNT_EXISTS));
                        }
                    }

                    if (account.getType().equalsIgnoreCase(Constants.CHECKING)) {
                        if (hasCurrentAccount) {
                            return Mono.error(new ServerWebInputException(Constants.CHECKING_ACCOUNT_EXISTS));
                        }
                    }

                    if (hasSavingsAccount && hasCurrentAccount) {
                        return Mono.error(new ServerWebInputException(Constants.CANNOT_CREATE_MORE_ACCOUNTS));
                    }

                    AccountEntity accountEntity = AccountMapper.INSTANCE.accountToAccountEntity(account);
                    return accountRepository.save(accountEntity).then();
                });
    }

    @Override
    public Flux<Account> getAccounts() {
        return accountRepository.findAll()
                .map(AccountMapper.INSTANCE::accountEntityToAccount);
    }

    @Override
    public Mono<Account> getAccountByNumber(Integer number) {
        return  accountRepository.findById(number)
                .map(AccountMapper.INSTANCE::accountEntityToAccount)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Account> patchAccount(Integer number, Boolean state) {
        return  accountRepository.findById(number)
                .flatMap(exist -> {
                    exist.setState(state);
                    return accountRepository.save(exist);
                })
                .map(AccountMapper.INSTANCE::accountEntityToAccount)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Void> deleteAccount(Integer number) {
        return accountRepository.findById(number)
                .flatMap(exist -> accountRepository.deleteById(number).thenReturn(exist))
                .switchIfEmpty(Mono.empty())
                .then();
    }

    @Override
    public Mono<Void> updateAccountBalance(Integer number, BigDecimal balance) {
        return accountRepository.findById(number)
                .flatMap(exist -> {
                    exist.setBalance(balance);
                    return accountRepository.save(exist);
                }).then();
    }

    @Override
    public Flux<Account> getAccountsByClientId(UUID id) {
        return accountRepository.findAccountsByClientId(id)
                .map(AccountMapper.INSTANCE::accountEntityToAccount)
                .switchIfEmpty(Mono.empty());
    }
}
