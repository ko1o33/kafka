package com.example.module1.service.Impl;


import com.example.module1.dto.UserDto;
import com.example.module1.entity.User;
import com.example.module1.exception.MyException;
import com.example.module1.kafka.KafkaProducer;
import com.example.module1.repository.UserRepository;
import com.example.module1.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final KafkaProducer kafkaProducer;

    @Override
    @Transactional
    public User createUser(UserDto userDto) {
        try {
            var newUser = User.builder()
                    .name(userDto.getName())
                    .email(userDto.getEmail())
                    .age(userDto.getAge())
                    .created_at(LocalDateTime.now())
                    .build();
            userRepository.save(newUser);
            kafkaProducer.sendTo("mail_crate", newUser);
            return newUser;
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public User findUserById(int id) {
        try {
            var user = userRepository.findById(id);
            if (user.isEmpty()) {
                throw new MyException("User not found");
            }
            return user.get();
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public User findUserByEmail(String email) {
        try {
            var user = userRepository.findByEmail(email);
            if (user.isEmpty()) {
                throw new MyException("User not found");
            }
            return user.get();
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public User deleteUser(int id) {
        try {
            var user = userRepository.findById(id);
            if (user.isEmpty()) {
                throw new MyException("User not found");
            }
            userRepository.delete(user.get());
            kafkaProducer.sendTo("mail_delete", user);
            return user.get();
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public User updateUser(int id, UserDto userDto) {
        try {
            var user = userRepository.findById(id);
            if (user.isEmpty()) {
                throw new MyException("User not found");
            }
            var updatedUser = user.get();
            updatedUser.setName(userDto.getName());
            updatedUser.setEmail(userDto.getEmail());
            updatedUser.setAge(userDto.getAge());
            userRepository.save(updatedUser);
            return user.get();
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }
}
