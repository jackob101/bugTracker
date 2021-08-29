package com.trix.bugtracker.model.Issue;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.trix.bugtracker.model.Comment.Comment;
import com.trix.bugtracker.model.Project.Project;
import com.trix.bugtracker.model.User.User;
import com.trix.bugtracker.model.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonSerialize(using = IssueSerializer.class)
@Builder
@AllArgsConstructor
@Data
@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Size(min = 0, max = 200, message = "Description must be between 0 - 200 chars")
    @NotNull(message = "Description cannot be empty")
    private String description;

    private LocalDateTime openedTime;

    private LocalDateTime closedTime;


    @Enumerated(EnumType.ORDINAL)
    private Priority priority;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "createdById", referencedColumnName = "id")
    private User createdBy;

    @ManyToMany
    @JoinTable(
            name = "Issue_User",
            joinColumns = {@JoinColumn(name = "issue_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> users;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "projectId", referencedColumnName = "id")
    private Project project;

    @OneToMany(mappedBy = "issue", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    public Issue() {
        this.description = "";
        this.title = "";
        this.openedTime = LocalDateTime.now();
        this.closedTime = null;
        this.priority = Priority.IMPORTANT;
        this.users = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", openedTime=" + openedTime +
                ", closedTime=" + closedTime +
                ", priority=" + priority +
                '}';
    }
}
