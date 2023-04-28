package com.example.doan.mapper;

import org.apache.logging.log4j.util.Strings;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResponseStudentMapper implements RowMapper<StudentInfoMapper> {
    @Override
    public StudentInfoMapper mapRow(ResultSet rs, int rowNum) throws SQLException {
        StudentInfoMapper info = new StudentInfoMapper(rs.getString("subject_code"),rs.getLong("student_id"));
        if(!Strings.isEmpty(String.valueOf(rs.getDouble("finaltest")))){
            info.setFinaltest(rs.getDouble("finaltest"));
        }
        return info;
    }
}
