package com.dpilaloa.api.clients.movements.controller;

import com.dpilaloa.api.clients.movements.service.MovementService;
import com.dpilaloa.api.clients.movements.service.models.Movement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movements")
public class MovementController implements MovementsApi{

    private final MovementService movementService;

    @GetMapping("/{id}")
    @Override
    public Mono<ResponseEntity<Movement>> getMovementById(@PathVariable UUID id, ServerWebExchange exchange) {
        return movementService.getMovementById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Override
    public Mono<ResponseEntity<Flux<Movement>>> getMovements(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok().body(movementService.getMovements()));
    }

    @PostMapping
    @Override
    public Mono<ResponseEntity<Void>> postMovement(@RequestBody Movement movement, ServerWebExchange exchange) {
        return movementService.postMovement(movement)
                .map(ResponseEntity::ok);
    }
}
