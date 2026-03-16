package aiss.restclient.service;


import aiss.restclient.model.github.Commit;
import org.springframework.beans.factory.annotation.Autowired;
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
        String url = "https://api.github.com/repos/" + owner + "/" + repo + "/commits";
        Commit[] response = restTemplate.getForObject(url, Commit[].class);
        if (response == null) return new ArrayList<>();
        return Arrays.asList(response);
    }
}
