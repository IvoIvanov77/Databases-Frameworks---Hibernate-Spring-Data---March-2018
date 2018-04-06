package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.domain.models.Category;
import app.domain.models.Product;
import app.domain.models.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceBetweenAndBuyerNullOrderByPrice(BigDecimal low, BigDecimal high);

    List<Product> findBySellerAndBuyerNotNull(User seller);

    Set<Product> findByCategoriesContains(Category category);

    List<Product> findBySeller(User seller);
}
