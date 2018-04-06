package app.domain.json_dto.p03_local_suppliers;

import app.domain.models.Supplier;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExportSupplierDto {

    @Expose
    @SerializedName("Id")
    private Long id;

    @Expose
    @SerializedName("Name")
    private String name;

    @Expose
    @SerializedName("PartsCount")
    private Integer partsCount;

    public ExportSupplierDto() {
    }

    public ExportSupplierDto(Supplier supplier) {
        this.id = supplier.getId();
        this.name = supplier.getName();
        this.partsCount = supplier.getParts().size();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(Integer partsCount) {
        this.partsCount = partsCount;
    }
}

