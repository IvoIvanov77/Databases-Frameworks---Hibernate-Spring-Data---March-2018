package app.terminal;

import app.controllers.CategoryController;
import app.controllers.ProductController;
import app.controllers.UserController;
import app.io.contracts.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class XmlExporter {

    private final static String EXPORT_PATH = "xml_data\\export\\";

    private final FileIO fileIO;
    private final ProductController productController;
    private final UserController userController;
    private final CategoryController categoryController;

    @Autowired
    public XmlExporter(FileIO fileIO, ProductController productController, UserController userController, CategoryController categoryController) {
        this.fileIO = fileIO;
        this.productController = productController;
        this.userController = userController;
        this.categoryController = categoryController;
    }


    public String exportProductsInRange(String lowerBoundPrice, String upperBoundPrice){
        String content = this.productController.productsInRage(lowerBoundPrice, upperBoundPrice);
        return this.writeToFile(content, "products-in-range.xml");
    }

    public String exportSuccessfullySoldProducts(){
        String content = this.userController.successfullySoldProducts();
        return this.writeToFile(content, "users-sold-products.xml");
    }

    public String exportCategoriesByProductsCount(){
        String content = this.categoryController.categoriesByProductsCount();
        return this.writeToFile(content, "categories-by-products.xml");
    }

    public String exportUsersAndTheirSoldProducts(){
        String content = this.userController.usersAndTheirSoldProducts();
        return this.writeToFile(content, "users-and-products.xml");
    }

    private String writeToFile(String content, String fileName){
        try {
            this.fileIO.write(EXPORT_PATH + fileName, content);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
            return "Can't writeToFile to file";
        }
    }
}
