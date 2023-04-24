package ru.rikmasters.test.driverservice.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.rikmasters.test.driverservice.dto.DriverDto;
import ru.rikmasters.test.driverservice.entity.Driver;
import ru.rikmasters.test.driverservice.mapper.DriverMapper;
import ru.rikmasters.test.driverservice.repository.DriverRepository;
import ru.rikmasters.test.driverservice.service.DriverService;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.rikmasters.test.driverservice.utils.TestUtils.*;

@WebMvcTest(DriverController.class)
@AutoConfigureMockMvc(addFilters = false)
public class DriverControllerTest {

    public static final String BASE_URL = "/drivers/driver";

    @Autowired
    MockMvc mockMvc;
    @MockBean
    DriverMapper mapper;
    @MockBean
    DriverRepository repository;
    @MockBean
    DriverService service;

    Driver driver = makeDriver();
    DriverDto driverDto = makeDriverDto();

    @Test
    @SneakyThrows
    void testFindAll_ShouldReturnCollectionOfDriver() {
        Page<Driver> page = new PageImpl<>(Collections.singletonList(driver));
        when(mapper.asDTO(any())).thenReturn(driverDto);
        when(repository.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get(BASE_URL))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value("00000000-0000-0000-0000-000000000000"))
                .andExpect(jsonPath("$[0].fio").value("Иванов Иван Водителевич"))
                .andExpect(jsonPath("$[0].birthdate").value("2023-04-22"))
                .andExpect(jsonPath("$[0].passport").value("3311000123"))
                .andExpect(jsonPath("$[0].licenseCategory").value("B1C1D1"))
                .andExpect(jsonPath("$[0].driverExperience").value("10"))
                .andExpect(jsonPath("$[0].accountBalance").isEmpty()) //todo connect entities
                .andDo(print());
    }

    @Test
    @SneakyThrows
    void testFindById_ShouldReturnOneDriver() {
        when(mapper.asDTO(any())).thenReturn(driverDto);
        when(repository.findById(any())).thenReturn(Optional.of(driver));

        mockMvc.perform(get(BASE_URL + "/{id}", driver.getId()))
                .andExpect(jsonPath("$.id").value("00000000-0000-0000-0000-000000000000"))
                .andExpect(jsonPath("$.fio").value("Иванов Иван Водителевич"))
                .andExpect(jsonPath("$.birthdate").value("2023-04-22"))
                .andExpect(jsonPath("$.passport").value("3311000123"))
                .andExpect(jsonPath("$.licenseCategory").value("B1C1D1"))
                .andExpect(jsonPath("$.driverExperience").value("10"))
                .andExpect(jsonPath("$.accountBalance").isEmpty()) //todo connect entities
                .andDo(print());
    }
    @Test
    @SneakyThrows
    void testFindOne_ShouldReturnErrorMessage() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        mockMvc.perform(get(BASE_URL + "/{id}", driver.getId()))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("Ресурс не найден"))
                .andExpect(jsonPath("$.details").isEmpty())
                .andDo(print());
    }

    @Test
    @SneakyThrows
    void testSave_ShouldReturnOk() {
        when(repository.save(any())).thenReturn(driver);
        when(mapper.asDTO(any())).thenReturn(driverDto);

        mockMvc.perform(post(BASE_URL)
                .content(asJson(driverDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("00000000-0000-0000-0000-000000000000"))
                .andExpect(jsonPath("$.fio").value("Иванов Иван Водителевич"))
                .andExpect(jsonPath("$.birthdate").value("2023-04-22"))
                .andExpect(jsonPath("$.passport").value("3311000123"))
                .andExpect(jsonPath("$.licenseCategory").value("B1C1D1"))
                .andExpect(jsonPath("$.driverExperience").value("10"))
                .andExpect(jsonPath("$.accountBalance").isEmpty()) //todo connect entities
                .andDo(print());
    }

    @Test
    @SneakyThrows
    void testSaveById_ShouldReturnOk() {
        when(repository.findById(driver.getId())).thenReturn(Optional.of(driver));
        when(mapper.asDTO(any())).thenReturn(driverDto);

        mockMvc.perform(put(BASE_URL + "/{id}",driver.getId())
                .content(asJson(driverDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("00000000-0000-0000-0000-000000000000"))
                .andExpect(jsonPath("$.fio").value("Иванов Иван Водителевич"))
                .andExpect(jsonPath("$.birthdate").value("2023-04-22"))
                .andExpect(jsonPath("$.passport").value("3311000123"))
                .andExpect(jsonPath("$.licenseCategory").value("B1C1D1"))
                .andExpect(jsonPath("$.driverExperience").value("10"))
                .andExpect(jsonPath("$.accountBalance").isEmpty()) //todo connect entities
                .andDo(print());
    }

    @Test
    @SneakyThrows
    void testSaveById_ShouldReturnNotFound()  {
        when(repository.findById(any())).thenReturn(Optional.empty());

        mockMvc.perform(put(BASE_URL + "/{id}", driver.getId().toString())
                        .content(asJson(driverDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist())
                .andDo(print());
    }

    @Test
    @SneakyThrows
    void testDelete_ShouldReturnNotFound() {
        mockMvc.perform(delete(BASE_URL + "/{id}", driver.getId().toString()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").doesNotExist())
                .andDo(print());
    }
}
