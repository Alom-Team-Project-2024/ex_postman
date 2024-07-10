package com.example.expostman.controller;

import com.example.expostman.entity.User;
import com.example.expostman.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * postman에서 http://localhost:8080/users 경로에 GET요청을 보낼 경우
     * 모든 유저 정보 JSON형식으로 return
     */
    @GetMapping("/users")
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    /**
     * http://localhost:8080/users/{id} GET 요청을 보내는경우
     * 해당 ID 값의 유저만 반환
     */
    @GetMapping("/users/{id}")
    public Optional<User> findUser(@PathVariable("id") long id) {
        return userRepository.findById(id);
    }

    /**
     * http://localhost:8080/users 경로에 POST요청을 보낼 경우
     * Query Params에 Key, Value 값으로 username, password를 입력 후 전송시 DB에 저장
     */

    @PostMapping("/users")
    public void joinUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        userRepository.save(user);
    }
}
