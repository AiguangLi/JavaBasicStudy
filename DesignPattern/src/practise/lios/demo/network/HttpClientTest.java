package practise.lios.demo.network;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liaiguang
 * @date 2020/8/15
 */
public class HttpClientTest {
    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        String url = "http://www.baidu.com/";
        String contentType = "application/x-www-from-urlencoded";
        Map<Object, Object> formData = new HashMap<>(4);
        formData.put("skey", "智慧城科技");
        String result = doPost(url, contentType, formData);

        System.out.println(result);
    }

    public static  String doPost(String url, String contentType, Map<Object, Object> data) throws IOException, URISyntaxException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();

        HttpRequest.BodyPublisher publisher = null;
        if (contentType.startsWith("multipart/form-data")) {
            String boundary = contentType.substring(contentType.lastIndexOf("=") + 1);
            publisher = BodyPublisherWrapper.ofMimeMultipartData(data, boundary);
        } else if ("application/x-www-form-urlencoded".equals(contentType)) {
            publisher = BodyPublisherWrapper.ofFormData(data);
        } else {
            contentType = "application/json";
            publisher = BodyPublisherWrapper.ofSimpleJSON(data);
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", contentType)
                .POST(publisher)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Headers: " + response.headers().map().toString());

        return response.body();
    }
}