package ru.rikmasters.test.driverservice.kafka.service.iface;

import com.nimbusds.jose.shaded.json.JSONObject;

public interface CarSaveService {
    
    void saveOrUpdate(JSONObject employee);
}
