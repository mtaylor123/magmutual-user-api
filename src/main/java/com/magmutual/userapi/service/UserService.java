package com.magmutual.userapi.service;

import com.magmutual.userapi.exception.InvalidDateRangeException;
import com.magmutual.userapi.exception.UserNotFoundException;
import com.magmutual.userapi.model.User;
import com.magmutual.userapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> getUsersByDateRange(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new InvalidDateRangeException();
        }

        return userRepository.findByDateCreatedBetween(startDate, endDate);
    }

    public List<User> searchUsers(String q) {
        return userRepository.search(q);
    }
}