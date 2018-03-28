package softuni.usersystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import softuni.usersystem.model.User;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByEmailEndsWith(@NotNull String provider);

    List<User> findByLastTimeLoggedInBeforeAndIsDeletedIsFalse(Date lastTimeLoggedIn);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update User u " +
            "set u.isDeleted = true " +
            "where u.isDeleted = false " +
            "and u.lastTimeLoggedIn < :time")
    Integer setUserDeleteOn(@Param("time") Date time);


}
