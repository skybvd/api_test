
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Http_ApiTest {

    @Test
    @Description("Проверка GET запроса к http://httpbin.org")
    public void testGet() {
        RestAssured.baseURI = "http://httpbin.org";
        ValidatableResponse response = null;

        response = given().urlEncodingEnabled(true)
                .param("username", "test1")
                .param("password", "qaz32132qw")
                .when()
                .get("/get?serv=2")
                .then()
                .statusCode(200)
                .body("url", equalTo("https://httpbin.org/get?username=test1&password=qaz32132qw&serv=2"));

        String responseBody = response.extract().asString();
        System.out.println("RESPONSE: " + responseBody);
    }

    @Test
    @Description("Проверка POST запроса к http://httpbin.org")
    public void testPOST() {

        Map<String, String> data = new HashMap();
        data.put("id", "Vasya");

        RestAssured.baseURI = "http://httpbin.org";

        ValidatableResponse response =  given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("json.id", equalTo("Vasya"));

        String responseBody = response.extract().asString();
        System.out.println("RESPONSE: " + responseBody);
    }

    @Test
    @Description("Проверка PUT запроса к http://httpbin.org")
    public void testPUT() {
        RestAssured.baseURI = "http://httpbin.org";
        Map<String, String> data = new HashMap();
        data.put("id", "Michail");
        ValidatableResponse response = null;

        response = given()
//                .pathParam("id", "Vasya")
                .contentType("application/json")
                .body(data)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("json.id", equalTo("Michail"));

        String responseBody = response.extract().asString();
        System.out.println("RESPONSE: " + responseBody);
    }

    @Test
    @Description("Проверка DELETE запроса к http://httpbin.org")
    public void testDELETE() {

        RestAssured.baseURI = "http://httpbin.org";

        ValidatableResponse response = null;

        response = given()
                .contentType("application/json")
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("json", equalTo(null));

        String responseBody = response.extract().asString();
        System.out.println("RESPONSE: " + responseBody);
    }
}
