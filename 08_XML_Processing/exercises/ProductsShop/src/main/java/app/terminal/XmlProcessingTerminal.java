package app.terminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class XmlProcessingTerminal implements CommandLineRunner {

    private final DataImporter importer;
    private final XmlExporter xmlExporter;

    @Autowired
    public XmlProcessingTerminal(DataImporter importer, XmlExporter xmlExporter) {
        this.importer = importer;
        this.xmlExporter = xmlExporter;
    }


    @Override
    public void run(String... strings) throws Exception {
        System.out.println(this.p00_seedDatabase());
        System.out.println(this.p01_productsInRange());
        System.out.println(this.p02_successfullySoldProducts());
        System.out.println(this.p03_categoriesByProductsCount());
        System.out.println(this.p04_usersAndProducts());
    }

    private String p00_seedDatabase(){
        return this.importer.importUsers() +
                System.lineSeparator() +
                this.importer.importCategories() +
                System.lineSeparator() +
                this.importer.importProducts();

    }

    private String p01_productsInRange(){
        return this.xmlExporter.exportProductsInRange("500", "1000");
    }

    private String p02_successfullySoldProducts(){
        return this.xmlExporter.exportSuccessfullySoldProducts();
    }

    private String p03_categoriesByProductsCount(){
        return this.xmlExporter.exportCategoriesByProductsCount();
    }

    private String p04_usersAndProducts(){
        return this.xmlExporter.exportUsersAndTheirSoldProducts();
    }





}
