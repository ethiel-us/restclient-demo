package aiss.restclient.service.restfulapi;

import aiss.restclient.model.restfulapi.ApiObject;
import aiss.restclient.model.restfulapi.Data;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApiObjectServiceTest {


    @Autowired
    ApiObjectService apiObjectService;

    private ApiObject buildObject(String name, String color, String capacity) {
        ApiObject apiObject = new ApiObject();
        apiObject.setName(name);
        Data data = new Data();
        data.setColor(color);
        data.setCapacity(capacity);
        apiObject.setData(data);
        return apiObject;
    }

    @Test
    @Order(1)
    @DisplayName("GET - Get all objects")
    void getAllObjects() {
        List<ApiObject> objects = apiObjectService.getAllObjects();

        Assertions.assertNotNull(objects, "Objects list is null");
        Assertions.assertFalse(objects.isEmpty(), "Objects list is empty");

        System.out.println(objects);
    }

    @Test
    @Order(2)
    @DisplayName("GET - Get object by id")
    void getObjectById() {
        ApiObject object = apiObjectService.getObjectById("1");

        Assertions.assertNotNull(object, "Object is null");
        Assertions.assertEquals("1", object.getId(), "Object id is not correct");

        System.out.println(object);
    }

    @Test
    @Order(3)
    @DisplayName("POST - Create an object")
    void createObject() {
        ApiObject apiObject = buildObject("createdObject1", "blue", "250 GB");
        ApiObject newObject = apiObjectService.createObject(apiObject);

        Assertions.assertNotNull(newObject, "Object not found");
        Assertions.assertNotNull(newObject.getId(), "Object id is null");
        Assertions.assertEquals("createdObject1", newObject.getName(), "Object name is not correct");
        Assertions.assertEquals("blue", newObject.getData().getColor(), "Object color is not correct");
        Assertions.assertEquals("250 GB", newObject.getData().getCapacity(), "Object capacity is not correct");

        System.out.println(newObject);
    }

    @Test
    @Order(4)
    @DisplayName("PUT - Update an object")
    void updateObject() {
        ApiObject apiObject = buildObject("createdObject2", "red", "500 GB");
        ApiObject createdObject = apiObjectService.createObject(apiObject);
        String id = createdObject.getId();
        Assertions.assertNotNull(id, "Object id is null");
        apiObject.setName("updatedObject2");

        ApiObject updatedObject = apiObjectService.updateObject(id, apiObject);

        Assertions.assertNotNull(updatedObject, "Object not found");
        Assertions.assertEquals("updatedObject2", updatedObject.getName(), "Object name is not correct");

        System.out.println(updatedObject);
    }

    @Test
    @Order(5)
    @DisplayName("DELETE- Delete an object")
    void deleteObject(){

        ApiObject apiObject = buildObject("objectToDelete", "green", "100 GB");
        ApiObject newObject = apiObjectService.createObject(apiObject);


    Assertions.assertDoesNotThrow(() -> apiObjectService.deleteObject(newObject.getId()));
    }
}