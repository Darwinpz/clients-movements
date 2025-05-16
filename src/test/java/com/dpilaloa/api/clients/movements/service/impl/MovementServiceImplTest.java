package com.dpilaloa.api.clients.movements.service.impl;

import com.dpilaloa.api.clients.movements.domain.models.MovementEntity;
import com.dpilaloa.api.clients.movements.repository.MovementRepository;
import com.dpilaloa.api.clients.movements.service.AccountService;
import com.dpilaloa.api.clients.movements.service.mapper.MovementMapper;
import com.dpilaloa.api.clients.movements.service.models.Account;
import com.dpilaloa.api.clients.movements.service.models.Movement;
import com.dpilaloa.api.clients.movements.util.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ContextConfiguration(classes = {MovementServiceImpl.class})
class MovementServiceImplTest {

    @MockitoBean
    MovementRepository movementRepository;

    @MockitoBean
    AccountService accountService;

    @Autowired
    MovementServiceImpl movementService;

    private MovementEntity movementEntity;
    private Movement movement;
    private Account account;

    @BeforeEach
    void setUp() {

        movement = MockData.createMovement();
        movementEntity = MovementMapper.INSTANCE.movementToMovementEntity(movement);
        account = MockData.createAccount();
    }


    @Test
    public void testPostMovementServiceSuccess() {

        when(accountService.getAccountByNumber(movement.getAccount())).thenReturn(Mono.just(account));
        when(accountService.updateAccountBalance(any(), any(BigDecimal.class))).thenReturn(Mono.empty());
        when(movementRepository.save(any(MovementEntity.class))).thenReturn(Mono.empty());

        StepVerifier.create(movementService.postMovement(movement))
                .verifyComplete();

    }

    @Test
    public void testGetMovementServiceByIdFound() {

        when(movementRepository.findById(movement.getId())).thenReturn(Mono.just(movementEntity));

        StepVerifier.create(movementService.getMovementById(movement.getId()))
                .expectNextMatches(returnedMovement ->
                        returnedMovement.getAccount().equals(movementEntity.getAccount()) &&
                                returnedMovement.getId().equals(movementEntity.getId()))
                .verifyComplete();
    }

}