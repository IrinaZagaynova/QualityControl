package lab8;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.io.IOException;


class RateMountebankTest {
    static CloseableHttpClient httpClient = HttpClients.createDefault();

    String getResponseBody(String url) {
        HttpResponse response = null;
        try {
            response = httpClient.execute(new HttpGet(url));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        assert response != null;
        HttpEntity entity = response.getEntity();

        String responseBody = null;
        try {
            responseBody = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return responseBody;
    }

    @Test
    public void shouldReturnEuroRate() {
        String expected = "{\n" +
                "    \"euro-rate\": \"91,02\"\n" +
                "}";
        assertEquals(expected, getResponseBody("http://localhost:4545/euro-rate"));
    }

    @Test
    public void shouldReturnUSDollarRate() {
        String expected = "{\n" +
                "    \"us-dollar-rate\": \"76,08\"\n" +
                "}";
        assertEquals(expected, getResponseBody("http://localhost:4545/us-dollar-rate"));
    }

    @Test
    public void shouldReturnPoundRate() {
        String expected = "{\n" +
                "    \"pound-rate\": \"101,23\"\n" +
                "}";
        assertEquals(expected, getResponseBody("http://localhost:4545/pound-rate"));
    }
}