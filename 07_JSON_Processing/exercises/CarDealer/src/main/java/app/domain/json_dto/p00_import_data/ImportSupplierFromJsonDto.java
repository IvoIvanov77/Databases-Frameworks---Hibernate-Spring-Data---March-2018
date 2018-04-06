package app.domain.json_dto.p00_import_data;

import com.google.gson.annotations.Expose;

public class ImportSupplierFromJsonDto {

    @Expose
    private String name;

    @Expose
    private boolean isImporter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
