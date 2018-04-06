package app.domain.dto.json.p00_seed_database;

import com.google.gson.annotations.Expose;

public class ImportCategoryJsonDto {

    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
