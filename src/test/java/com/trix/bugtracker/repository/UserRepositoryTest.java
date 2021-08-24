package com.trix.bugtracker.repository;

import com.trix.bugtracker.model.Issue.Issue;
import com.trix.bugtracker.model.Project.Project;
import com.trix.bugtracker.model.User.User;
import com.trix.bugtracker.model.enums.Priority;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    ProjectRepository projectRepository;

    Issue issue;

    List<User> users;

    @BeforeEach
    void setUp() {


        Project project = Project.builder().name("Test").description("test desc").build();
        projectRepository.save(project);

        issue = Issue.builder()
                .description("Test desc")
                .priority(Priority.IMPORTANT)
                .title("Issue title")
                .users(new ArrayList<>())
                .build();

        users = List.of(User.builder()
                        .name("User 1")
                        .lastName("Last name 1")
                        .email("email1@gmail.com")
                        .nickname("Nickname 1")
                        .age(15)
                        .emailVerified(false)
                        .build(),
                User.builder()
                        .name("User 2")
                        .lastName("Last name 2")
                        .email("email2@gmail.com")
                        .nickname("Nickname 2")
                        .age(15)
                        .emailVerified(false)
                        .build(),
                User.builder()
                        .name("User 3")
                        .lastName("Last name 3")
                        .email("email3@gmail.com")
                        .nickname("Nickname 3")
                        .age(15)
                        .emailVerified(false)
                        .build());

        users = userRepository.saveAll(users);
        issue.getUsers().add(users.get(1));
        issue.setCreatedBy(users.get(1));
        issue.setProject(project);
        issueRepository.save(issue);

    }

//    @Test
//    void testUserNotInIssue() {
//        //given
//
//        //when
//
//        //then
//        List<User> byIssues_idIsNot = userRepository.findByNotAssigned(issue.getId());
//        int size = byIssues_idIsNot.size();
//        assertEquals(2, size) ;
//    }
}