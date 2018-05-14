package app.repositories;

import app.model.entities.BasicCamera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CameraRepository extends JpaRepository<BasicCamera, Long> {
}
