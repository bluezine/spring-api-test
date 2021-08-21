package kr.co.bluezine.springapitest.fruit;

import kr.co.bluezine.springapitest.rest.RestApiConnect;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
