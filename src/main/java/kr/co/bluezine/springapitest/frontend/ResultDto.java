package kr.co.bluezine.springapitest.frontend;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/*
 * 검색 결과에 사용되는 Dto
 */
@Getter
@Setter
@Builder
public class ResultDto {

    /*
     * 검색 결과가 속해있는 카테고리
     */
    private String category;

    /*
     * 검색 결과의 항목 이름
     */
    private String name;

    /*
     * 검색 결과의 항목 가격
     */
    private int price;
}
