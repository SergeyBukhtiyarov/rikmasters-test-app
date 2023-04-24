package ru.rikmasters.test.vehicleservice.entity;

import common.entity.IdEntity;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Car extends IdEntity {
    private String vin;
    private String plateNumber;
    @OneToMany(mappedBy = "car")
    private List<Detail> details;
}
