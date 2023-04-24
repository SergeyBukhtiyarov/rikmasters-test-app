package ru.rikmasters.test.vehicleservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.rikmasters.test.vehicleservice.entity.Detail;

import java.util.List;
import java.util.UUID;

@Repository
public interface DetailRepository extends PagingAndSortingRepository<Detail, UUID> {
    List<Detail> findAllByCarId(UUID id);
}
