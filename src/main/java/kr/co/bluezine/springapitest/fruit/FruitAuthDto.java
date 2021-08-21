package kr.co.bluezine.springapitest.fruit;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * 과일가게 API - 인증 Token Dto
 */
@Getter
@Setter
@ToString
public class FruitAuthDto {

    /*
     * Access Token
     */
    private String accessToken;
}