package com.dpilaloa.api.clients.movements.controller;

import com.dpilaloa.api.clients.movements.util.MockData;
import com.dpilaloa.api.clients.movements.service.impl.ClientServiceImpl;
import com.dpilaloa.api.clients.movements.service.models.Client;
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

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {ClientControllerTest.class})
class ClientControllerTest {

    @Mock
    ClientServiceImpl clientService;

    @InjectMocks
    ClientController clientController;

    private Client client;

    @BeforeEach
    void setUp() {
        client = MockData.createClient();
    }

    @Test
    public void testPostClientSuccess(){

        when(clientService.postClient(any(Client.class))).thenReturn(Mono.just(client).then());
        StepVerifier.create(clientController.postClient(client, any()))
                .expectSubscription()
                .expectNext(ResponseEntity.ok().build())
                .expectComplete();
        verify(clientService, times(1)).postClient(client);

    }

    @Test
    public void testPostClientError() {

        when(clientService.postClient(any(Client.class))).thenReturn(Mono.error(new ServerWebInputException(Constants.INVALID_DATA)));
        StepVerifier.create(clientController.postClient(client,any()))
                .expectErrorMatches(throwable -> throwable instanceof ServerWebInputException)
                .verify();

    }

    @Test
    public void testGetClientByIdSuccess() {

        when(clientService.getClientById(client.getId())).thenReturn(Mono.just(client));
        StepVerifier.create(clientController.getClientById(client.getId(), any()))
                .expectNextMatches(customerDtoResponseEntity -> {
                    assertEquals(customerDtoResponseEntity.getBody(),client);
                    return true;
                })
                .verifyComplete();

    }

}