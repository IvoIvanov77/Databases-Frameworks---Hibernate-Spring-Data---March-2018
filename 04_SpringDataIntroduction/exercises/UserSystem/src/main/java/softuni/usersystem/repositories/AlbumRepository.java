package softuni.usersystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.usersystem.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {


}
