package com.dpilaloa.api.clients.movements.controller;

import com.dpilaloa.api.clients.movements.util.MockData;
import com.dpilaloa.api.clients.movements.service.impl.MovementServiceImpl;
import com.dpilaloa.api.clients.movements.service.models.Movement;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = {MovementControllerTest.class})
class MovementControllerTest {

    @Mock
    MovementServiceImpl movementService;

    @InjectMocks
    MovementController movementController;

    private Movement movement;

    @BeforeEach
    void setUp() {
        movement = MockData.createMovement();
    }

    @Test
    public void testPostMovementSuccess(){

        when(movementService.postMovement(any(Movement.class))).thenReturn(Mono.just(movement).then());
        StepVerifier.create(movementController.postMovement(movement, any()))
                .expectSubscription()
                .expectNext(ResponseEntity.ok().build())
                .expectComplete();
        verify(movementService, times(1)).postMovement(movement);

    }

    @Test
    public void testPostMovementError() {

        when(movementService.postMovement(any(Movement.class))).thenReturn(Mono.error(new ServerWebInputException(Constants.INVALID_DATA)));
        StepVerifier.create(movementController.postMovement(movement,any()))
                .expectErrorMatches(throwable -> throwable instanceof ServerWebInputException)
                .verify();

    }

    @Test
    public void testGetMovementByIdSuccess() {

        when(movementService.getMovementById(movement.getId())).thenReturn(Mono.just(movement));
        StepVerifier.create(movementController.getMovementById(movement.getId(), any()))
                .expectNextMatches(customerDtoResponseEntity -> {
                    assertEquals(customerDtoResponseEntity.getBody(),movement);
                    return true;
                })
                .verifyComplete();

    }

}