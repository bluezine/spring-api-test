package kr.co.bluezine.springapitest.fruit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

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

    /*
     * 과일 목록
     */
    @Test
    void getFruitList() {
        List<FruitDto> fruitDtoList = fruitService.getFruitList();
        Assertions.assertNotNull(fruitDtoList, "과일 목록 없음");

        fruitDtoList.stream().forEach(item -> {
            logger.info(String.valueOf(item));
        });

        Assertions.assertTrue(fruitDtoList.size() > 0, "과일 목록이 존재하지 않음");
    }

    /*
     * 과일 가격
     */
    @Test
    void getFruitPrice() throws Exception {
        Assertions.assertThrows(HttpClientErrorException.NotFound.class, () -> {
            fruitService.getFruitPrice("존재안함");
        }, "과일이 존재하지 않음.");

        FruitPriceDto priceDto = fruitService.getFruitPrice("사과");
        Assertions.assertTrue(priceDto.getPrice() > 0, "가격이 존재하지 않음");
    }
}