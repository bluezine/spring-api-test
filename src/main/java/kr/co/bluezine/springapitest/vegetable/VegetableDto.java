package kr.co.bluezine.springapitest.vegetable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * 채소가게 API - 목록 Dto
 * 목록은 단순 StringArray 이기 때문에 List 로 처리한다.
 */
@Setter
@Getter
@Builder
@ToString
public class VegetableDto {

    /*
     * 채소 이름
     */
    private String name;
}