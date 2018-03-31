package app.controllers;

import app.enums.Size;
import app.model.shampoos.BasicShampoo;
import app.model.shampoos.Shampoo;
import app.services.contracts.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ShampooController {

    @Autowired
    private ShampooService shampooService;

    public String shampoosBySizeToString(Size size){
        List<BasicShampoo> resultList = this.shampooService.selectShampoosBySize(size);
        return this.getFormattedShampoos(resultList);
    }

    public String shampoosByBySizeOrLabel(Size size, Long id){
        List<BasicShampoo> resultList = this.shampooService.selectShampoosBySizeOrLabel(size, id);
        return this.getFormattedShampoos(resultList);
    }

    public String selectShampoosByPrice(String input){
        List<BasicShampoo> resultList = this.shampooService.selectShampoosByPrice(new BigDecimal(input));
        return this.getFormattedShampoos(resultList);
    }

    public int countOfShampooWithPriceLowerThan(String input){
        return this.shampooService.countShampoosByPrice(new BigDecimal(input));
    }

    public String shampoosByIngredientIn(String... names){
        List<BasicShampoo> resultList = this.shampooService.selectShampoosByIngredients(names);
        return this.getFormattedShampoos(resultList);
    }

    public String shampoosByIngredientsLessThan(int count){
        List<BasicShampoo> resultList = this.shampooService.selectShampoosByIngredientsLessThan(count);
        return this.getFormattedShampoos(resultList);
    }

    public BigDecimal getIngredientsTotalPriceByShampooName(String name){
        return this.shampooService.selectIngredientsPriceByShampooName(name);
    }

    private String getFormattedShampoos(List<BasicShampoo> resultList) {
        StringBuilder builder = new StringBuilder();
        for (Shampoo shampoo : resultList ) {
            builder.append(String.format("%s %s %slv.",
                    shampoo.getBrand(),
                    shampoo.getSize(),
                    shampoo.getPrice()))
                    .append(System.lineSeparator());
        }
        return builder.toString().trim();
    }


}
