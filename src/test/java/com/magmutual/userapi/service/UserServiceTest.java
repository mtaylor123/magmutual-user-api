package com.magmutual.userapi.service;

import com.magmutual.userapi.exception.InvalidDateRangeException;
import com.magmutual.userapi.exception.UserNotFoundException;
import com.magmutual.userapi.model.User;
import com.magmutual.userapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    void testGetUserById() {
        User user = new User(
                100L, "Andree", "Flita", "Andree.Flita@gmail.com", "worker",
                LocalDate.of(2020, 8, 31), "Wallis and Futuna", "Nanjing"
          );

        when(userRepository.findById(100L))
                .thenReturn(Optional.of(user));

        User result = userService.getUserById(100L);
        assertEquals(100L, result.getId());
           assertEquals("Andree", result.getFirstname());
        verify(userRepository).findById(100L);
    }



    @Test
    void testGetUserByIdUserNotFound() {
        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                UserNotFoundException.class,
                () -> userService.getUserById(1L)
        );
    }



    @Test
    void testGetUsersByDateRange() {
        LocalDate start = LocalDate.of(2020, 1, 1);

        LocalDate end = LocalDate.of(2020, 12, 31);
        User user = new User(100L, "Andree", "Flita", "Andree.Flita@gmail.com",
                "worker", LocalDate.of(2020, 8, 31), "Wallis and Futuna", "Nanjing"
        );

        when(userRepository.findByDateCreatedBetween(start, end))
                .thenReturn(List.of(user));

        List<User> results =
                userService.getUsersByDateRange(start, end);

        assertEquals(1, results.size());
        assertEquals(100L, results.getFirst().getId());

        verify(userRepository)
                .findByDateCreatedBetween(start, end);
    }

    @Test
    void testGetUsersByDateRangeException() {
        LocalDate start = LocalDate.of(2021, 1, 1);
        LocalDate end = LocalDate.of(2020, 1, 1);

        assertThrows(
                InvalidDateRangeException.class,
                () -> userService.getUsersByDateRange(start, end)
        );
        verifyNoInteractions(userRepository);
    }

    @Test
    void testSearchUsers() {
        User user = new User(
                111L, "Justinn", "Lymann", "Justinn.Lymann@gmail.com",
                "developer", LocalDate.of(2020, 11, 19), "Georgia",
                "Lomé"
        );

        when(userRepository.search("developer"))
                .thenReturn(List.of(user));

        List<User> results =
                userService.searchUsers("developer");

        assertEquals(1, results.size());
        assertEquals("developer", results.getFirst().getProfession());
        verify(userRepository).search("developer");
    }


}