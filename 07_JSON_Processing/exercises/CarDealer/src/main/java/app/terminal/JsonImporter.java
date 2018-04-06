package app.terminal;

import app.controllers.*;
import app.io.contracts.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonImporter implements DataImporter{


    private static final String IMPORT_PATH = "/data/import/";


    private final FileIO fileIO;
    private final SupplierController supplierController;
    private final PartController partController;
    private final CarController carController;
    private final CustomerController customerController;
    private final SaleController saleController;

    @Autowired
    public JsonImporter(FileIO fileIO, SupplierController supplierController,
                        PartController partController, CarController carController,
                        CustomerController customerController, SaleController saleController) {
        this.fileIO = fileIO;
        this.supplierController = supplierController;
        this.partController = partController;
        this.carController = carController;
        this.customerController = customerController;
        this.saleController = saleController;
    }

    @Override
    public String importSuppliers(){
        return this.importFromJson(this.supplierController, "suppliers.json");
    }

    @Override
    public String importParts(){
        return this.importFromJson(this.partController, "parts.json");
    }

    @Override
    public String importCars(){
        return this.importFromJson(this.carController, "cars.json");
    }

    @Override
    public String importCustomers(){
        return this.importFromJson(this.customerController, "customers.json");
    }

    @Override
    public String importSales(){
        return this.saleController.createSales();
    }

    private String read(String fileName){
        try {
            return this.fileIO.read(IMPORT_PATH + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return "Can't read file";
        }
    }

    private String importFromJson(BaseController controller, String fileName){
        String jsonContent = this.read(fileName);
        return controller.importFromJsonString(jsonContent);
    }
}
