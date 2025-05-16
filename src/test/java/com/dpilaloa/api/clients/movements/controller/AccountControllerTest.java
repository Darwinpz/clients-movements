package com.dpilaloa.api.clients.movements.controller;

import com.dpilaloa.api.clients.movements.util.MockData;
import com.dpilaloa.api.clients.movements.service.impl.AccountServiceImpl;
import com.dpilaloa.api.clients.movements.service.models.Account;
import com.dpilaloa.api.clients.movements.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = {AccountControllerTest.class})
class AccountControllerTest {

    @Mock
    AccountServiceImpl accountService;

    @InjectMocks
    AccountController accountController;

    private Account account;

    @BeforeEach
    void setUp() {
        account = MockData.createAccount();
    }

    @Test
    public void testPostAccountSuccess(){

        when(accountService.postAccount(any(Account.class))).thenReturn(Mono.just(account).then());
        StepVerifier.create(accountController.postAccount(account, any()))
                .expectSubscription()
                .expectNext(ResponseEntity.ok().build())
                .expectComplete();
        verify(accountService, times(1)).postAccount(account);

    }

    @Test
    public void testPostAccountError() {

        when(accountService.postAccount(any(Account.class))).thenReturn(Mono.error(new ServerWebInputException(Constants.INVALID_DATA)));
        StepVerifier.create(accountController.postAccount(account,any()))
                .expectErrorMatches(throwable -> throwable instanceof ServerWebInputException)
                .verify();

    }

    @Test
    public void testGetAccountByNumberSuccess() {

        when(accountService.getAccountByNumber(account.getNumber())).thenReturn(Mono.just(account));
        StepVerifier.create(accountController.getAccountByNumber(account.getNumber(), any()))
                .expectNextMatches(customerDtoResponseEntity -> {
                    assertEquals(customerDtoResponseEntity.getBody(),account);
                    return true;
                })
                .verifyComplete();

    }


}