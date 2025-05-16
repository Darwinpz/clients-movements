package com.dpilaloa.api.clients.movements.domain.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("movement")
public class MovementEntity {

    @Id
    @Column("id")
    private UUID id;

    @Column("type")
    private String type;

    @Column("balance")
    private BigDecimal balance;

    @Column("amount")
    private BigDecimal amount;

    @Column("account")
    private Integer account;

    @Column("marker")
    private LocalDateTime marker;
}
