package app.terminal;


import app.controllers.IngredientController;
import app.controllers.ShampooController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ShampooController shampooController;
    private final IngredientController ingredientController;

    @Autowired
    public ConsoleRunner(ShampooController shampooController, IngredientController ingredientController) {
        this.shampooController = shampooController;
        this.ingredientController = ingredientController;
    }

    @Override
    public void run(String... strings) throws Exception {
//        System.out.println(this.shampooController.shampoosBySizeToString(Size.MEDIUM));
//        System.out.println(this.shampooController.shampoosByBySizeOrLabel(Size.MEDIUM, 10L));
//        System.out.println(this.shampooController.selectShampoosByPrice("5"));
//        System.out.println(this.shampooController.countOfShampooWithPriceLowerThan("8.50"));
//        System.out.println(this.shampooController.shampoosByIngredientIn("Berry", "Mineral-Colagen"));
//        System.out.println(this.shampooController.shampoosByIngredientsLessThan(2));
//        System.out.println(this.shampooController.getIngredientsTotalPriceByShampooName("Fresh it up!"));
//        System.out.println(this.ingredientController.getIngredientsWithNameStartsWith("M"));
//        System.out.println(this.ingredientController
//                .ingredientsWithNameContainedIn("Lavender", "Herbs", "Apple"));
//        System.out.println(this.ingredientController.deleteIngredientsByName("Apple"));
//        System.out.println(this.ingredientController.updateAllIngredientsPrice());
        System.out.println(this.ingredientController
                .updateIngredientsPriceWithPercentageByNames("20", "Apple", "Nettle"));

    }





}
