package kr.co.bluezine.springapitest.frontend;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
 * 카테고리
 */
@AllArgsConstructor
@Getter
public enum CategoryType {
    FRUIT("과일"),
    VEGETABLE("채소");

    private String name;
}
