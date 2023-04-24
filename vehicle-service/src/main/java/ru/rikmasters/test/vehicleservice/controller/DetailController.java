package ru.rikmasters.test.vehicleservice.controller;

import common.controller.MainAbstractController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rikmasters.test.vehicleservice.dto.DetailDto;
import ru.rikmasters.test.vehicleservice.entity.Detail;
import ru.rikmasters.test.vehicleservice.mapper.DetailMapper;
import ru.rikmasters.test.vehicleservice.repository.DetailRepository;
import ru.rikmasters.test.vehicleservice.service.DetailService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vehicle/detail")
public class DetailController extends MainAbstractController<Detail, DetailDto> {
    private final DetailService service;
    private final DetailMapper mapper;

    public DetailController(DetailMapper mapper, DetailRepository repository, DetailService service) {
        super(Detail.class, DetailDto.class, mapper, repository);
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("by-car/{id}")
    @Operation(summary = "Получение коллекции деталей по ID автомобиля")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ОК", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Не найдено ни одной детали для выбранного автомобиля", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content)
    })
    public ResponseEntity<List<DetailDto>> findAllByCar(@PathVariable("id") UUID id) {
        List<Detail> details = service.findAllByCarId(id);
        return ResponseEntity.ok(details.stream().map(mapper::asDTO).toList());
    }
}
