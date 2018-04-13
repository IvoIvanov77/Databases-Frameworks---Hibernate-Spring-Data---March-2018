package app.terminal;

import app.controllers.*;
import app.io.contracts.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class XmlImporter implements DataImporter{


    private static final String IMPORT_PATH = "/data/import/";


    private final FileIO fileIO;
    private final SupplierController supplierController;
    private final PartController partController;
    private final CarController carController;
    private final CustomerController customerController;
    private final SaleController saleController;

    @Autowired
    public XmlImporter(FileIO fileIO, SupplierController supplierController,
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
        return this.importFromXml(this.supplierController, "suppliers.xml");
    }

    @Override
    public String importParts(){
        return this.importFromXml(this.partController, "parts.xml");
    }

    @Override
    public String importCars(){
        return this.importFromXml(this.carController, "cars.xml");
    }

    @Override
    public String importCustomers(){
        return this.importFromXml(this.customerController, "customers.xml");
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

    private String importFromXml(BaseController controller, String fileName){
        String xmlContent = this.read(fileName);
        return controller.importFromXmlString(xmlContent);
    }
}
