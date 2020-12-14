package lab9;

import org.json.JSONObject;

import static org.junit.Assert.assertEquals;

public class ProductsVerifier {
    public static void verifyJsonProducts(JSONObject object1, JSONObject object2) {
        if (object1.has("id") && object2.has("id"))
        {
            assertEquals(object1.getInt("id"), object2.getInt("id"));
        }

        assertEquals(object1.getInt("category_id"), object2.getInt("category_id"));
        assertEquals(object1.getString("title"), object2.getString("title"));

        if (object1.has("alias") && object2.has("alias")) {
            assertEquals(object1.getString("alias"), object2.getString("alias"));
        }

        if (object1.has("content") && object2.has("content")) {
            assertEquals(object1.getString("content"), object2.getString("content"));
        }

        assertEquals(object1.getInt("price"), object2.getInt("price"));
        assertEquals(object1.getInt("old_price"), object2.getInt("old_price"));

        if (object1.has("status") && object2.has("status")) {
            assertEquals(object1.getInt("status"), object2.getInt("status"));
        }

        if (object1.has("keywords") && object2.has("keywords")) {
            assertEquals(object1.getString("keywords"), object2.getString("keywords"));
        }

        if (object1.has("description") && object2.has("description")) {
            assertEquals(object1.getString("description"), object2.getString("description"));
        }

        if (object1.has("img") && object2.has("img")) {
            assertEquals(object1.getString("img"), object2.getString("img"));
        }

        if (object1.has("hit") && object2.has("hit")) {
            assertEquals(object1.getInt("hit"), object2.getInt("hit"));
        }

        if (object1.has("cat") && object2.has("cat"))
        {
            assertEquals(object1.getInt("cat"), object2.getInt("cat"));
        }
    }
}
