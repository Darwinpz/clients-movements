package com.dpilaloa.api.clients.movements.service.impl;

import com.dpilaloa.api.clients.movements.repository.MovementRepository;
import com.dpilaloa.api.clients.movements.service.AccountService;
import com.dpilaloa.api.clients.movements.service.MovementService;
import com.dpilaloa.api.clients.movements.service.mapper.MovementMapper;
import com.dpilaloa.api.clients.movements.service.models.Movement;
import com.dpilaloa.api.clients.movements.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {

    private final MovementRepository movementRepository;
    private final AccountService accountService;

    @Override
    public Mono<Void> postMovement(Movement movement) {
        if (movement.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return Mono.error(new ServerWebInputException(Constants.MOVEMENT_AMOUNT_GREATER_THAN_ZERO));
        }

        return accountService.getAccountByNumber(movement.getAccount())
                .flatMap(account -> {
                    if (!List.of(Constants.DEBIT, Constants.CREDIT).contains(movement.getType().toUpperCase())) {
                        return Mono.error(new ServerWebInputException(Constants.INVALID_MOVEMENT_TYPE));
                    }

                    BigDecimal newBalance = movement.getType().equalsIgnoreCase(Constants.DEBIT)
                            ? account.getBalance().subtract(movement.getAmount())
                            : account.getBalance().add(movement.getAmount());

                    if (movement.getType().equalsIgnoreCase(Constants.DEBIT) &&
                            account.getBalance().compareTo(movement.getAmount()) < 0) {
                        return Mono.error(new ServerWebInputException(Constants.INSUFFICIENT_BALANCE));
                    }

                    movement.setBalance(account.getBalance());
                    return accountService.updateAccountBalance(movement.getAccount(), newBalance)
                            .then(movementRepository.save(MovementMapper.INSTANCE.movementToMovementEntity(movement)))
                            .then();
                });
    }

    @Override
    public Flux<Movement> getMovements() {
        return movementRepository.findAll()
                .map(MovementMapper.INSTANCE::movementEntityToMovement);
    }

    @Override
    public Mono<Movement> getMovementById(UUID movementId) {
        return movementRepository.findById(movementId)
                .map(MovementMapper.INSTANCE::movementEntityToMovement)
                .switchIfEmpty(Mono.empty());
    }
}
