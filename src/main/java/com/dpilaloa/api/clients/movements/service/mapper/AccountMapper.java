package com.dpilaloa.api.clients.movements.service.mapper;

import com.dpilaloa.api.clients.movements.domain.models.AccountEntity;
import com.dpilaloa.api.clients.movements.service.models.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account accountEntityToAccount(AccountEntity accountEntity);

    AccountEntity accountToAccountEntity(Account account);

}
