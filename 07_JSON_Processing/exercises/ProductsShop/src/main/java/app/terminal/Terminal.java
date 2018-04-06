package app.terminal;


import app.controllers.CategoryController;
import app.controllers.ProductController;
import app.controllers.UserController;
import app.io.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {


    private final UserController userController;
    private final ProductController productController;
    private final CategoryController categoryController;


    @Autowired
    public Terminal(UserController userController,
                    ProductController productController,
                    CategoryController categoryController) {
        this.userController = userController;
        this.productController = productController;
        this.categoryController = categoryController;
    }


    @Override
    public void run(String... strings) throws Exception {
        seedDatabase();
        this.p01_productsInRange();
        this.p02_successfullySoldProducts();
        this.p03_categoriesByProductsCount();
        this.p04_usersAndProducts();
    }

    private void seedDatabase(){
        this.categoryController.importCategoriesFromJason();
        this.userController.importUsersFromJson();
        this.productController.importProductsFromJson();

    }

    private void p01_productsInRange() {
        String content = this.productController.productsInRage("500", "1000");
        System.out.println(content);
        FileUtil.writeFile("export\\products-in-range.json", content);
    }

    private void p02_successfullySoldProducts() {
        String content = this.userController.successfullySoldProducts();
        System.out.println(content);
        FileUtil.writeFile("export\\users-sold-products.json", content);
    }

    private void p03_categoriesByProductsCount(){
        String content = this.categoryController.categoriesByProductsCount();
        System.out.println(content);
        FileUtil.writeFile("export\\categories-by-products.json", content);
    }

    private void p04_usersAndProducts(){
        String content = this.userController.usersAndTheirSoldProducts();
        System.out.println(content);
        FileUtil.writeFile("export\\users-and-products.json", content);
    }


}
