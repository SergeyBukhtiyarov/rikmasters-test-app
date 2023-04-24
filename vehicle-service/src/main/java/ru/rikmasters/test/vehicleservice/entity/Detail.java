package ru.rikmasters.test.vehicleservice.entity;

import common.entity.IdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Detail extends IdEntity {
    private String serialNumber;
    private String type;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
