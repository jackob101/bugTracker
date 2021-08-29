package com.trix.bugtracker.model.Project;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.trix.bugtracker.model.Issue.Issue;
import com.trix.bugtracker.model.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@JsonSerialize(using = ProjectSerializer.class)
@Builder
@AllArgsConstructor
@Data
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(message = "Name must be between 0 - 100 chars", min = 0, max = 100)
    @NotNull(message = "Name cannot be empty")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "Project_User",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> assignedUsers;


    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Issue> issues;

    public Project() {
        assignedUsers = new ArrayList<>();
        issues = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
