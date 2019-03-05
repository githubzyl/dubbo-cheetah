package top.zylsite.cheetah.dubbo.batch.demo.entity;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>Description: TODO</p>
 *
 * @author zhaoyl
 * @version v1.0
 * @date 2019/3/4 20:11
 */
public class PermissionFieldSetMapper implements FieldSetMapper<Permission> {

    @Override
    public Permission mapFieldSet(FieldSet fieldSet) throws BindException {
        Permission p = new Permission();
        p.setVcCode(fieldSet.readString("vcCode"));
        p.setVcName(fieldSet.readString("vcName"));
        p.setVcUrl(fieldSet.readString("vcUrl"));
        return p;
    }

}
