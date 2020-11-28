package lab8;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Imposter {
    static CloseableHttpClient httpClient = HttpClients.createDefault();
    static HttpPost request = new HttpPost("http://localhost:2525/imposters");

    public static void main(String[] args) {
        try {
            StringEntity params =new StringEntity("{\n" +
                    "    \"port\": 4545,\n" +
                    "    \"protocol\": \"http\",\n" +
                    "    \"stubs\" : [{\n" +
                    "        \"predicates\": [{\n" +
                    "            \"and\": [{\n" +
                    "                \"equals\": {\n" +
                    "                    \"path\": \"/euro-rate\",\n" +
                    "                    \"method\": \"GET\"\n" +
                    "                }\n" +
                    "            }\n" +
                    "            ]\n" +
                    "        }],\n" +
                    "        \"responses\": [\n" +
                    "            { \"is\": {\"body\":{\"euro-rate\": \"91,02\"}}}\n" +
                    "        ]\n" +
                    "    },\n" +
                    "        {\n" +
                    "            \"predicates\": [{\n" +
                    "                \"and\": [{\n" +
                    "                    \"equals\": {\n" +
                    "                        \"path\": \"/us-dollar-rate\",\n" +
                    "                        \"method\": \"GET\"\n" +
                    "                    }\n" +
                    "                }\n" +
                    "                ]\n" +
                    "            }],\n" +
                    "            \"responses\": [\n" +
                    "                { \"is\": {\"body\":{\"us-dollar-rate\": \"76,08\"}}}\n" +
                    "            ]\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"predicates\": [{\n" +
                    "                \"and\": [{\n" +
                    "                    \"equals\": {\n" +
                    "                        \"path\": \"/pound-rate\",\n" +
                    "                        \"method\": \"GET\"\n" +
                    "                    }\n" +
                    "                }\n" +
                    "                ]\n" +
                    "            }],\n" +
                    "            \"responses\": [\n" +
                    "                { \"is\": {\"body\":{\"pound-rate\": \"101,23\"}}}\n" +
                    "            ]\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"responses\": [\n" +
                    "                {\"is\": {\"statusCode\": 400}}\n" +
                    "            ]\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}\n");

            request.addHeader("content-type", "application/json");
            request.addHeader("Accept","application/json");
            request.setEntity(params);

            httpClient.execute(request);
        }
        catch (Exception ignored) {
        }
    }
}
