package aiss.restclient.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ObjectService {

    @Autowired
    RestTemplate restTemplate;

    public List<Object> getAllObjects() {
        String url = "https://api.restful-api.dev/objects";
        Object[] response = restTemplate.getForObject(url, Object[].class);

        if (response == null) return new ArrayList<>();
        return  Arrays.stream(response).toList();
    }

    public Object getObjectById(String id) {
        String url = "https://api.restful-api.dev/objects/" + id;
        return restTemplate.getForObject(url, Object.class);
    }
}
