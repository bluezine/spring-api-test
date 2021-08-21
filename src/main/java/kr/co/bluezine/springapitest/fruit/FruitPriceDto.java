package kr.co.bluezine.springapitest.fruit;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
 * 과일가게 API - 가격 Dto
 */
@Setter
@Getter
@ToString
public class FruitPriceDto {

    /*
     * 과일이름
     */
    @NotNull
    @NotEmpty
    private String name;

    /*
     * 가격
     */
    private int price;
}
