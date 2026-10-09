package com.magmutual.userapi.controller;

import com.magmutual.userapi.model.User;
import com.magmutual.userapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by ID")
    public User getUserById(@PathVariable Long id) {
         return userService.getUserById(id);
    }


    @GetMapping
    @Operation(summary = "Get users created within a date range")
    public List<User> getUsersByDateRange( @Parameter(description = "Start date in yyyy-MM-dd format", example = "2020-01-01")
                                               @RequestParam LocalDate startDate,
                                           @Parameter(description = "End date in yyyy-MM-dd format", example = "2020-12-31")
                                           @RequestParam LocalDate endDate) {
        return userService.getUsersByDateRange(startDate, endDate);
    }

    @GetMapping("/search")
    @Operation(summary = "Search users across multiple fields")
    public List<User>  searchUsers( @Parameter(description = "Text to search across  name, email,  profession, country, and city") @RequestParam String q) {
        return userService.searchUsers(q);
    }


}