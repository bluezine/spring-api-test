package kr.co.bluezine.springapitest.vegetable;

import kr.co.bluezine.springapitest.rest.RestApiConnect;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * 채소가게 API - Service
 */
@Service
@RequiredArgsConstructor
public class VegetableService {

    /*
     * 토큰 발급
     */
    private static final String TOKEN_URL = "/token";

    /*
     * 채소 목록
     */
    private static final String LIST_URL = "/item";

    /*
     * 채소 가격
     */
    private static final String PRICE_URL = "/item?name=";

    private final RestApiConnect restApiConnect;

    @Value("${api.vegetable.api-prefix}")
    private String apiRootUrl;

    /*
     * Access Token을 발급
     */
    public String getAccessToken() {
        String url = apiRootUrl + TOKEN_URL;
        Map<String, String> cookies = restApiConnect.connectGetMethodCookieValues(url);
        if (cookies.containsKey("Authorization"))
            return cookies.get("Authorization");
        return null;
    }

    /*
     * 채소 목록
     */
    public List<VegetableDto> getVegetableList() {
        String url = apiRootUrl + LIST_URL;

        List<VegetableDto> vegetableDtos = new ArrayList<>();

        String accessToken = getAccessToken();
        List<String> vegetableArrayBefore = restApiConnect.connectGetMethodWithAccessToken(url, accessToken, List.class);
        vegetableArrayBefore.stream().forEach(item -> {
            vegetableDtos.add(VegetableDto.builder().name(item).build());
        });
        return vegetableDtos;
    }

    /*
     * 채소 가격
     */
    public VegetablePriceDto getVegetablePrice(String name) throws Exception {
        if (StringUtils.isEmpty(name)) {
            throw new Exception();
        }
        String url = apiRootUrl + PRICE_URL + name;

        String accessToken = getAccessToken();
        return restApiConnect.connectGetMethodWithAccessToken(url, accessToken, VegetablePriceDto.class);
    }
}
