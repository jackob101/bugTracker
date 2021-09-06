package com.trix.bugtracker.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class CommentDTO{
    
    private Long id;

    private String comment;

    private Long userId;

    private Long issueId;

    public CommentDTO(String comment, Long userId, Long issueId){
	this.comment = comment;
	this.userId = userId;
	this.issueId = issueId;
    }

    public CommentDTO(Long id, String comment, Long userId, Long issueId){
	this.id = id;
	this.comment = comment;
	this.userId = userId;
	this.issueId = issueId;
    }


}
