package com.dpilaloa.api.clients.movements.service;

import com.dpilaloa.api.clients.movements.service.models.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ClientService {

    Mono<Void> postClient(Client client);
    Flux<Client> getClients();
    Mono<Client> getClientById(UUID clientId);
    Mono<Client> putClient(UUID clientId, Client client);
    Mono<Void> deleteClient(UUID clientId);

}
