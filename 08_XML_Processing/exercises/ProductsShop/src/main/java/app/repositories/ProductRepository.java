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

//    SELECT p.id, p.name, p.price
//    FROM products AS p
//    INNER JOIN users AS u ON u.id = p.buyer_id
//    WHERE p.price BETWEEN 500 AND 1000
//    ORDER BY p.price;
    List<Product> findAllByPriceBetweenAndBuyerNullOrderByPrice(BigDecimal low, BigDecimal high);

    List<Product> findBySellerAndBuyerNotNull(User seller);

    Set<Product> findByCategoriesContains(Category category);

    List<Product> findBySeller(User seller);
}
