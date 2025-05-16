package com.dpilaloa.api.clients.movements.domain.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Table("account")
public class AccountEntity {

    @Id
    @Column("number")
    private Integer number;

    @Column("type")
    private String type;

    @Column("balance")
    private BigDecimal balance;

    @Column("state")
    private Boolean state;

    @Column("client")
    private UUID client;
}
