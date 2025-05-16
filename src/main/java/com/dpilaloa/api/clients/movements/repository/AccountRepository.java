package com.dpilaloa.api.clients.movements.repository;

import com.dpilaloa.api.clients.movements.domain.models.AccountEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;


@Repository
public interface AccountRepository extends ReactiveCrudRepository<AccountEntity, Integer> {

    @Query("SELECT * FROM account WHERE client = :id")
    Flux<AccountEntity> findAccountsByClientId(@Param("id") UUID id);

}
