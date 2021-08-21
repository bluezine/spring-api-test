package kr.co.bluezine.springapitest.vegetable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
 * 채소가게 API - 가격 Dto
 */
@Setter
@Getter
@ToString
public class VegetablePriceDto {

    /*
     * 채소이름
     */
    @NotNull
    @NotEmpty
    private String name;

    /*
     * 가격
     */
    private int price;
}
