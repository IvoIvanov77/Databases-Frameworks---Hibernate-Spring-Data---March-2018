package app.controllers;

import app.model.ingredients.BasicIngredient;
import app.services.contracts.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    public String getIngredientsWithNameStartsWith(String prefix){
        List<BasicIngredient> resultList = this.ingredientService.ingredientsByNameStartsWith(prefix);
        return this.getFormattedIngredients(resultList);
    }

    public String ingredientsWithNameContainedIn(String... names){
        List<BasicIngredient> resultList = this.ingredientService.ingredientsByNamesInGivenList(names);
        return this.getFormattedIngredients(resultList);
    }

    public int deleteIngredientsByName(String name){
        return this.ingredientService.deleteIngredientByName(name);
    }

    public int updateAllIngredientsPrice(){
        return this.ingredientService.updateIngredientsPrice();
    }

    public int updateIngredientsPriceWithPercentageByNames(String input, String... names){
        BigDecimal percentage = new BigDecimal(input);
        return this.ingredientService.updateIngredientsByNames(percentage, names);
    }


    private String getFormattedIngredients(List<BasicIngredient> resultList) {
        return String.join(System.lineSeparator(), resultList.stream()
                .map(BasicIngredient::getName).collect(Collectors.toList()));
    }


}
