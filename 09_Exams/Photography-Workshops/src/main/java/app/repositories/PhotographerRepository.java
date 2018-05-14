package app.repositories;

import app.model.dtos.json_export.ExportPhotographersToJsonDto;
import app.model.dtos.xml_export.ExportPhotographersToXmlDto;
import app.model.entities.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotographerRepository extends JpaRepository<Photographer, Long> {

    @Query("select new app.model.dtos.json_export.ExportPhotographersToJsonDto(p.firstName, p.lastName, p.phone) " +
            "from Photographer p order by p.firstName, p.lastName desc")
    List<ExportPhotographersToJsonDto> getOrderedPhotographers();


    @Query("select new app.model.dtos.xml_export.ExportPhotographersToXmlDto(" +
            "p.firstName, p.lastName, p.primaryCamera.make, p.primaryCamera.model) " +
            "from Photographer p " +
            "where p.primaryCamera.make = p.secondaryCamera.make")
    List<ExportPhotographersToXmlDto> photographersWithSameCameraMake();
}
