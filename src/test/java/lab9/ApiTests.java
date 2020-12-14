package lab9;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class ApiTests {
    private final String baseUrl = "http://91.210.252.240:9000/api";

    @Test
    public void shouldGetProducts() {
        RestAssured
                .given()
                .when().get(baseUrl + "/products")
                .then().assertThat()
                .statusCode(200);
    }

    @Test
    public void shouldAddProduct() throws IOException {
        String data = new String(Files.readAllBytes(Paths.get("src/test/java/lab9/product1.json")));

        int id = given()
                .contentType(ContentType.JSON)
                .body(data)
                .when().post(baseUrl + "/addproduct")
                .then().assertThat()
                .statusCode(200)
                .body("status", equalTo(1))
                .body("id", notNullValue())
                .extract()
                .path("id");

        JSONObject body = new JSONObject(data);
        ProductsVerifier.verifyJsonProducts(body, ApiRequests.getProductById(id));

        ApiRequests.deleteProduct(id);
    }

    @Test
    @DisplayName("if a product with given title exists, new product is added with an alias, to which '-0' is added")
    public void shouldChangeAliasIfAddingProductWithExistingTitle() {
        int id1 = ApiRequests.addProduct("src/test/java/lab9/product1.json");
        int id2 = ApiRequests.addProduct("src/test/java/lab9/product1.json");

        JSONObject object1 = ApiRequests.getProductById(id1);
        JSONObject object2 = ApiRequests.getProductById(id2);

        assertNotNull(object1);
        assertNotNull(object2);
        assertEquals(object1.getString("alias") + "-0", object2.getString("alias"), "received incorrect alias");

        ApiRequests.deleteProduct(id1);
        ApiRequests.deleteProduct(id2);
    }

    @Test
    public void shouldNotAddProductWithIncorrectRequiredParameterCategoryId() throws IOException {
        String data = new String(Files.readAllBytes(Paths.get("src/test/java/lab9/product1.json")));
        JSONObject body = new JSONObject(data);
        body.put("category_id", 16);

        int id = given()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when().post(baseUrl + "/addproduct")
                .then()
                .extract()
                .path("id");

        assertNull(ApiRequests.getProductById(id), "there is a product when an incorrect category_id");
    }

    @Test
    public void shouldEditProduct() throws IOException {
        int id = ApiRequests.addProduct("src/test/java/lab9/product1.json");

        String data = new String(Files.readAllBytes(Paths.get("src/test/java/lab9/product2.json")));
        JSONObject body = new JSONObject(data);
        body.put("id", id);

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when().post(baseUrl + "/editproduct")
                .then().assertThat()
                .statusCode(200)
                .body("status", equalTo(1));

        ProductsVerifier.verifyJsonProducts(body, ApiRequests.getProductById(id));

        ApiRequests.deleteProduct(id);
    }

    @Test
    public void shouldDeleteProduct() {
        int id = ApiRequests.addProduct("src/test/java/lab9/product1.json");

        RestAssured
                .given()
                .when().get(baseUrl + "/deleteproduct?id=" + id)
                .then().assertThat()
                .statusCode(200)
                .body("status", equalTo(1));

        assertNull(ApiRequests.getProductById(id), "product with passed id wasn't deleted");
    }
}
