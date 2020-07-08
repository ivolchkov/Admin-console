package ua.com.admin.console.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.admin.console.dto.UserDto;
import ua.com.admin.console.entity.UserEntity;

@Component
public class UserMapper implements Mapper<UserEntity, UserDto> {
    private final AddressMapper addressMapper;

    @Autowired
    public UserMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public UserEntity mapDtoToEntity(UserDto dto) {
        return dto == null ? null : UserEntity.newBuilder()
                .withId(dto.getId())
                .withName(dto.getName())
                .withSurname(dto.getSurname())
                .withLogin(dto.getLogin())
                .withPassword(dto.getPassword())
                .withDateOfBirth(dto.getDateOfBirth())
                .withAbout(dto.getAbout())
                .withAddress(addressMapper.mapDtoToEntity(dto.getAddress()))
                .build();
    }

    @Override
    public UserDto mapEntityToDto(UserEntity entity) {
        return entity == null ? null : UserDto.newBuilder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withSurname(entity.getSurname())
                .withLogin(entity.getLogin())
                .withPassword(entity.getPassword())
                .withDateOfBirth(entity.getDateOfBirth())
                .withAbout(entity.getAbout())
                .withAddress(addressMapper.mapEntityToDto(entity.getAddress()))
                .build();
    }
}
