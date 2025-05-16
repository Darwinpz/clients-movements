package com.dpilaloa.api.clients.movements.util;

import com.dpilaloa.api.clients.movements.service.models.Account;
import com.dpilaloa.api.clients.movements.service.models.Client;
import com.dpilaloa.api.clients.movements.service.models.Movement;

import java.math.BigDecimal;
import java.util.UUID;

public class MockData {

    public static Client createClient() {
        Client client = new Client();
        client.setId(UUID.fromString("78c8cf1a-6eb2-4024-ae25-1a94a4103d14"));
        client.setName("Darwin Pilaloa");
        client.setAddress("Av. Paez");
        client.setPhone("0969140482");
        client.age(27);
        client.setIdentification("0705463420");
        client.setPassword("123");
        client.setState(true);
        return client;
    }

    public static Account createAccount() {
        Account account = new Account();
        account.setClient(UUID.fromString("78c8cf1a-6eb2-4024-ae25-1a94a4103d14"));
        account.setBalance(new BigDecimal("100.00"));
        account.setNumber(123456);
        account.setType(Constants.SAVINGS);
        account.setState(true);
        return account;
    }

    public static Movement createMovement() {
        Movement movement = new Movement();
        movement.setId(UUID.fromString("7042d2cd-e12f-4e69-b184-e24340fe36c8"));
        movement.setBalance(new BigDecimal("100.00"));
        movement.setType(Constants.CREDIT);
        movement.setAmount(new BigDecimal("20.00"));
        movement.setAccount(123456);
        return movement;
    }

}
