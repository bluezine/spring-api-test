package kr.co.bluezine.springapitest.fruit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
 * 과일가게 API - Service Test
 */
@SpringBootTest
class FruitServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(FruitServiceTest.class);

    @Autowired
    private FruitService fruitService;

    /*
     * Access Token을 발급
     */
    @Test
    void getAccessToken() {
        String accessToken = fruitService.getAccessToken();
        logger.info("Fruit Access Token ::: " + accessToken);
        Assertions.assertNotNull(accessToken, "엑세스 토큰 값 없음");
    }
}