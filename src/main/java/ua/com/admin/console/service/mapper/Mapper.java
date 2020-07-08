package ua.com.admin.console.service.mapper;

public interface Mapper<E, D> {
    E mapDtoToEntity(D dto);

    D mapEntityToDto(E entity);
}
