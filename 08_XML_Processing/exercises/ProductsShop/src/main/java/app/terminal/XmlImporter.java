package app.terminal;

import app.controllers.BaseController;
import app.controllers.CategoryController;
import app.controllers.ProductController;
import app.controllers.UserController;
import app.io.contracts.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class XmlImporter implements DataImporter{


    private static final String IMPORT_PATH = "/xml_data/import/";

    private final UserController userController;
    private final CategoryController categoryController;
    private final ProductController productController;
    private final FileIO fileIO;

    @Autowired
    public XmlImporter(UserController userController, CategoryController categoryController, ProductController productController, FileIO fileIO) {
        this.userController = userController;
        this.categoryController = categoryController;
        this.productController = productController;
        this.fileIO = fileIO;
    }

    @Override
    public String importUsers() {
        return this.importFromXml(this.userController, "users.xml");
    }

    @Override
    public String importCategories() {
        return this.importFromXml(this.categoryController, "categories.xml");
    }

    @Override
    public String importProducts() {
        return this.importFromXml(this.productController, "products.xml");
    }

    private String read(String fileName){
        try {
            return this.fileIO.read(IMPORT_PATH + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return "Can't read file";
        }
    }

    private String importFromXml(BaseController controller, String fileName){
        String xmlContent = this.read(fileName);
        return controller.importFromXmlString(xmlContent);
    }
}
