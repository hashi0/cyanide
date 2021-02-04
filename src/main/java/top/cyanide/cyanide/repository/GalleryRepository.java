package top.cyanide.cyanide.repository;

import top.cyanide.cyanide.model.domain.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : RYAN0UP
 * @date : 2018/2/26
 * @version : 1.0
 * description :
 */
public interface GalleryRepository extends JpaRepository<Gallery,Long> {
}
