package ru.rikmasters.test.driverservice.dto;

import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;

    private JSONObject carJson;
}