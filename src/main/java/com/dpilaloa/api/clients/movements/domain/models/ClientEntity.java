package com.dpilaloa.api.clients.movements.domain.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("client")
public class ClientEntity{

    @Id
    @Column("id")
    private UUID id;

    @Column("name")
    private String name;

    @Column("gender")
    private String gender;

    @Column("age")
    private Integer age;

    @Column("identification")
    private String identification;

    @Column("address")
    private String address;

    @Column("phone")
    private String phone;

    @Column("state")
    private Boolean state;

    @Column("password")
    private String password;

}
