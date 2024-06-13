package com.tornado4651.lmix.cloud.data.mybatis.handler;

import com.tornado4651.lmix.cloud.common.utils.SecurityUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义mybatis类型转换器
 * 由于加密字段比较特殊，所以不能指定映射类型，只能在实体类或者xml的resultMap中指定，否则会应用到所有相同属性的字段。
 * @author tornado4651
 * @date 2024/5/22 20:04
 */
//@MappedTypes({String.class, Date.class})
//@MappedJdbcTypes({JdbcType.VARCHAR})
public class SecurityHandler implements TypeHandler<String> {

    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        // 对敏感字段值进行加密
        String newParameter = SecurityUtils.encrypt(parameter);
        ps.setString(i, newParameter);
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        String columValue = rs.getString(columnName);
        return SecurityUtils.decrypt(columValue);
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        String columValue = rs.getString(columnIndex);
        return SecurityUtils.decrypt(columValue);
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String columValue = cs.getString(columnIndex);
        return SecurityUtils.decrypt(columValue);
    }
}
