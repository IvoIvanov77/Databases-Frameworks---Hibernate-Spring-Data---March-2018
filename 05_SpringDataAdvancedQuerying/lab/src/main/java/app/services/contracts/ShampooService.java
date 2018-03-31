package app.services.contracts;

import app.enums.Size;
import app.model.shampoos.BasicShampoo;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {

    /**
     * selects all shampoos by size type, ordered by shampoo id.
     * @param size size type(Enum)
     * @return List of shampoos
     */
    List<BasicShampoo> selectShampoosBySize(Size size);

    /**
     * select shampoos by Size or Label ordered ascending by price.
     * @param size size type(Enum)
     * @param id Long
     * @return List of shampoos
     */
    List<BasicShampoo>	selectShampoosBySizeOrLabel(Size size, Long id);


    /**
     * selects all shampoos  with price higher than a given price,
     * ordered <strong>descending</strong> by price.
     * @param price BigDecimal price
     * @return List of shampoos
     */
    List<BasicShampoo>	selectShampoosByPrice(BigDecimal price);


    /**
     * counts all shampoos with price lower than a given price
     * @param price BigDecimal price
     * @return count of shampoos with lower price
     */
    Integer	countShampoosByPrice(BigDecimal price);

    /**
     * counts all shampoos with price lower than a given price
     * @param ingredientsNames varargs of String - names of ingredients
     * @return List of shampoos
     */
    List<BasicShampoo> selectShampoosByIngredients(String... ingredientsNames);


    /**
     * counts all shampoos with price lower than a given price
     * @param count count of ingredients
     * @return List of shampoos
     */
    List<BasicShampoo>	selectShampoosByIngredientsLessThan(int count);

    /**
     * get ingredients prices by shampoo name
     * @param name shampoo's name
     * @return BigDecimal - total price of ingredients
     */
    BigDecimal selectIngredientsPriceByShampooName(String name);


}
