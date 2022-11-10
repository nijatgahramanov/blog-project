package com.blogproject.project.controller;

import com.blogproject.project.entity.User;
import com.blogproject.project.repository.UserRepository;
import com.blogproject.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping
    public User creteUser(@RequestBody User newUser) {
        return userService.saveOneUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId) {
        //custom Exception
        return userService.getOneUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
        return userService.updateOneUser(userId,newUser);
    }


    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userService.deleteById(userId);
    }

}
