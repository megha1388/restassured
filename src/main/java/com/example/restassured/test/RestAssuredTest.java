package com.example.restassured.test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class RestAssuredTest extends TestReportConfig {

    @Test
    public void restApiTest() {
        try {
            Response response = get("http://localhost:8080/restAPI/person/people");
            Assert.assertEquals(response.getStatusCode(), 200);
            System.out.println(response.toString());
//            response.then().body("firstName", hasItems("Dayaan", " Diyaan"));
//            response.then().body("lastName", hasItems("patel"));
        } catch (Exception e) {
            Assert.assertEquals(0, 1);
            e.printStackTrace();
        }
    }

    @Test
    public void restApiPostTest(){
        try {
            Response response = given().contentType(ContentType.JSON)
                    .accept(ContentType.JSON).body(" {\n" +
                            "        \"firstName\": \"Diyaan\",\n" +
                            "        \"lastName\": \"Patel\",\n" +
                            "        \"age\": 1,\n" +
                            "        \"gender\": \"male\",\n" +
                            "        \"address\": \"chicago\"\n" +
                            "    }").when().post("http://localhost:8080/restAPI/person/people");
            Assert.assertEquals(response.getStatusCode(),200);
            System.out.println(response.body().asString());

        } catch (Exception e) {
            Assert.assertEquals(0, 1);
            e.printStackTrace();
        }
    }
    @Test
    public void restApiCountPractiseTest() {
        try {
            Response response = get("http://localhost:8080/restAPI/person/people");
            Assert.assertEquals(response.getStatusCode(), 200);
            System.out.println(response.body().asString());
            System.out.println(response.jsonPath().getList("firstName").size());
//            response.then().body("firstName",hasItems("Dayaan"," Diyaan"));
        } catch (Exception e) {
            Assert.assertEquals(0, 1);
            e.printStackTrace();
        }
    }
}
