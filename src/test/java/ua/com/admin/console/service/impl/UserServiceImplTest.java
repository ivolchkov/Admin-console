package ua.com.admin.console.service.impl;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.admin.console.dto.AddressDto;
import ua.com.admin.console.dto.UserDto;
import ua.com.admin.console.entity.AddressEntity;
import ua.com.admin.console.entity.UserEntity;
import ua.com.admin.console.repository.UserRepository;
import ua.com.admin.console.service.mapper.UserMapper;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UserServiceImpl.class})
public class UserServiceImplTest {
    private static final UserEntity USER_ENTITY = getUserEntity();

    private static final UserDto USER_DTO = getUserDto();

    private static final Integer ID = 1;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private UserRepository repository;

    @MockBean
    private UserMapper mapper;

    @Autowired
    private UserServiceImpl userService;

    @After
    public void resetMock() {
        reset(repository, mapper);
    }

    @Test
    public void create_shouldCreateUser() {
        when(mapper.mapDtoToEntity(any(UserDto.class))).thenReturn(USER_ENTITY);
        when(repository.save(any(UserEntity.class))).thenReturn(USER_ENTITY);
        when(mapper.mapEntityToDto(any(UserEntity.class))).thenReturn(USER_DTO);

        UserDto user = userService.create(USER_DTO);

        verify(repository).save(any(UserEntity.class));
        verify(mapper).mapEntityToDto(any(UserEntity.class));
        verify(mapper).mapDtoToEntity(any(UserDto.class));

        assertEquals(USER_DTO, user);
    }

    @Test
    public void findById_shouldFindUserById() {
        when(repository.findById(any(Integer.class))).thenReturn(Optional.of(USER_ENTITY));
        when(mapper.mapEntityToDto(any(UserEntity.class))).thenReturn(USER_DTO);

        UserDto user = userService.findById(ID);

        verify(repository).findById(any(Integer.class));
        verify(mapper).mapEntityToDto(any(UserEntity.class));

        assertEquals(USER_DTO, user);
    }

    @Test
    public void findById_shouldThrowIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Id is not valid");

        userService.findById(null);
    }

    @Test
    public void findById_ShouldThrowEntityNotFoundException() {
        exception.expect(EntityNotFoundException.class);
        exception.expectMessage("User is not found");

        when(repository.findById(any(Integer.class))).thenReturn(Optional.empty());

        userService.findById(ID);
    }

    @Test
    public void findAll_ShouldReturnListOfUsers() {
        Page<UserEntity> users = new PageImpl<>(Collections.singletonList(USER_ENTITY));
        Page<UserDto> expected = new PageImpl<>(Collections.singletonList(USER_DTO));
        Pageable pageable = PageRequest.of(1, 1);

        when(repository.findAll(pageable)).thenReturn(users);
        when(mapper.mapEntityToDto(USER_ENTITY)).thenReturn(USER_DTO);

        Page<UserDto> actual = userService.findAll(pageable);

        assertEquals(expected, actual);
    }

    @Test
    public void update_ShouldUpdateUser() {
        when(repository.findById(ID)).thenReturn(Optional.of(USER_ENTITY));
        when(mapper.mapDtoToEntity(USER_DTO)).thenReturn(USER_ENTITY);

        userService.update(USER_DTO, ID);

        verify(repository).save(USER_ENTITY);
    }

    @Test
    public void update_shouldThrowIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Id is not valid");

        userService.update(USER_DTO, null);
    }

    @Test
    public void delete_ShouldDeleteUser() {
        when(mapper.mapDtoToEntity(any(UserDto.class))).thenReturn(USER_ENTITY);

        userService.deleteById(ID);

        verify(repository).deleteById(ID);
    }

    @Test
    public void delete_shouldThrowIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Id is not valid");

        userService.deleteById(null);
    }

    private static UserEntity getUserEntity() {
        return UserEntity.newBuilder()
                .withId(1)
                .withName("Nick")
                .withSurname("Cage")
                .withLogin("nick cage")
                .withPassword("qwerty123")
                .withDateOfBirth(LocalDate.of(1985, 10, 1))
                .withAbout("Something about user")
                .withAddress(new AddressEntity("New York", "Broadway", 23))
                .build();
    }

    private static UserDto getUserDto() {
        return UserDto.newBuilder()
                .withName("Nick")
                .withSurname("Cage")
                .withLogin("nick cage")
                .withPassword("qwerty123")
                .withDateOfBirth(LocalDate.of(1985, 10, 1))
                .withAbout("Something about user")
                .withAddress(new AddressDto("New York", "Broadway", 23))
                .build();
    }

}