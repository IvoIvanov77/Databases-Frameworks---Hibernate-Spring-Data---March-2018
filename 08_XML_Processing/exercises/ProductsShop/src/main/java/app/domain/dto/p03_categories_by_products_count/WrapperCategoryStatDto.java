package app.domain.dto.p03_categories_by_products_count;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class WrapperCategoryStatDto {

    @XmlElement(name = "category")
    private List<CategoryStatDto> categories;

    public List<CategoryStatDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryStatDto> categories) {
        this.categories = categories;
    }
}
