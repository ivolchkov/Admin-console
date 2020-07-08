package ua.com.admin.console.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.com.admin.console.dto.UserDto;
import ua.com.admin.console.entity.UserEntity;
import ua.com.admin.console.repository.UserRepository;
import ua.com.admin.console.service.UserService;
import ua.com.admin.console.service.mapper.Mapper;

import javax.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {
    private final Mapper<UserEntity, UserDto> userMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(Mapper<UserEntity, UserDto> userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    /**
     * Method which provide the functionality to saving user in database.
     *
     * @param user input user who has not any id yet.
     * @return UserDto which has already saved to database.
     */
    @Override
    public UserDto create(UserDto user) {
        return userMapper.mapEntityToDto(saveUser(user));
    }

    /**
     * Method which provide the functionality to finding user in database.
     *
     * @param id identifier for searching a user.
     * @return UserDto which was found in database.
     * @throws EntityNotFoundException if user was not found.
     * @throws IllegalArgumentException if id was null.
     */
    @Override
    public UserDto findById(Integer id) {
        validateNotNull(id, "Id");

        return userMapper.mapEntityToDto(findUserById(id));
    }

    /**
     * Method which provide the functionality to finding all users in database.
     *
     * @param pageable information about how many users have to be shown on one page.
     * @return Page<UserDto> which represents one page of users in database.
     */
    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository
                .findAll(pageable)
                .map(userMapper::mapEntityToDto);
    }

    /**
     * Method for updating one user.
     *
     * @param id identifier for a user who will be updated.
     * @return UserDto which was successfully updated.
     * @throws EntityNotFoundException if user was not found in database.
     * @throws IllegalArgumentException if id or user was null.
     */
    @Override
    public UserDto update(UserDto user, Integer id) {
        validateNotNull(id, "Id");
        validateNotNull(user, "User");

        findUserById(id);
        user.setId(id);

        return userMapper.mapEntityToDto(saveUser(user));
    }

    /**
     * Method for deleting user from database.
     *
     * @param id identifier for a user who will be deleted.
     * @throws IllegalArgumentException if id was null.
     */
    @Override
    public void deleteById(Integer id) {
        validateNotNull(id, "Id");

        userRepository.deleteById(id);
    }

    private void validateNotNull(Object object, String name) {
        if (object == null) {
            throw new IllegalArgumentException(name + " is not valid");
        }
    }

    private UserEntity saveUser(UserDto user) {
        return userRepository.save(userMapper.mapDtoToEntity(user));
    }

    private UserEntity findUserById(Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User is not found"));
    }
}
