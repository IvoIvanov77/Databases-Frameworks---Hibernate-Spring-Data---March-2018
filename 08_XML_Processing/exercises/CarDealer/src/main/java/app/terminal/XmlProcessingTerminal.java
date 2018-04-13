package app.terminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class XmlProcessingTerminal implements CommandLineRunner {

    private final XmlImporter xmlImporter;
    private final XmlExporter xmlExporter;


    @Autowired
    public XmlProcessingTerminal(XmlImporter xmlImporter, XmlExporter xmlExporter) {
        this.xmlImporter = xmlImporter;
        this.xmlExporter = xmlExporter;
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
        return this.xmlImporter.importSuppliers() +
                System.lineSeparator() +
                this.xmlImporter.importParts() +
                System.lineSeparator() +
                this.xmlImporter.importCars() +
                System.lineSeparator() +
                this.xmlImporter.importCustomers() +
                System.lineSeparator() +
                this.xmlImporter.importSales();
    }

    private String p01_orderedCustomers(){
        return this.xmlExporter.exportOrderedCustomers();
    }

    private String p02_carsFromMakeToyota(){
        return this.xmlExporter.exportCarsFromMakeToyota();
    }

    private String p03_localSuppliers(){
        return this.xmlExporter.exportLocalSuppliers();
    }

    private String p04_carsWithTheirListOfParts(){
        return this.xmlExporter.exportCarsWithParts();
    }

    private String p05_totalSalesByCustomer(){
        return this.xmlExporter.exportTotalSalesByCustomer();
    }

    private String p06_salesWithAppliedDiscount(){
        return this.xmlExporter.exportSalesWithAppliedDiscount();
    }




}
