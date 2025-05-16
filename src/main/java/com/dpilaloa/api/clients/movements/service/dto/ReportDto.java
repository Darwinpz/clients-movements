package com.dpilaloa.api.clients.movements.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportDto {

    private LocalDateTime marker;
    private String client;
    private Integer account;
    private String type;
    private Double initial;
    private Boolean state;
    private Double amount;
    private String movement;
    private Double balance;

}
