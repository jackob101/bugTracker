package com.trix.bugtracker.services.implementation;

import com.trix.bugtracker.model.User.User;
import com.trix.bugtracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .name("Tomas")
                .lastName("Samot")
                .age(54)
                .email("Tom@gmail.com")
                .build();
    }

    @Test
    void findById() {
        //given


        //when
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        User userFound = userService.findById(1L);

        //then
        assertNotNull(userFound);
        assertEquals(user, userFound);

    }

    @Test
    void findById_notFound() {
        //given

        //when
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        //then
        assertNull(userService.findById(1L));
    }

    @Test
    void save() {
        //given
        user.setId(1L);

        //when
        when(userRepository.save(user)).thenReturn(user);
        User saved = userService.save(user);

        //then
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(user.getName(), saved.getName());
    }

    @Test
    void save_nullParam() {
        //given

        //when

        //then
        assertNull(userService.save(null));
    }

    @Test
    void saveAll_null() {
        //given
        List<User> users = Arrays.asList(User.builder()
                        .name("Name1")
                        .lastName("LastName1")
                        .build(),
                User.builder()
                        .name("Name2")
                        .lastName("LastName2")
                        .build(),
                null,
                User.builder()
                        .name("Name2")
                        .lastName("LastName2")
                        .build()
                );

        //when

        //then
        assertThrows(IllegalArgumentException.class,() -> userService.saveAll(users));
        assertThrows(IllegalArgumentException.class,() -> userService.saveAll(null));
    }

    @Test
    void saveAll() {
        //given
        List<User> users = Arrays.asList(User.builder()
                        .name("Name1")
                        .lastName("LastName1")
                        .build(),
                User.builder()
                        .name("Name2")
                        .lastName("LastName2")
                        .build(),
                User.builder()
                        .name("Name2")
                        .lastName("LastName2")
                        .build()
        );

        //when
        when(userRepository.saveAll(users)).thenReturn(users);

        //then
        assertEquals(3,userService.saveAll(users).size());
    }

    @Test
    void deleteByEntity() {
        //given
        //when
        //then
        assertTrue(userService.delete(user));
    }

    @Test
    void deleteById() {
        //given
        //when
        //then
        assertTrue(userService.delete(1L));
    }

    @Test
    void deleteNull() {
        //given
        //when
        //then
        assertFalse(userService.delete((User)null));
        assertFalse(userService.delete((Long)null));
    }

}























