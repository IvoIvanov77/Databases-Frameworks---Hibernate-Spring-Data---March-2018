package app.terminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JsonProcessingTerminal implements CommandLineRunner {

    private final JsonImporter jsonImporter;
    private final JsonExporter jsonExporter;


    @Autowired
    public JsonProcessingTerminal(JsonImporter jsonImporter, JsonExporter jsonExporter) {
        this.jsonImporter = jsonImporter;
        this.jsonExporter = jsonExporter;
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println(this.p00_seedDatabase());
        System.out.println(this.p01_orderedCustomers());
        System.out.println(this.p02_carsFromMakeToyota());
        System.out.println(this.p03_localSuppliers());
        System.out.println(this.p04_carsWithTheirListOfParts());
        System.out.println(this.p05_totalSalesByCustomer());
        System.out.println(this.p06_salesWithAppliedDiscount());
    }

    private String p00_seedDatabase(){
        return this.jsonImporter.importSuppliers() +
                System.lineSeparator() +
                this.jsonImporter.importParts() +
                System.lineSeparator() +
                this.jsonImporter.importCars() +
                System.lineSeparator() +
                this.jsonImporter.importCustomers() +
                System.lineSeparator() +
                this.jsonImporter.importSales();
    }

    private String p01_orderedCustomers(){
        return this.jsonExporter.exportOrderedCustomers();
    }

    private String p02_carsFromMakeToyota(){
        return this.jsonExporter.exportCarsFromMakeToyota();
    }

    private String p03_localSuppliers(){
        return this.jsonExporter.exportLocalSuppliers();
    }

    private String p04_carsWithTheirListOfParts(){
        return this.jsonExporter.exportCarsWithParts();
    }

    private String p05_totalSalesByCustomer(){
        return this.jsonExporter.exportTotalSalesByCustomer();
    }

    private String p06_salesWithAppliedDiscount(){
        return this.jsonExporter.exportSalesWithAppliedDiscount();
    }




}
