package com.trix.bugtracker.repository;

import com.trix.bugtracker.model.Issue.Issue;
import com.trix.bugtracker.model.Project.Project;
import com.trix.bugtracker.model.User.User;
import com.trix.bugtracker.model.enums.Priority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class IssueRepositoryTest {

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectRepository projectRepository;

    Project project;

    Project project2;

    List<Issue> issues;

    List<User> users;


    @BeforeEach
    void setUp() {
        User user1 = User.builder()
                .id(1L)
                .name("Tomas")
                .lastName("Samot")
                .age(25)
                .email("toma@gmail.com")
                .build();

        User user2 = User.builder()
                .id(2L)
                .name("Andrew")
                .lastName("Werdna")
                .age(35)
                .email("and@gmail.com")
                .build();

        User user3 = User.builder()
                .id(3L)
                .name("Henry")
                .lastName("YrneH")
                .age(19)
                .email("hen@gmail.com")
                .build();

        users = userRepository.saveAll(Arrays.asList(user1, user2, user3));

        project = Project.builder()
                .name("Testing")
                .build();

        project2 = Project.builder()
                .name("Testing 2")
                .build();

        project = projectRepository.save(project);
        project2 = projectRepository.save(project2);


        Issue issue1 = Issue.builder()
                .priority(Priority.IMPORTANT)
                .project(project2)
                .openedTime(LocalDateTime.now())
                .description("Description number 1")
                .users(Arrays.asList(user1, user2))
                .closedTime(LocalDateTime.now())
                .createdBy(users.get(1))
                .build();

        Issue issue2 = Issue.builder()
                .priority(Priority.URGENT)
                .openedTime(LocalDateTime.now())
                .description("Description number 2")
                .users(Collections.singletonList(user1))
                .createdBy(users.get(1))
                .project(project)
                .build();

        Issue issue3 = Issue.builder()
                .priority(Priority.NOT_IMPORTANT)
                .openedTime(LocalDateTime.now())
                .description("Description number 3")
                .users(Arrays.asList(user1, user3))
                .createdBy(users.get(1))
                .project(project2)
                .build();

        Issue issue4 = Issue.builder()
                .priority(Priority.URGENT)
                .openedTime(LocalDateTime.now())
                .description("Description number 4")
                .users(Arrays.asList(user1, user2, user3))
                .closedTime(LocalDateTime.now())
                .createdBy(users.get(1))
                .project(project)
                .build();

        issues = issueRepository.saveAll(Arrays.asList(issue1, issue2, issue3, issue4));

    }

    @Test
    void testFindByUsersId() {
        //given

        //when
        List<Issue> user1Issues = issueRepository.findByUsersId(users.get(0).getId());
        List<Issue> user2Issues = issueRepository.findByUsersId(users.get(1).getId());
        List<Issue> user3Issues = issueRepository.findByUsersId(users.get(2).getId());

        //then
        assertEquals(4, user1Issues.size());
        assertEquals(2, user2Issues.size());
        assertEquals(2, user3Issues.size());
    }

    @Test
    void testFindByProjectId() {
        //given

        //when
        List<Issue> issues = issueRepository.findByProjectId(project.getId());

        //then
        assertEquals(2, issues.size());
    }

    @Test
    void testFindOpenedIssuesByProjectId() {
        //given

        //when
        List<Issue> openedIssues = issueRepository.findOpenedByProjectId(project.getId());

        //then
        assertEquals(1, openedIssues.size());
    }


}
