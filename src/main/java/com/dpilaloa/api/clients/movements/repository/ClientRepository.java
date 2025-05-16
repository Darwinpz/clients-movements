package com.dpilaloa.api.clients.movements.repository;

import com.dpilaloa.api.clients.movements.domain.models.ClientEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends ReactiveCrudRepository<ClientEntity, UUID> {
}
