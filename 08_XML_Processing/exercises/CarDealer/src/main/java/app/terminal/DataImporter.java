package app.terminal;

import javax.xml.bind.JAXBException;

public interface DataImporter {

    String importSuppliers();

    String importParts();

    String importCars();

    String importCustomers();

    String importSales();
}
