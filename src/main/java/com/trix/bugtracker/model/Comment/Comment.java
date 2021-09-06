package com.trix.bugtracker.model.Comment;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.trix.bugtracker.model.Issue.Issue;
import com.trix.bugtracker.model.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@JsonSerialize(using = CommentSerializer.class)
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="comment")
    private String comment;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User commentOwner;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @NotNull
    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    public Comment(Long id, String comment, User commentOwner, Issue issue) {
        this.id = id;
        this.comment = comment;
        this.commentOwner = commentOwner;
        this.issue = issue;
	this.creationDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", commentOwnerId=" + commentOwner.getId() +
                '}';
    }
}
