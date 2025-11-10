package com.example.module1.controller;


import com.example.module1.dto.UserDto;
import com.example.module1.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Создает user",
            description = "Создает user в базе данных"
    )
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        try {
            log.info("create New User: " + userDto.toString());
            var user = userService.createUser(userDto);
            log.info("created User: " + user.toString());
            return ResponseEntity.ok(user);
        }catch (Exception e){
            log.info("Произошла ошибка при создание user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ошибка " + e.getMessage());
        }
    }

    @Operation(
            summary = "Ищет user по id",
            description = "Ищет user по id в базе данных"
    )
    @GetMapping("/findUserById")
    public ResponseEntity<?> findUser(@RequestParam int id) {
        try {
            log.info("find user by id : " + id);
            var user = userService.findUserById(id);
            log.info("find User: " + user.toString());
            return ResponseEntity.ok(user);
        }catch (Exception e){
            log.info("Произошла ошибка при поиске user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ошибка " + e.getMessage());
        }
    }

    @Operation(
            summary = "Ищет user по email",
            description = "Ищет user по email в базе данных"
    )
    @GetMapping("/findUserByEmail")
    public ResponseEntity<?> findUser(@RequestParam String email) {
        try {
            log.info("find user by email : " + email);
            var user = userService.findUserByEmail(email);
            log.info("find User: " + user.toString());
            return ResponseEntity.ok(user);
        }catch (Exception e){
            log.info("Произошла ошибка при поиске user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ошибка " + e.getMessage());
        }
    }

    @Operation(
            summary = "удалет user",
            description = "Ищет user по id в базе данных и удаляет его"
    )
    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam int id) {
        try {
            log.info("delete user by id : " + id);
            var user = userService.deleteUser(id);
            log.info("delete User: " + user.toString());
            return ResponseEntity.ok("user deleted : " + user);
        }catch (Exception e){
            log.info("Произошла ошибка при удалении user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ошибка " + e.getMessage());
        }
    }

    @Operation(
            summary = "обновляет user",
            description = "Ищет user по id в базе данных и обновляет его"
    )
    @PatchMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestParam int id,
                                        @RequestBody UserDto userDto) {
        try {
            log.info("update user by id : " + id);
            var user = userService.updateUser(id,userDto);
            log.info("update User: " + user.toString());
            return ResponseEntity.ok("user update : " + user);
        }catch (Exception e){
            log.info("Произошла ошибка при удалении user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ошибка " + e.getMessage());
        }
    }

}
