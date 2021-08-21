package kr.co.bluezine.springapitest.fruit;

import kr.co.bluezine.springapitest.rest.RestApiConnect;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * 과일가게 API - Service
 */
@Service
@RequiredArgsConstructor
public class FruitService {

    /*
     * 토큰 발급
     */
    private static final String TOKEN_URL = "/token";

    /*
     * 과일 목록
     */
    private static final String LIST_URL = "/product";

    /*
     * 과일 가격
     */
    private static final String PRICE_URL = "/product?name=";

    private final RestApiConnect restApiConnect;

    @Value("${api.fruit.api-prefix}")
    private String apiRootUrl;

    /*
     * Access Token을 발급
     */
    public String getAccessToken() {
        String url = apiRootUrl + TOKEN_URL;
        FruitAuthDto authDto = restApiConnect.connectGetMethod(url, FruitAuthDto.class);

        return authDto.getAccessToken();
    }

    /*
     * 과일 목록
     */
    public List<FruitDto> getFruitList() {
        String url = apiRootUrl + LIST_URL;

        List<FruitDto> fruitDtos = new ArrayList<>();

        String accessToken = getAccessToken();
        List<String> fruitArrayBefore = restApiConnect.connectGetMethodWithAccessToken(url, accessToken, List.class);
        fruitArrayBefore.stream().forEach(item -> {
            fruitDtos.add(FruitDto.builder().name(item).build());
        });
        return fruitDtos;
    }

    /*
     * 과일 가격
     */
    public FruitPriceDto getFruitPrice(String name) {
        String url = apiRootUrl + PRICE_URL + name;

        String accessToken = getAccessToken();
        return restApiConnect.connectGetMethodWithAccessToken(url, accessToken, FruitPriceDto.class);
    }
}
