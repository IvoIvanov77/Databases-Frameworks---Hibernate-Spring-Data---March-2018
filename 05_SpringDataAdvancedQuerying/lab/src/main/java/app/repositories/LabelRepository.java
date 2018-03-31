package app.repositories;

import app.model.labels.BasicLabel;
import app.model.labels.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<BasicLabel, Long> {
}
