package top.cyanide.cyanide.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "halo_category")
public class Category implements Serializable{

    private static final long serialVersionUID = 8383678847517271505L;

    /**
     * 分类编号
     */
    @Id
    @GeneratedValue
    private Long cateId;

    /**
     * 分类名称
     */
    private String cateName;

    /**
     * 分类路径
     */
    private String cateUrl;

    /**
     * 分类描述
     */
    private String cateDesc;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();
}
