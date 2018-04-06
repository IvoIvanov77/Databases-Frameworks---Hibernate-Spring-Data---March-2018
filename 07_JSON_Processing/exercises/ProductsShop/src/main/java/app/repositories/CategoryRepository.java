package app.repositories;

import app.domain.dto.json.p03_categories_by_products_count.CategoryStatDto;
import app.domain.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {



//    select c.name, count(p.id), sum(p.price), avg(p.price)
//    from categories as c
//    inner join products_categories as pc on pc.categories_id = c.id
//    inner join products as p on p.id = pc.products_id
//    group by c.id;

    @Query("select new " +
            "app.domain.dto.json.p03_categories_by_products_count.CategoryStatDto( " +
            "c.name, count(p.price), avg(p.price), sum(p.price)) " +
            " from Category c " +
            "inner join c.products as p " +
            "group by c order by count(p) desc ")
    List<CategoryStatDto> categoriesByProductsCount();



}
