package ua.com.admin.console.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.admin.console.dto.UserDto;

public interface UserService {
    /**
     * Returns String which represents what url will be shown.
     *
     * @param request element who has all needed information for processing command.
     * @return String which represents what url will be shown
     */
    UserDto create(UserDto user);

    UserDto findById(Integer id);

    Page<UserDto> findAll(Pageable pageable);

    UserDto update(UserDto user, Integer id);

    void deleteById(Integer id);
}
