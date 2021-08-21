package kr.co.bluezine.springapitest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
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

    /*
     * 메세지 공통사용을 위한 Bean
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /*
     * View 단 Valid 메시지를 위한 Bean
     */
    @Bean
    public LocalValidatorFactoryBean getValidator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }
}