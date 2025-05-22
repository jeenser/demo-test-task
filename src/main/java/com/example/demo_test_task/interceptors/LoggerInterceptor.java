package com.example.demo_test_task.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
public class LoggerInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) {

        log.info("\n\n=========================== REQUEST START =============================================");
        log.info("\nURI         : {}", request.getURI());
        log.info("\nMethod      : {}", request.getMethod());
//            log.info("\nHeaders     : {}", request.getHeaders());
        log.info("\nRequest body: {}", new String(body, StandardCharsets.UTF_8));
        log.info("\n============================ REQUEST END ================================================");
    }

    private void logResponse(ClientHttpResponse response) throws IOException {

        log.info("\n\n=========================== RESPONSE START ==========================================");
        log.info("\nStatus code  : {}", response.getStatusCode());
        log.info("\nStatus text  : {}", response.getStatusText());
//            log.info("\nHeaders      : {}", response.getHeaders());
        log.info("\nResponse body: {}", StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
        log.info("\n============================ RESPONSE END =================================================");
    }
}
