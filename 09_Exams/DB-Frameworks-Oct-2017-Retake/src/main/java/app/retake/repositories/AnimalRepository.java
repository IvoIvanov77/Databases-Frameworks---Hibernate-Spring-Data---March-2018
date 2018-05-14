package app.retake.repositories;

import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;
import app.retake.domain.models.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer>{

    Animal getFirstByPassport(Passport passport);

    @Query("select new app.retake.domain.dto.AnimalsJSONExportDTO(a.passport.ownerName, a.name, a.age, " +
            "a.passport.serialNumber, a.passport.registeredOn) " +
            "from Animal a where a.passport.ownerPhoneNumber =:phoneNumber " +
            "order by a.age, a.passport.serialNumber")
    List<AnimalsJSONExportDTO> allAnimalsByOwnerPhoneNumber(@Param("phoneNumber") String phoneNumber);

}
