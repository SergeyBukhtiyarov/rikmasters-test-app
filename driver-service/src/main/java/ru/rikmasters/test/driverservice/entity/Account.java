package ru.rikmasters.test.driverservice.entity;

import common.entity.IdEntity;
import lombok.Getter;
import lombok.Setter;
import ru.rikmasters.test.driverservice.enums.Currency;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Account extends IdEntity {
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Driver owner;
    private Currency currency;
    private Double balance;

}
