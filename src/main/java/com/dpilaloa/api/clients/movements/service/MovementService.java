package com.dpilaloa.api.clients.movements.service;

import com.dpilaloa.api.clients.movements.service.models.Movement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface MovementService {

    Mono<Void> postMovement(Movement movement);
    Flux<Movement> getMovements();
    Mono<Movement> getMovementById(UUID movementId);

}
