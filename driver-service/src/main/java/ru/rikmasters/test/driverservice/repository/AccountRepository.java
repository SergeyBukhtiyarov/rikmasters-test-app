package ru.rikmasters.test.driverservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.rikmasters.test.driverservice.entity.Account;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, UUID> {
    List<Account> findAllByOwnerId(UUID ownerId);
}
