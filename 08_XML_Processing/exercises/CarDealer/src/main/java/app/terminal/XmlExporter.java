package app.terminal;

import app.controllers.*;
import app.io.contracts.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class XmlExporter {

    private final static String EXPORT_PATH = "data\\export\\";

    private final FileIO fileIO;
    private final SupplierController supplierController;
    private final CarController carController;
    private final CustomerController customerController;
    private final SaleController saleController;

    @Autowired
    public XmlExporter(FileIO fileIO,
                       SupplierController supplierController, CarController carController,
                       CustomerController customerController, SaleController saleController) {
        this.fileIO = fileIO;
        this.supplierController = supplierController;
        this.carController = carController;
        this.customerController = customerController;
        this.saleController = saleController;
    }

    public String exportOrderedCustomers(){
        String content = this.customerController.orderedCustomers();
        return this.writeToFile(content, "ordered-customers.xml");
    }

    public String exportCarsFromMakeToyota(){
        String content = this.carController.carsFromMake("Toyota");
        return this.writeToFile(content, "toyota-cars.xml");
    }

    public String exportLocalSuppliers(){
        String content = this.supplierController.getLocalSuppliers();
        return this.writeToFile(content, "local-suppliers.xml");
    }

    public String exportCarsWithParts(){
        String content = this.carController.carsWithParts();
        return this.writeToFile(content, "cars-and-parts.xml");
    }

    public String exportTotalSalesByCustomer(){
        String content = this.customerController.totalSalesByCustomer();
        return this.writeToFile(content, "customers-total-sales.xml");
    }

    public String exportSalesWithAppliedDiscount(){
        String content = this.saleController.allSalesWithAppliedDiscount();
        return this.writeToFile(content, "sales-discounts.xml");
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
