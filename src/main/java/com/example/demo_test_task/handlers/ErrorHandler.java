package com.example.demo_test_task.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;

@Slf4j
public class ErrorHandler implements ResponseErrorHandler  {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {

        log.error("\n\n**************** ERROR MESSAGE - START ****************");
        log.error("\nStatus Code  : " + response.getStatusCode());
        log.error("\nError Message: " + StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
        log.error("\n**************** ERROR MESSAGE - END ****************");
    }
}
