package aiss.restclient.service;

import aiss.restclient.service.restfulapi.ObjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ObjectServiceTest {


    @Autowired
    ObjectService objectService;


    @Test
    @DisplayName("Get all objects")
    void getAllObjects() {
        List<Object> objects = objectService.getAllObjects();
        Assertions.assertFalse(objects.isEmpty(), "No objects found");
        System.out.println(objects);
    }

    @Test
    @DisplayName("Get object by id")
    void getObjectById() {
        Object object = objectService.getObjectById("1");
        Assertions.assertNotNull(object, "Object not found");
        System.out.println(object);
    }
}