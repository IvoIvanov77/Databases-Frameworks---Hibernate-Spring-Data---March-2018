package softuni.usersystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.usersystem.model.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {


}
