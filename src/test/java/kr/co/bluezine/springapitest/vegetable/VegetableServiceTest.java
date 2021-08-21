package kr.co.bluezine.springapitest.vegetable;

import kr.co.bluezine.springapitest.vegetable.VegetableDto;
import kr.co.bluezine.springapitest.vegetable.VegetablePriceDto;
import kr.co.bluezine.springapitest.vegetable.VegetableService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

/*
 * 채소가게 API - Service Test
 */
@SpringBootTest
class VegetableServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(VegetableServiceTest.class);

    @Autowired
    private VegetableService vegetableService;

    /*
     * Access Token을 발급
     */
    @Test
    void getAccessToken() {
        String accessToken = vegetableService.getAccessToken();
        logger.info("Vegetable Access Token ::: " + accessToken);
        Assertions.assertNotNull(accessToken, "엑세스 토큰 값 없음");
    }

    /*
     * 채소 목록
     */
    @Test
    void getVegetableList() {
        List<VegetableDto> vegetableDtoList = vegetableService.getVegetableList();
        Assertions.assertNotNull(vegetableDtoList, "채소 목록 없음");

        vegetableDtoList.stream().forEach(item -> {
            logger.info(String.valueOf(item));
        });

        Assertions.assertTrue(vegetableDtoList.size() > 0, "채소 목록이 존재하지 않음");
    }

    /*
     * 채소 가격
     */
    @Test
    void getVegetablePrice() throws Exception {
        Assertions.assertThrows(HttpClientErrorException.NotFound.class, () -> {
            vegetableService.getVegetablePrice("존재안함");
        }, "채소이 존재하지 않음.");

        VegetablePriceDto priceDto = vegetableService.getVegetablePrice("치커리");
        Assertions.assertTrue(priceDto.getPrice() > 0, "가격이 존재하지 않음");
    }
}