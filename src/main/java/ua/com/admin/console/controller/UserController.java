package ua.com.admin.console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.admin.console.dto.UserDto;
import ua.com.admin.console.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private static final String ID = "/{id}";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(ID)
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUsers(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC)
                                                                 Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto user) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.create(user));
    }

    @PutMapping(ID)
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @RequestBody UserDto user) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.update(user, id));

    }

    @DeleteMapping(ID)
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        userService.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
