package aiss.restclient.service.restfulapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<Object[]> response  = restTemplate.exchange(
      url,
      HttpMethod.GET,
      null,
      Object[].class
    );
        if (response.getBody() == null) return new ArrayList<>();
        return Arrays.asList(response.getBody());
    }

    public Object getObjectById(String id) {
        String url = "https://api.restful-api.dev/objects/" + id;
        ResponseEntity<Object> response = restTemplate.exchange(
          url,
          HttpMethod.GET,
          null,
          Object.class
        );
        return response.getBody();
    }
}
