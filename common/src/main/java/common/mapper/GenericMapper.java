package common.mapper;

import java.util.List;

public interface GenericMapper<E, D> {
    E asEntity(D dto);
    D asDTO(E entity);
}