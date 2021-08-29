package com.trix.bugtracker.model.Comment;

import com.trix.bugtracker.model.Issue.Issue;
import com.trix.bugtracker.model.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
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

    public Comment(Long id, String comment, User commentOwner, Issue issue) {
        this.id = id;
        this.comment = comment;
        this.commentOwner = commentOwner;
        this.issue = issue;
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
