package app.domain.dto.p00_seed_database;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WrapperCategoriesImportDto {

    @XmlElement(name = "category")
    private List<ImportCategoryXmlDto> categories;

    public List<ImportCategoryXmlDto> getCategories() {
        return categories;
    }

    public void setCategories(List<ImportCategoryXmlDto> categories) {
        this.categories = categories;
    }
}
