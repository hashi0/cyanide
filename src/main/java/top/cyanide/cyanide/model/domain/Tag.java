package top.cyanide.cyanide.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : RYAN0UP
 * @date : 2018/1/12
 * @version : 1.0
 * description : 文章标签实体类
 */
@Data
@Entity
@Table(name = "halo_tag")
public class Tag implements Serializable{

    private static final long serialVersionUID = -7501342327884372194L;

    /**
     * 标签编号
     */
    @Id
    @GeneratedValue
    private Long tagId;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 标签路径
     */
    private String tagUrl;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();
}
