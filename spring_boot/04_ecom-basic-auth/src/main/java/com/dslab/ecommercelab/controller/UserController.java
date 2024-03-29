package com.dslab.ecommercelab.controller;

import com.dslab.ecommercelab.entity.User;
import com.dslab.ecommercelab.service.EcommUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    EcommUserService userService;

    //POST http://localhost:8080/user/register
    @PostMapping(path = "/register")
    public @ResponseBody User register(@RequestBody User user) {
        return userService.addUser(user);
    }

    //GET http://localhost:8080/user/all
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAll() {
        return userService.getAllUsers();
    }

    //GET http://localhost:8080/user/<mail>
    @GetMapping(path = "/{email}")
    public @ResponseBody
    Optional<User> getUser(Authentication auth, @PathVariable String email) {
        if (email.equalsIgnoreCase(auth.getName()))
            return userService.getByEmail(email);
        else return Optional.empty();
    }

    //DELETE http://localhost:8080/user/1
    @DeleteMapping(path = "/{id}")
    public @ResponseBody String deleteUser (@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
}
