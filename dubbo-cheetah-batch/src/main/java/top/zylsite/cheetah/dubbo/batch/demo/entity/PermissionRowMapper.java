package top.zylsite.cheetah.dubbo.batch.demo.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>Description: TODO</p>
 *
 * @author zhaoyl
 * @version v1.0
 * @date 2019/3/4 20:11
 */
public class PermissionRowMapper implements RowMapper<Permission> {

    @Override
    public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {
        Permission p = new Permission();
        p.setId(rs.getInt("id"));
        p.setVcCode(rs.getString("vc_code"));
        p.setVcName(rs.getString("vc_name"));
        p.setVcUrl(rs.getString("vc_url"));
        return p;
    }

}
