package app.repositories;

import app.model.ingredients.BasicIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface IngredientRepository  extends JpaRepository<BasicIngredient, Long>{

    List<BasicIngredient> findByNameStartingWith(String prefix);

    List<BasicIngredient> findByNameInOrderByPrice(String... names);

    List<BasicIngredient> findByNameIn(String... names);


    @Transactional
    int deleteBasicIngredientsByName(String name);

    @Modifying
    @Transactional
    @Query("update BasicIngredient i set i.price = 1.1 * i.price")
    int updateAllPricesWith10Percentage();

    @Modifying
    @Transactional
    @Query("update BasicIngredient as i set i.price = i.price + i.price / 100 * :percent where i.name in :list")
    int updatePriceByPercentage(@Param(value = "percent") BigDecimal percentage,
                                 @Param(value = "list") String... names);
}
