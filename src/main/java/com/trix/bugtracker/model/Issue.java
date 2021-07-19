package com.trix.bugtracker.model;

import com.trix.bugtracker.model.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


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
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToMany
    @JoinTable(
            name = "Issue_User",
            joinColumns = { @JoinColumn(name = "issue_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<User> users;

    @ManyToOne
    private Project project;

    public Issue() {
        this.description = "";
        this.openedTime = LocalDateTime.now();
        this.closedTime = null;
        this.priority = Priority.IMPORTANT;
    }
}
