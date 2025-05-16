package com.dpilaloa.api.clients.movements.repository;

import com.dpilaloa.api.clients.movements.domain.models.MovementEntity;
import com.dpilaloa.api.clients.movements.service.dto.ReportDto;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface MovementRepository extends ReactiveCrudRepository<MovementEntity, UUID> {

    @Query("SELECT m.marker as marker, c.\"name\" as client, a.\"number\" as account, a.\"type\" as type, m.balance as initial, a.state as state, m.amount as amount, m.type as movement, " +
            "CASE WHEN m.type = 'Debito' THEN m.balance - m.amount ELSE m.balance + m.amount END as balance " +
            "FROM client c " +
            "INNER JOIN account a ON c.id = a.client " +
            "INNER JOIN movement m ON a.\"number\" = m.account " +
            "WHERE c.id = :id AND m.marker BETWEEN :startDate AND :endDate " +
            "ORDER BY m.marker")
    Flux<ReportDto>  findReportByClientAndDate (@Param("id") UUID id, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
