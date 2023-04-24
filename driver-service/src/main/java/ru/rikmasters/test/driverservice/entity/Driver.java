package ru.rikmasters.test.driverservice.entity;

import common.entity.IdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Driver extends IdEntity {
    @Column(name = "fio", nullable = false)
    private String fio;
    @Column(name = "birthdate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Column(name = "passport", nullable = false, unique = true, length = 10)
    private String passport;
    @Column(name = "license_category", nullable = false)
    private String licenseCategory;
    @Column(name = "driver_experience", nullable = false)
    private Integer driverExperience;
    @OneToMany
    private List<Car> cars;
}
