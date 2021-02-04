package top.cyanide.cyanide.repository;

import top.cyanide.cyanide.model.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : RYAN0UP
 * @date : 2018/1/24
 * @version : 1.0
 * description :
 */
public interface MenuRepository extends JpaRepository<Menu,Long> {
}
