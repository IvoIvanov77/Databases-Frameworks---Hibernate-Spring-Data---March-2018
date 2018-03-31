package app.services;

import app.model.ingredients.BasicIngredient;
import app.model.ingredients.Ingredient;
import app.repositories.IngredientRepository;
import app.services.contracts.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<BasicIngredient> ingredientsByNameStartsWith(String prefix) {
        return this.ingredientRepository.findByNameStartingWith(prefix);
    }

    @Override
    public List<BasicIngredient> ingredientsByNamesInGivenList(String... names) {
        return this.ingredientRepository.findByNameInOrderByPrice(names);
    }

    @Override
    public int deleteIngredientByName(String name) {
        return this.ingredientRepository.deleteBasicIngredientsByName(name);
    }

    @Override
    public int updateIngredientsPrice() {
        return this.ingredientRepository.updateAllPricesWith10Percentage();
    }

    @Override
    public int updateIngredientsByNames(BigDecimal percentage, String... names) {
        return this.ingredientRepository.updatePriceByPercentage(percentage, names);
    }
}
