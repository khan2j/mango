package com.mango.handler.mybatis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 类型转换器
 * 字符串转JSONObject
 */
@MappedTypes(JSONObject.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class JSONObjectHandler extends BaseTypeHandler<JSONObject> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, JSONObject jsonObject, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, String.valueOf(jsonObject.toJSONString()));
    }

    @Override
    public JSONObject getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String sqlJson = resultSet.getString(s);
        if (null != sqlJson){
            return JSON.parseObject(sqlJson);
        }
        return null;
    }

    @Override
    public JSONObject getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String sqlJson = resultSet.getString(i);
        if (null != sqlJson){
            return JSON.parseObject(sqlJson);
        }
        return null;
    }

    @Override
    public JSONObject getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String sqlJson = callableStatement.getString(i);
        if (null != sqlJson){
            return JSON.parseObject(sqlJson);
        }
        return null;
    }
}
