package app.domain.dto.p03_categories_by_products_count;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryStatDto {

   @XmlAttribute(name = "name")
    private String category;

    @XmlElement(name = "products-count")
    private long productsCount;

    @XmlElement(name = "average-price")
    private BigDecimal averagePrice;

    @XmlElement(name = "total-revenue")
    private BigDecimal totalRevenue;

    public CategoryStatDto() {
    }

    public CategoryStatDto(String category, long productsCount, double averagePrice, BigDecimal totalRevenue) {
        this.category = category;
        this.productsCount = productsCount;
        this.averagePrice = new BigDecimal(averagePrice).setScale(2, RoundingMode.HALF_UP);
        this.totalRevenue = totalRevenue;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(long productsCount) {
        this.productsCount = productsCount;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }


//    private List<BigDecimal> getPrices(Category category){
//        return category.getProducts()
//                .stream()
//                .map(Product::getPrice)
//                .collect(Collectors.toList());
//    }
//
//    private BigDecimal average(List<BigDecimal> bigDecimals) {
//        BigDecimal sum = bigDecimals.stream()
//                .map(Objects::requireNonNull)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//        return sum.divide(new BigDecimal(bigDecimals.size()), 2);
//    }
//
//    private BigDecimal sum(List<BigDecimal> bigDecimals) {
//        return bigDecimals.stream()
//                .map(Objects::requireNonNull)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//    }


}
