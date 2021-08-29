package com.trix.bugtracker.model;

import com.trix.bugtracker.model.User.User;
import com.trix.bugtracker.model.enums.Priority;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserTest {


    @Test
    void testBuilder() {
        //given

        //when
        User user = User.builder()
                .name("Tomas")
                .lastName("Samot")
                .age(24)
                .email("tomas@gmail.com")
                .id(1L)
                .build();

        //then
        assertEquals(user.getName(), "Tomas");
        assertEquals(user.getLastName(), "Samot");
        assertEquals(user.getAge(), 24);
        assertEquals(user.getEmail(), "tomas@gmail.com");
    }

    @Test
    void name() {
        //given


        LocalDateTime date = null;

        System.out.println(date + "");

        System.out.println(Priority.IMPORTANT.toString());

    }
}