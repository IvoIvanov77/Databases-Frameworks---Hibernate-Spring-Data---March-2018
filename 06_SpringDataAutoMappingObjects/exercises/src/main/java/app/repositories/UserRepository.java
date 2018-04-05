package app.repositories;

import app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u " +
            "left join fetch u.orders " +
            "left join fetch u.games " +
            "where u.email = :email " +
            "and u.password = :password")
    User getLoggedInUser(@Param("email") String email, @Param("password") String password);

    @Query("select u from User u left join fetch u.games where u.id =:id")
    User getOneWithGames(@Param("id") Long id);

}
