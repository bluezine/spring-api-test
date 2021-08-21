package kr.co.bluezine.springapitest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/*
 * Context Configuration
 */
@Configuration
public class WebApplicationConfig {

    /*
     * 연결대기 timeout
     */
    @Value("${api.connect.connect-timeout}")
    private Long connectTimeout;

    /*
     * 연결 후 timeout
     */
    @Value("${api.connect.read-timeout}")
    private Long readTimeout;

    /*
     * 외부 API 통신에 사용될 rest template
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.setConnectTimeout(Duration.ofMillis(connectTimeout)).setReadTimeout(Duration.ofMillis(readTimeout)).build();
    }
}