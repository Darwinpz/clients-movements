package com.dpilaloa.api.clients.movements.controller;

import com.dpilaloa.api.clients.movements.service.ClientService;
import com.dpilaloa.api.clients.movements.service.models.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController  implements ClientsApi{

    private final ClientService clientService;

    @DeleteMapping("/{id}")
    @Override
    public Mono<ResponseEntity<Void>> deleteClient(@PathVariable UUID id, ServerWebExchange exchange) {
        return clientService.deleteClient(id)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    @Override
    public Mono<ResponseEntity<Client>> getClientById(@PathVariable UUID id, ServerWebExchange exchange) {
         return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Override
    public Mono<ResponseEntity<Flux<Client>>> getClients(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok().body(clientService.getClients()));
    }

    @PostMapping
    @Override
    public Mono<ResponseEntity<Void>> postClient(@RequestBody Client client, ServerWebExchange exchange) {
        return clientService.postClient(client)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    @Override
    public Mono<ResponseEntity<Void>> putClient(@PathVariable UUID id, @RequestBody Client client, ServerWebExchange exchange) {
         return clientService.putClient(id,client)
                .flatMap(clientUpdated -> Mono.just(ResponseEntity.ok().build()));
    }

}
