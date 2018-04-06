package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import app.domain.models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {




    /** Get all users who have at least 1 sold item with a buyer.
     * Order them by last name, then by first name. Select the person's first and last name.
     * For each of the sold products (products with buyers), select the product's name,
     * price and the buyer's first and last name.
     * @return List of users
     */
    @Query("select distinct u from User u inner join fetch u.soldProducts p " +
            "where p.buyer is not null " +
            "order by u.lastName, u.firstName")
    List<User> findBySoldProducts();


//    SELECT u.first_name, u.last_name, COUNT(p.id) AS products_count
//    FROM users AS u
//    INNER JOIN products AS p ON p.seller_id = u.id
//    WHERE p.buyer_id IS NOT NULL
//    GROUP BY u.id
//    ORDER BY products_count DESC, u.last_name;


    /** Get all users who have at least 1 sold product. Order them by the number of sold products
     * (from highest to lowest), then by last name (ascending).
     * Select only their first and last name, age and for each product - name and price.
     *
     * @return List of users
     */
    @Query("select distinct u from User u " +
            "inner join fetch u.soldProducts p " +
            "where p.buyer is not null " +
            "order by u.lastName")
    List<User> findAllUsersWithSoldProducts();


}
