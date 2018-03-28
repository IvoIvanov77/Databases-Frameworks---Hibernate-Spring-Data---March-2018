package softuni.usersystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.usersystem.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {


}
