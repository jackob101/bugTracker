package com.trix.bugtracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String name;
    private String lastName;

    @Column(unique = true)
    private String email;
    private int age;

    @ManyToMany(mappedBy = "users")
    private List<Issue> issues;

    @ManyToMany(mappedBy = "assignedUsers")
    private List<Project> projects;

}
