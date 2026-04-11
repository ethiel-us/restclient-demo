package aiss.restclient.service.restfulapi;

import aiss.restclient.model.restfulapi.ApiObject;
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
public class ApiObjectServiceAuth {
    private static final String API_KEY = "7ea0b4e6-9e5d-4b06-a6f3-f6c4d06385b6";

    @Autowired
    RestTemplate restTemplate;

    private static HttpHeaders buildHeader() {
        HttpHeaders header = new HttpHeaders();
        header.set("x-api-key", API_KEY);
        return header;
    }

    public List<ApiObject> getAllObjectsCollection(String collection) {
        String url = "https://api.restful-api.dev/collections/" + collection + "/objects";

        HttpEntity<Void> request = new HttpEntity<>(buildHeader());

        ResponseEntity<ApiObject[]> response  = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                ApiObject[].class
        );
        if (response.getBody() == null) return new ArrayList<>();
        return Arrays.asList(response.getBody());
    }

    public ApiObject getObjectByIdCollection(String id, String collection) {
    String url = "https://api.restful-api.dev/collections/" + collection + "/objects/" + id;

    HttpEntity<Void> request = new HttpEntity<>(buildHeader());

    ResponseEntity<ApiObject> response = restTemplate.exchange(
          url,
          HttpMethod.GET,
          request,
          ApiObject.class
        );
        return response.getBody();
    }

    public ApiObject createObjectCollection(ApiObject object, String collection){
        String url = "https://api.restful-api.dev/collections/" + collection + "/objects";

        HttpEntity<ApiObject> request = new HttpEntity<>(object, buildHeader());
        ResponseEntity<ApiObject> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                ApiObject.class
        );
        return response.getBody();
    }

    public ApiObject updateObjectCollection(String id, String collection, ApiObject object){
        String url = "https://api.restful-api.dev/collections/" + collection + "/objects/" + id;

        HttpEntity<ApiObject> request = new HttpEntity<>(object, buildHeader());

        ResponseEntity<ApiObject> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                request,
                ApiObject.class
        );
        return response.getBody();
    }

    public void deleteObjectCollection(String id, String collection){
        String url = "https://api.restful-api.dev/collections/" + collection + "/objects/" + id;

        HttpEntity<Void> request = new HttpEntity<>(buildHeader());

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                request,
                Void.class
        );
    }
}
