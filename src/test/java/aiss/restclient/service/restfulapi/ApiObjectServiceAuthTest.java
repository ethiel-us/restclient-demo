package aiss.restclient.service.restfulapi;

import aiss.restclient.model.restfulapi.ApiObject;
import aiss.restclient.model.restfulapi.Data;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApiObjectServiceAuthTest {

    @Autowired
    ApiObjectServiceAuth apiObjectServiceAuth;

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
    @DisplayName("POST - Create an object")
    void createObjectCollection() {
        ApiObject apiObject = buildObject("createdObject1", "blue", "250 GB");
        ApiObject newObject = apiObjectServiceAuth.createObjectCollection( apiObject, "AISS-LAB");

        Assertions.assertNotNull(newObject, "Object not found");
        Assertions.assertNotNull(newObject.getId(), "Object id is null");
        Assertions.assertEquals("createdObject1", newObject.getName(), "Object name is not correct");
        Assertions.assertEquals("blue", newObject.getData().getColor(), "Object color is not correct");
        Assertions.assertEquals("250 GB", newObject.getData().getCapacity(), "Object capacity is not correct");

        System.out.println(newObject);
    }

    @Test
    @Order(2)
    @DisplayName("PUT - Update an object")
    void updateObjectCollection() {
        ApiObject apiObject = buildObject("createdObject2", "red", "500 GB");
        ApiObject createdObject = apiObjectServiceAuth.createObjectCollection( apiObject, "AISS-LAB");
        String id = createdObject.getId();
        Assertions.assertNotNull(id, "Object id is null");
        apiObject.setName("updatedObject2");

        ApiObject updatedObject = apiObjectServiceAuth.updateObjectCollection(id, "AISS-LAB", apiObject);

        Assertions.assertNotNull(updatedObject, "Object not found");
        Assertions.assertEquals("updatedObject2", updatedObject.getName(), "Object name is not correct");

        System.out.println(updatedObject);
    }

    @Test
    @Order(3)
    @DisplayName("GET - Get all objects")
    void getAllObjectsCollection() {
        List<ApiObject> objectList = apiObjectServiceAuth.getAllObjectsCollection("AISS-LAB");

        Assertions.assertNotNull(objectList, "Object list is null");
        Assertions.assertFalse(objectList.isEmpty(), "Object list is empty");

        System.out.println(objectList);
    }

    @Test
    @Order(4)
    @DisplayName("GET - Get object by id")
    void getObjectByIdCollection() {
        ApiObject apiObject = buildObject("createdObject3", "red", "500 GB");
        ApiObject createdObject = apiObjectServiceAuth.createObjectCollection( apiObject, "AISS-LAB");

        ApiObject object = apiObjectServiceAuth.getObjectByIdCollection(createdObject.getId(), "AISS-LAB");

        Assertions.assertNotNull(object, "Object is null");
        Assertions.assertEquals(createdObject.getId(), object.getId(), "Object id is not correct");

        System.out.println(object);
    }

    @Test
    @Order(5)
    @DisplayName("DELETE- Delete an object")
    void deleteObjectCollection() {
        ApiObject apiObject = buildObject("createdObject4", "red", "500 GB");
        ApiObject createdObject = apiObjectServiceAuth.createObjectCollection( apiObject, "AISS-LAB");

        Assertions.assertDoesNotThrow(
                () -> apiObjectServiceAuth.deleteObjectCollection(createdObject.getId(), "AISS-LAB")
        );
    }
}