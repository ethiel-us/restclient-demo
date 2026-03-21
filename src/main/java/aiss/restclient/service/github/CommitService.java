package aiss.restclient.service.github;


import aiss.restclient.model.github.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CommitService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Commit> getAllCommits(String owner, String repo) {
        String uri = "https://api.github.com/repos/" + owner + "/" + repo + "/commits";
        ResponseEntity<Commit[]> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                Commit[].class
        );
        if(response.getBody()!=null) return Arrays.asList(response.getBody());
        return new ArrayList<>();
    }
}
