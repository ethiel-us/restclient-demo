package aiss.restclient.service;

import aiss.restclient.model.github.Commit;
import aiss.restclient.service.github.CommitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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
        List<Commit> commits = commitService.getAllCommits(owner, repo, 3, 5);
        Assertions.assertNotNull(commits, "Commit list is null");
        Assertions.assertFalse(commits.isEmpty(), "Commit list is empty");
        System.out.println("número de commits del repo " + repo + " : " + commits.size());

        String owner2 = "octocat";
        String repo2 = "Hello-World";
        List<Commit> commits2 = commitService.getAllCommits(owner2, repo2,3, 5);
        Assertions.assertNotNull(commits2, "Commit list is null");
        Assertions.assertFalse(commits2.isEmpty(), "Commit list is empty");
        System.out.println("número de commits del repo " + repo2 + " : " + commits2.size());
    }
}