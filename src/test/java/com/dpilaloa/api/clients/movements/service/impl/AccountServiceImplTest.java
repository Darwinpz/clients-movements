package com.dpilaloa.api.clients.movements.service.impl;

import com.dpilaloa.api.clients.movements.domain.models.AccountEntity;
import com.dpilaloa.api.clients.movements.repository.AccountRepository;
import com.dpilaloa.api.clients.movements.service.mapper.AccountMapper;
import com.dpilaloa.api.clients.movements.service.models.Account;
import com.dpilaloa.api.clients.movements.util.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ContextConfiguration(classes = {AccountServiceImpl.class})
class AccountServiceImplTest {

    @MockitoBean
    AccountRepository accountRepository;

    @Autowired
    AccountServiceImpl accountService;

    private AccountEntity accountEntity;
    private Account account;

    @BeforeEach
    void setUp() {

        account = MockData.createAccount();
        accountEntity = AccountMapper.INSTANCE.accountToAccountEntity(account);
    }


    @Test
    public void testPostAccountServiceSuccess() {

        when(accountRepository.findAccountsByClientId(any(UUID.class))).thenReturn(Flux.empty());
        when(accountRepository.save(any(AccountEntity.class))).thenReturn(Mono.just(accountEntity));
        StepVerifier.create(accountService.postAccount(account))
                .verifyComplete();

    }

    @Test
    public void testGetAccountServiceByNumberFound() {

        when(accountRepository.findById(account.getNumber())).thenReturn(Mono.just(accountEntity));
        StepVerifier.create(accountService.getAccountByNumber(account.getNumber()))
                .expectNextMatches(account -> account.getClient().equals(accountEntity.getClient()))
                .verifyComplete();

    }

}