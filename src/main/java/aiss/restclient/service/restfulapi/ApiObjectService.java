package aiss.restclient.service.restfulapi;


import aiss.restclient.model.restfulapi.ApiObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class    ApiObjectService {

    @Autowired
    RestTemplate restTemplate;

    public List<ApiObject> getAllObjects() {
    String url = "https://api.restful-api.dev/objects";
    ResponseEntity<ApiObject[]> response  = restTemplate.exchange(
      url,
      HttpMethod.GET,
      null,
      ApiObject[].class
    );
        if (response.getBody() == null) return new ArrayList<>();
        return Arrays.asList(response.getBody());
    }

    public ApiObject getObjectById(String id) {
    String url = "https://api.restful-api.dev/objects/" + id;
        ResponseEntity<ApiObject> response = restTemplate.exchange(
          url,
          HttpMethod.GET,
          null,
          ApiObject.class
        );
        return response.getBody();
    }

    public ApiObject createObject(ApiObject object){
        String url = "https://api.restful-api.dev/objects";
        return restTemplate.postForObject(url, object, ApiObject.class);
    }

    public ApiObject updateObject(String id, ApiObject object){
        String url = "https://api.restful-api.dev/objects/" + id;
        HttpEntity<ApiObject> request = new HttpEntity<>(object);
        ResponseEntity<ApiObject> response = restTemplate.exchange(
                url, HttpMethod.PUT, request, ApiObject.class
        );
        return response.getBody();
    }

    public void deleteObject(String id){
        String url = "https://api.restful-api.dev/objects/" + id;
        ResponseEntity<Void> response = restTemplate.exchange(
                url, HttpMethod.DELETE, null, Void.class
        );

        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Object with id " + id + " not found");
        }
    }

}
