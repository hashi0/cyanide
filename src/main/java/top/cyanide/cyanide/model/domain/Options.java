package top.cyanide.cyanide.model.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author : RYAN0UP
 * @date : 2017/11/14
 * @version : 1.0
 * description : 设置实体类
 */
@Data
@Entity
@Table(name = "halo_options")
public class Options implements Serializable {

    private static final long serialVersionUID = -4065369084341893446L;

    /**
     * 设置项名称
     */
    @Id
    private String optionName;

    /**
     * 设置项的值
     */
    @Lob
    private String optionValue;
}
