package lab9;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ApiRequests {
    private static final String baseUrl = "http://91.210.252.240:9000/api";

    public static JSONArray getProducts() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when().post(baseUrl + "/products")
                .then()
                .extract()
                .response();

        return new JSONArray(response.body().asString());
    }

    public static JSONObject getProductById(int id) {
        JSONArray JSONResponseBody = getProducts();
        for (int i = JSONResponseBody.length() - 1; i >= 0; i--)
        {
            JSONObject object = JSONResponseBody.getJSONObject(i);
            if (object.getInt("id") == id)
            {
                return object;
            }
            else if ((object.getInt("id") < id))
            {
                break;
            }
        }
        return null;
    }

    public static int addProduct(String jsonProductPath) {
        File file = new File(jsonProductPath);
        assertNotNull(file);

        return given()
                .contentType(ContentType.JSON)
                .body(file)
                .when().post(baseUrl + "/addproduct")
                .then()
                .extract()
                .path("id");
    }

    public static void deleteProduct(int id) {
        RestAssured
                .given()
                .when().get(baseUrl + "/deleteproduct?id=" + id);
    }
}
