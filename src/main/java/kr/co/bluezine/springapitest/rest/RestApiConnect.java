package kr.co.bluezine.springapitest.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * Rest 요청 Service
 */
@Service
@RequiredArgsConstructor
public class RestApiConnect {

    private static final Logger logger = LoggerFactory.getLogger(RestApiConnect.class);

    private final RestTemplate restTemplate;

    /*
     * 단순 GET 요청을 가져온다.
     */
    public <T> T connectGetMethod(String url, Class<T> t) {
        logger.debug("Connect Url ::: " + url);
        return restTemplate.getForObject(url, t);
    }

    /*
     * 단순 GET 요청을 가져온다. (액세스 토큰을 전달한다.)
     */
    public <T> T connectGetMethodWithAccessToken(String url, String accessToken, Class<T> t) {
        logger.debug("Connect Url ::: " + url);

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", accessToken);

        return connectGetMethodWithHeader(url, headers, t);
    }

    /*
     * 단순 GET 요청을 가져온다.
     * 헤더를 추가한다.
     */
    public <T> T connectGetMethodWithHeader(String url, Map<String, String> headers, Class<T> t) {
        logger.debug("Connect Url ::: " + url);

        HttpHeaders header = new HttpHeaders();
        for (Map.Entry<String, String> headersEntry : headers.entrySet()) {
            header.put(headersEntry.getKey(), Arrays.stream(new String[]{headersEntry.getValue()}).collect(Collectors.toList()));
        }
        HttpEntity<String> request = new HttpEntity<>(header);

        return restTemplate.exchange(url, HttpMethod.GET, request, t).getBody();
    }
}
