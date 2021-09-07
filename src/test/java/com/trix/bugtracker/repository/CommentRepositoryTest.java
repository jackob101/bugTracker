package com.trix.bugtracker.repository;

import com.trix.bugtracker.model.Comment.Comment;
import com.trix.bugtracker.model.Issue.Issue;
import com.trix.bugtracker.model.Project.Project;
import com.trix.bugtracker.model.User.User;
import com.trix.bugtracker.model.enums.Priority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    ProjectRepository projectRepository;

    Comment comment;
    User user;


//    @BeforeEach
    void setUp() {

        user = User.builder()
                .id(1L)
                .name("User name")
                .lastName("Last name")
                .age(21)
                .email("email@gmail.com")
                .nickname("nickname")
                .emailVerified(false)
                .build();
        user = userRepository.save(user);

        Issue issue = Issue.builder()
                .title("Test")
                .description("Issue description")
                .priority(Priority.IMPORTANT)
                .createdBy(user)
                .build();

        issue = issueRepository.save(issue);

        comment = new Comment(1L, "This is comment example", user, issue);
        comment = commentRepository.save(comment);

    }

//    @Test
    void findByUserId() {
        //given

        //when
        List<Comment> byUser_id = commentRepository.findByCommentOwner_id(user.getId());

        //then
        assertEquals(1, byUser_id.size());
    }


    @Test
    @Sql("/createData.sql")
    void testingDatasql() {
        //given

        //when
	commentRepository.findAll().forEach(System.out::println);
	projectRepository.findAll().forEach(project -> {
		System.out.println("Project");
		System.out.println(project.getAssignedUsers());
	    });
        //then
    }


    
}
