package com.dpilaloa.api.clients.movements.service.impl;

import com.dpilaloa.api.clients.movements.domain.models.ClientEntity;
import com.dpilaloa.api.clients.movements.repository.ClientRepository;
import com.dpilaloa.api.clients.movements.service.ClientService;
import com.dpilaloa.api.clients.movements.service.mapper.ClientMapper;
import com.dpilaloa.api.clients.movements.service.models.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Mono<Void> postClient(Client client) {
        ClientEntity clientEntity = ClientMapper.INSTANCE.clientToClientEntity(client);
        return clientRepository.save(clientEntity).then();
    }

    @Override
    public Flux<Client> getClients() {
        return clientRepository.findAll()
                .map(ClientMapper.INSTANCE::clientEntityToClient);
    }

    @Override
    public Mono<Client> getClientById(UUID clientId) {
        return clientRepository.findById(clientId)
                .map(ClientMapper.INSTANCE::clientEntityToClient)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Client> putClient(UUID clientId, Client client) {
        return clientRepository.findById(clientId)
                .flatMap(exist -> {
                    exist.setName(client.getName());
                    exist.setAge(client.getAge());
                    exist.setGender(client.getGender());
                    exist.setPassword(client.getPassword());
                    exist.setState(client.getState());
                    exist.setAddress(client.getAddress());
                    exist.setPhone(client.getPhone());
                    return clientRepository.save(exist);
                })
                .map(ClientMapper.INSTANCE::clientEntityToClient)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Void> deleteClient(UUID clientId) {
        return clientRepository.findById(clientId)
                .flatMap(exist -> clientRepository.deleteById(clientId).thenReturn(exist))
                .switchIfEmpty(Mono.empty())
                .then();
    }
}
