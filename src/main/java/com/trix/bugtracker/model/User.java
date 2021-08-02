package com.trix.bugtracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 0, max = 100, message = "Name must be between 0 - 100 chars")
    @NotNull(message = "Name must not be empty")
    private String name;

    @Size(min = 0, max = 100, message = "Last name must be between 0 - 100 chars")
    @NotNull(message = "Last name must not be empty")
    private String lastName;

    @Email(message = "Please insert correct email")
    @NotNull(message = "Email cannot be null")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Age cannot be null")
    @Max(value = 200, message = "Max age is 200")
    private int age;

    @ManyToMany(mappedBy = "users")
    private List<Issue> issues;

    @ManyToMany(mappedBy = "assignedUsers")
    private List<Project> projects;

}
