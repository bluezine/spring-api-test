package kr.co.bluezine.springapitest.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
}
