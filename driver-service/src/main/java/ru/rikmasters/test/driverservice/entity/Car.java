package ru.rikmasters.test.driverservice.entity;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import common.entity.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "car")
@Getter
@Setter
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@NoArgsConstructor
@AllArgsConstructor
public class Car extends IdEntity {
    @Column(name = "car_json")
    @Type(type = "jsonb")
    private JSONObject carJson;
}
