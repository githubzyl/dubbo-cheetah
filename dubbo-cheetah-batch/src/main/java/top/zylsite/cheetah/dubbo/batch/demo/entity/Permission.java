package top.zylsite.cheetah.dubbo.batch.demo.entity;

import org.springframework.jdbc.core.RowMapper;

import javax.xml.bind.annotation.XmlAttribute;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>Description: TODO</p>
 *
 * @author zhaoyl
 * @version v1.0
 * @date 2019/3/4 20:11
 */
public class Permission {

    private Integer id;
    private String vcCode;
    private String vcName;
    private String vcUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVcCode() {
        return vcCode;
    }

    public void setVcCode(String vcCode) {
        this.vcCode = vcCode;
    }

    public String getVcName() {
        return vcName;
    }

    public void setVcName(String vcName) {
        this.vcName = vcName;
    }

    public String getVcUrl() {
        return vcUrl;
    }

    public void setVcUrl(String vcUrl) {
        this.vcUrl = vcUrl;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", vcCode='" + vcCode + '\'' +
                ", vcName='" + vcName + '\'' +
                ", vcUrl='" + vcUrl + '\'' +
                '}';
    }

}
