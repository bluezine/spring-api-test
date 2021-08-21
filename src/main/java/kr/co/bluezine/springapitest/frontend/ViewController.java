package kr.co.bluezine.springapitest.frontend;

import kr.co.bluezine.springapitest.fruit.FruitPriceDto;
import kr.co.bluezine.springapitest.fruit.FruitService;
import kr.co.bluezine.springapitest.vegetable.VegetablePriceDto;
import kr.co.bluezine.springapitest.vegetable.VegetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/*
 * View Controller
 */
@Controller
@RequiredArgsConstructor
public class ViewController {

    private final MessageSource messageSource;
    private final FruitService fruitService;
    private final VegetableService vegetableService;

    /*
     * 카테고리 정보 (고정)
     */
    private static List<String> categories = new ArrayList<>();

    static {
        for (CategoryType category : CategoryType.values()) {
            categories.add(category.getName());
        }
    }

    /*
     * 메인 페이지
     */
    @GetMapping(value = {"/", "index"})
    public ModelAndView index(Model model) {
        List<String> categories = new ArrayList<>();
        for (CategoryType category : CategoryType.values()) {
            categories.add(category.getName());
        }

        model.addAttribute("searchDto", new SearchDto());
        model.addAttribute("categories", categories);
        return new ModelAndView("index");
    }

    /*
     * 검색 결과 페이지
     */
    @PostMapping(value = "search")
    public ModelAndView search(Model model, @ModelAttribute @Valid SearchDto searchDto, BindingResult bindingResult) {
        model.addAttribute("searchDto", searchDto);
        model.addAttribute("categories", categories);

        if (bindingResult.hasFieldErrors())
            return new ModelAndView("index");

        ResultDto resultDto = null;
        try {
            if (searchDto.getCategory().equals(CategoryType.FRUIT.getName())) {
                FruitPriceDto fruitPriceDto = fruitService.getFruitPrice(searchDto.getName());
                resultDto = ResultDto.builder().category(searchDto.getCategory()).name(fruitPriceDto.getName()).price(fruitPriceDto.getPrice()).build();
            } else if (searchDto.getCategory().equals(CategoryType.VEGETABLE.getName())) {
                VegetablePriceDto vegetablePriceDto = vegetableService.getVegetablePrice(searchDto.getName());
                resultDto = ResultDto.builder().category(searchDto.getCategory()).name(vegetablePriceDto.getName()).price(vegetablePriceDto.getPrice()).build();
            }
        } catch (HttpClientErrorException.NotFound ex) {
            model.addAttribute("error", messageSource.getMessage("name.notExist", new Object[]{searchDto.getName(), searchDto.getCategory()}, null));
        }
        model.addAttribute("resultDto", resultDto);
        return new ModelAndView("index");
    }
}
