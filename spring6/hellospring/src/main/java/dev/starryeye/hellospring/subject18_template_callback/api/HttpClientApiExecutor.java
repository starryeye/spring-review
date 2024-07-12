package dev.starryeye.hellospring.subject18_template_callback.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientApiExecutor implements ApiExecutor{

    @Override
    public String execute(URI uri) throws IOException {

        /**
         * Java 11 에 추가된 HttpClient 이다.
         * 기존 SimpleApiExecutor 에서 사용하던 Web Api 호출 기술에서 좀더 모던한 스타일이 되었다.
         */

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        // try-with-resources
        try (HttpClient client = HttpClient.newBuilder().build()) {

            return client.send(request, HttpResponse.BodyHandlers.ofString())
                    .body();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
