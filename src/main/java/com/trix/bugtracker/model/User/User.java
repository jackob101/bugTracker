package com.trix.bugtracker.model.User;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.trix.bugtracker.model.Issue.Issue;
import com.trix.bugtracker.model.Project.Project;
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

@JsonSerialize(using = UserSerializer.class)
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

    @OneToMany(mappedBy = "createdBy")
    private List<Issue> submittedIssues;

    public User(Long id, String name, String lastName, String email, int age) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
