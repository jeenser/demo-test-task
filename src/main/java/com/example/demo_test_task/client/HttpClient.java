package com.example.demo_test_task.client;

import com.example.demo_test_task.handlers.ErrorHandler;
import com.example.demo_test_task.interceptors.LoggerInterceptor;
import com.example.demo_test_task.utils.ParameterizedTypeReferenceBuilder;
import com.google.common.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class HttpClient {

    private final String uri = "https://fakerestapi.azurewebsites.net/api/v1";

    private RestClient restClient() {
        return RestClient.builder()
                .baseUrl(uri)
                .requestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .requestInterceptor(new LoggerInterceptor())
                .defaultStatusHandler(new ErrorHandler())
                .build();
    }

    public <T> ResponseEntity<T> execute(SystemApi api, Object body, Class<T> responseType, Object... parameters) {
        return switch (api.getMethod().toString()) {
            case "GET" -> get(api.getEndpoint(), responseType, parameters);
            case "POST" -> post(api.getEndpoint(), body, responseType, parameters);
            case "PUT" -> put(api.getEndpoint(), body, responseType, parameters);
            case "DELETE" -> delete(api.getEndpoint(), responseType, parameters);
            default -> throw new IllegalArgumentException("Only GET, POST, PUT, DELETE supported");
        };
    }

    public <T> ResponseEntity<T> execute(SystemApi api, Class<T> responseType, Object... parameters) {
        return this.execute(api, null, responseType, parameters);
    }

    public <T> ResponseEntity<T> execute(SystemApi api, Object body, Class<T> responseType) {
        return this.execute(api, body, responseType, (Object) null);
    }

    public <T> ResponseEntity<T> execute(SystemApi api, Class<T> responseType) {
        return this.execute(api, (Object) null, responseType, (Object) null);
    }

    public <T> ResponseEntity<T> execute(SystemApi api, TypeToken<T> resultTypeToken, Object... parameters) {
        ParameterizedTypeReference<T> responseTypeRef =
                ParameterizedTypeReferenceBuilder.fromTypeToken(resultTypeToken);

        return restClient().method(api.getMethod())
                .uri(api.getEndpoint(), parameters)
                .retrieve()
                .toEntity(responseTypeRef);
    }

    public <T> ResponseEntity<T> execute(SystemApi api, TypeToken<T> resultTypeToken) {
        return this.execute(api, resultTypeToken, (Object) null);
    }

    private <T> ResponseEntity<T> get(String endpoint, Class<T> responseType, Object... parameters) {
        return restClient().get()
                .uri(endpoint, parameters)
                .retrieve()
                .toEntity(responseType);
    }

    private <T> ResponseEntity<T> post(String endpoint, Object body, Class<T> responseType, Object... parameters) {
        return restClient().post()
                .uri(endpoint, parameters)
                .body(body)
                .retrieve()
                .toEntity(responseType);
    }

    private <T> ResponseEntity<T> put(String endpoint, Object body, Class<T> responseType, Object... parameters) {
        return restClient().put()
                .uri(endpoint, parameters)
                .body(body)
                .retrieve()
                .toEntity(responseType);
    }

    private <T> ResponseEntity<T> delete(String endpoint, Class<T> responseType, Object... parameters) {
        return restClient().delete()
                .uri(endpoint, parameters)
                .retrieve()
                .toEntity(responseType);
    }
}
