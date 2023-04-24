package ru.rikmasters.test.driverservice.mapper;

import common.mapper.GenericMapper;
import org.mapstruct.Mapper;
import ru.rikmasters.test.driverservice.dto.AccountDto;
import ru.rikmasters.test.driverservice.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper extends GenericMapper<Account, AccountDto> {
    @Override
    Account asEntity(AccountDto dto);

    @Override
    AccountDto asDTO(Account entity);
}
