package aiss.restclient.service.github;


import aiss.restclient.model.github.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CommitService {
    private static final String TOKEN = "YOUR TOKEN HERE";

    @Autowired
    private RestTemplate restTemplate;

    public List<Commit> getAllCommits(String owner, String repo, Integer numPages,Integer numPerPage) {
        String baseUri = "https://api.github.com/repos/" + owner + "/" + repo + "/commits";

        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<Void> request = new HttpEntity<>(header);

        List<Commit> commits = new ArrayList<>();

        for(int i=1;i<=numPages;i++) {
            String uri = baseUri + "?page=" + i + "&per_page=" + numPerPage;
            ResponseEntity<Commit[]> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    request,
                    Commit[].class
            );
            if (response.getBody() != null) {
                commits.addAll(Arrays.asList(response.getBody()));
            }
        }
        return commits;
    }
}
