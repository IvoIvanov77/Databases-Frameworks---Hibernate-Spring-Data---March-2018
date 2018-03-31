package app.repositories;

import app.enums.Size;
import app.model.ingredients.BasicIngredient;
import app.model.labels.Label;
import app.model.shampoos.BasicShampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooRepository extends JpaRepository<BasicShampoo, Long> {


    List<BasicShampoo> findBySizeOrderByIdAsc(Size size);

    List<BasicShampoo> findBySizeOrLabelOrderByPriceAscIdAsc(Size size, Label label);

    List<BasicShampoo> findByPriceGreaterThanOrderByPriceDescIdAsc(BigDecimal price);

    Integer countByPriceLessThan(BigDecimal price);

    List<BasicShampoo> findByIngredientsIn(List<BasicIngredient> labels);

    @Query("select s from BasicShampoo as s inner join s.ingredients as i where i.name in :labelNames")
    List<BasicShampoo> findByIngredientsInJPQL(@Param(value = "labelNames") String... labelNames);

    @Query("select s from BasicShampoo s where size(s.ingredients) < :count ")
    List<BasicShampoo> selectShampoosByIngredientsCount(@Param(value = "count") int count);

    @Query("select  sum(i.price) from BasicShampoo as s " +
            "inner join s.ingredients as i " +
            "where s.brand = :name")
    BigDecimal findShampooNamesAndIngredientsPrices(@Param(value = "name") String name);

}
