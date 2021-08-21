package kr.co.bluezine.springapitest.vegetable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * 채소가게 API - 인증 Token Dto
 */
@Getter
@Setter
@ToString
public class VegetableAuthDto {

    /*
     * Access Token
     */
    private String accessToken;
}