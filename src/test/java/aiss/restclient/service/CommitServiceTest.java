package aiss.restclient.service;

import aiss.restclient.model.github.Commit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommitServiceTest {

    @Autowired
    CommitService commitService;

    @Test
    @DisplayName("Get all commits from a repo")
    void getAllCommits() {
        String owner = "spring-projects";
        String repo = "spring-framework";
        List<Commit> commits = commitService.getAllCommits(owner, repo);
        Assertions.assertFalse(commits.isEmpty(), "Commit list is empty");
        System.out.println(commits);

    }
}