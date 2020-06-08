package repositories;

import model.Picture;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends PagingAndSortingRepository<Picture, Long> {
}
