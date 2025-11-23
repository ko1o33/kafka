package com.example.module1.controller;


import com.example.module1.dto.UserDto;
import com.example.module1.entity.User;
import com.example.module1.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllUncaughtException(Exception ex) {
        log.info("Произошла ошибка при создание user: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Ошибка " + ex.getMessage());
    }

    @Operation(
            summary = "Создает user",
            description = "Создает user в базе данных"
    )
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        log.info("create New User: " + userDto.toString());
        var user = userService.createUser(userDto);
        log.info("created User: " + user.toString());
        EntityModel<User> userModel = EntityModel.of(user);
        userModel.add(linkTo(methodOn(UserController.class).createUser(userDto)).withRel("create"));

        return ResponseEntity.ok(userModel);

    }

    @Operation(
            summary = "Ищет user по id",
            description = "Ищет user по id в базе данных"
    )
    @GetMapping("/findUserById")
    public ResponseEntity<?> findUser(@RequestParam int id) {
        log.info("find user by id : " + id);
        var user = userService.findUserById(id);
        log.info("find User: " + user.toString());

        EntityModel<User> userModel = EntityModel.of(user);
        userModel.add(linkTo(methodOn(UserController.class).findUser(id)).withSelfRel());
        userModel.add(linkTo(methodOn(UserController.class).updateUser(id, new UserDto())).withRel("update"));
        userModel.add(linkTo(methodOn(UserController.class).deleteUser(id)).withRel("delete"));
        return ResponseEntity.ok(userModel);
    }

    @Operation(
            summary = "Ищет user по email",
            description = "Ищет user по email в базе данных"
    )
    @GetMapping("/findUserByEmail")
    public ResponseEntity<?> findUser(@RequestParam String email) {
        log.info("find user by email : " + email);
        var user = userService.findUserByEmail(email);
        log.info("find User: " + user.toString());

        EntityModel<User> userModel = EntityModel.of(user);
        userModel.add(linkTo(methodOn(UserController.class).findUser(email)).withSelfRel());
        return ResponseEntity.ok(userModel);
    }

    @Operation(
            summary = "удалет user",
            description = "Ищет user по id в базе данных и удаляет его"
    )
    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam int id) {

        log.info("delete user by id : " + id);
        var user = userService.deleteUser(id);
        log.info("delete User: " + user.toString());
        return ResponseEntity.ok("user deleted : " + user);

    }

    @Operation(
            summary = "обновляет user",
            description = "Ищет user по id в базе данных и обновляет его"
    )
    @PatchMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestParam int id,
                                        @RequestBody UserDto userDto) {

        log.info("update user by id : " + id);
        var user = userService.updateUser(id, userDto);
        log.info("update User: " + user.toString());
        return ResponseEntity.ok("user update : " + user);

    }

}
