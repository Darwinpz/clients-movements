package com.dpilaloa.api.clients.movements.service.impl;

import com.dpilaloa.api.clients.movements.domain.models.ClientEntity;
import com.dpilaloa.api.clients.movements.repository.ClientRepository;
import com.dpilaloa.api.clients.movements.service.mapper.ClientMapper;
import com.dpilaloa.api.clients.movements.service.models.Client;
import com.dpilaloa.api.clients.movements.util.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ContextConfiguration(classes = {ClientServiceImpl.class})
class ClientServiceImplTest {

    @MockitoBean
    ClientRepository clientRepository;

    @Autowired
    ClientServiceImpl clientService;

    private ClientEntity clientEntity;
    private Client client;

    @BeforeEach
    void setUp() {

        client = MockData.createClient();
        clientEntity = ClientMapper.INSTANCE.clientToClientEntity(client);
    }


    @Test
    public void testPostClientServiceSuccess() {

        when(clientRepository.save(any(ClientEntity.class))).thenReturn(Mono.just(clientEntity));
        StepVerifier.create(clientService.postClient(client))
                .expectNext()
                .verifyComplete();

    }

    @Test
    public void testGetClientServiceByIdFound() {

        when(clientRepository.findById(client.getId())).thenReturn(Mono.just(clientEntity));
        StepVerifier.create(clientService.getClientById(client.getId()))
                .expectNextMatches(client -> client.getIdentification().equals(clientEntity.getIdentification()))
                .verifyComplete();

    }

}