package common.controller;

import common.exception.ResourceNotFoundException;
import common.mapper.GenericMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public abstract class MainAbstractController<Entity, DTO> {
    private final Class<Entity> entityClass;
    private final Class<DTO> modelClass;
    private final GenericMapper<Entity, DTO> mapper;
    private final PagingAndSortingRepository<Entity, UUID> repository;

    protected MainAbstractController(Class<Entity> entityClass,
                                     Class<DTO> modelClass,
                                     GenericMapper<Entity, DTO> mapper,
                                     PagingAndSortingRepository<Entity, UUID> repository) {
        this.entityClass = entityClass;
        this.modelClass = modelClass;
        this.mapper = mapper;
        this.repository = repository;
    }

    @ResponseBody
    @GetMapping({""})
    @Operation(
            summary = "Получение списка ресурсов (в режиме пагинации)"
    )
    public ResponseEntity<List<DTO>> findAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                             @RequestParam(value = "size", defaultValue = "20", required = false) Integer size,
                                             @RequestParam(value = "sort", defaultValue = "no sort", required = false) String sort) {
        Sort sorting = Sort.by(sort);
        Page<Entity> entityPage = this.repository.findAll(PageRequest.of(page, size, sorting));

        List<DTO> dtos = entityPage.getContent()
                .stream()
                .map(this.mapper::asDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @ResponseBody
    @Operation(
            summary = "Получение ресурса по ID"
    )
    @GetMapping({"/{uuid}"})
    public ResponseEntity<DTO> findOne(@PathVariable("uuid") UUID uuid) throws ResourceNotFoundException {
        Entity entity = this.repository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("Ресурс не найден"));
        return ResponseEntity.ok(this.mapper.asDTO(entity));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping({""})
    @Operation(
            summary = "Создание нового ресурса"
    )
    public ResponseEntity<DTO> save(@RequestBody Entity entity) {
        Entity saved = this.repository.save(entity);
        return ResponseEntity.ok(this.mapper.asDTO(saved));
    }

    @ResponseBody
    @Operation(
            summary = "Изменение данных ресурса"
    )
    @PutMapping({"/{uuid}"})
    public ResponseEntity<?> saveById(@RequestBody DTO dto, @PathVariable("uuid") UUID uuid) {
        Optional<Entity> optional = this.repository.findById(uuid);

        if (optional.isPresent()) {
            Entity entity = this.mapper.asEntity(dto);
            return ResponseEntity.ok(this.mapper.asDTO(this.repository.save(entity)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @Operation(
            summary = "Удаление ресурса"
    )
    @DeleteMapping({"/{uuid}"})
    public ResponseEntity<?> delete(@PathVariable("uuid") UUID uuid) {
        this.repository.deleteById(uuid);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}