package app.services.contracts;

import app.model.ingredients.BasicIngredient;
import app.model.ingredients.Ingredient;

import java.math.BigDecimal;
import java.util.List;

public interface IngredientService {

    /**
     * selects all ingredients which name starts with given letters.
     * @param prefix String prefix
     * @return List of ingredients
     */
    List<BasicIngredient> ingredientsByNameStartsWith(String prefix);


    /**
     * selects all ingredients which are contained in a given list, ordered ascending by price.
     * @param names String varargs - list of names
     * @return List of ingredients
     */
    List<BasicIngredient> ingredientsByNamesInGivenList(String ... names);

    /**
     * delete ingredient by a given name.
     * @param name String varargs - list of names
     * @return integer - count of deletes ingredients
     */
    int deleteIngredientByName(String name);


    /**
     * increases the price of all ingredients by 10%.
     * @return integer - count of affected ingredients
     */
    int updateIngredientsPrice();

    /**
     * increases the price of all ingredients with name in given list
     * by given percentage.
     * @param percentage BigDecimal - increasing percentage
     * @param names varargs - list of ingredient names
     * @return integer - count of affected ingredients
     */
    int	updateIngredientsByNames(BigDecimal percentage, String...names);
}
