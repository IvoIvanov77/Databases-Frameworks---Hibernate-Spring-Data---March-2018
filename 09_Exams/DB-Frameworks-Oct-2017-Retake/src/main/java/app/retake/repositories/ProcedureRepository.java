package app.retake.repositories;

import app.retake.domain.models.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Integer> {

    @Query("select distinct p from Procedure p left join fetch p.services")
    List<Procedure> findAllWithServices();
}
