package ua.com.admin.console.service.mapper;

import org.springframework.stereotype.Component;
import ua.com.admin.console.dto.AddressDto;
import ua.com.admin.console.entity.AddressEntity;

@Component
public class AddressMapper implements Mapper<AddressEntity, AddressDto> {
    @Override
    public AddressEntity mapDtoToEntity(AddressDto dto) {
        return dto == null ? null : new AddressEntity(
                dto.getCity(),
                dto.getStreet(),
                dto.getStreetNumber()
        );
    }

    @Override
    public AddressDto mapEntityToDto(AddressEntity entity) {
        return entity == null ? null : new AddressDto(
                entity.getCity(),
                entity.getStreet(),
                entity.getStreetNumber()
        );
    }
}
