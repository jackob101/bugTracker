package com.trix.bugtracker.services.implementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.trix.bugtracker.DTO.CommentDTO;
import com.trix.bugtracker.model.Comment.Comment;
import com.trix.bugtracker.model.Issue.Issue;
import com.trix.bugtracker.model.User.User;
import com.trix.bugtracker.repository.CommentRepository;
import com.trix.bugtracker.services.interfaces.IssueService;
import com.trix.bugtracker.services.interfaces.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest{

    @Mock
    UserService userService;

    @Mock
    IssueService issueService;

    @Mock
    CommentRepository commentRepository;

    @InjectMocks
    CommentServiceImpl commentServiceImpl;

    User user;

    Issue issue;

    @BeforeEach
    public void setUp(){
	user = 	User.builder()
	    .id(1L)
	    .age(24)
	    .email("Example@gmail.com")
	    .name("Name")
	    .lastName("Last name")
	    .nickname("Example nickname")
	    .build();

	issue = Issue.builder()
	    .id(1L)
	    .title("Issue title")
	    .description("Issue description")
	    .createdBy(user)
	    .build();
    }

    @Test
    public void saveDtoTest(){
	//given
	CommentDTO commentDTO = new CommentDTO(1L, "This is example comment", 1L, 1L);

	//when
	when(userService.findById(anyLong())).thenReturn(user);
	when(issueService.findById(anyLong())).thenReturn(issue);
	when(commentRepository.save(Mockito.any(Comment.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());

	//then
	Comment saved = commentServiceImpl.save(commentDTO);

	assertEquals(1L, saved.getCommentOwner().getId());
	assertEquals(1L, saved.getIssue().getId());
	assertEquals(commentDTO.getComment(), saved.getComment());

    }

}
