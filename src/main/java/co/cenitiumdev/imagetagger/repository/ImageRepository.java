package co.cenitiumdev.imagetagger.repository;

import co.cenitiumdev.imagetagger.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByTagsContainingIgnoreCase(String tag);
}