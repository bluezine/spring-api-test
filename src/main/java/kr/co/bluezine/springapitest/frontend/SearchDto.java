package kr.co.bluezine.springapitest.frontend;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
 * 검색 화면 Dto
 */
@Getter
@Setter
public class SearchDto {

    /*
     * 검색 카테고리
     */
    @NotNull(message = "{category.notNull}")
    @NotEmpty(message = "{category.notEmpty}")
    private String category;

    /*
     * 검색명
     */
    @NotNull(message = "{name.notNull")
    @NotEmpty(message = "{name.notEmpty}")
    private String name;
}
