package com.trix.bugtracker.model;

import com.trix.bugtracker.model.enums.Priority;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
@AllArgsConstructor
@Data
@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDateTime openedTime;
    private LocalDateTime closedTime;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Priority priority;

    @ManyToMany
    @JoinTable(
            name = "Issue_User",
            joinColumns = { @JoinColumn(name = "issue_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<User> users;


    @NotNull
    @ManyToOne
    private Project project;

    public Issue() {
        this.description = "";
        this.openedTime = LocalDateTime.now();
        this.closedTime = null;
        this.priority = Priority.IMPORTANT;
        this.users = new ArrayList<>();
    }



}
