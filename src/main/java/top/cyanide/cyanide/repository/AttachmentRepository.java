package top.cyanide.cyanide.repository;

import top.cyanide.cyanide.model.domain.Attachment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : RYAN0UP
 * @date : 2018/1/10
 * @version : 1.0
 * description :
 */
public interface AttachmentRepository extends JpaRepository<Attachment,Long>{

    /**
     * 查询所有附件，分页
     *
     * @param pageable pageable
     * @return  page
     */
    @Override
    Page<Attachment> findAll(Pageable pageable);
}
