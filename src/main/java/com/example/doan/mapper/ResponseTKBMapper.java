package com.example.doan.mapper;

import org.apache.logging.log4j.util.Strings;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResponseTKBMapper implements RowMapper<TKBInfoMapper> {
    @Override
    public TKBInfoMapper mapRow(ResultSet rs, int rowNum) throws SQLException {
        TKBInfoMapper info = new TKBInfoMapper();
        info.setIdClassSection(rs.getLong("class_section_id"));
        info.setMahp(rs.getString("subject_code"));
        info.setTenhp(rs.getString("subject_name"));
        if(!Strings.isEmpty(String.valueOf(rs.getLong("id_teacher")))){
            info.setId_teacher(rs.getLong("id_teacher"));
            info.setTeacherName(rs.getString("teacherName"));
        }
        info.setId_day(rs.getString("id_day"));
        info.setLesson(rs.getString("lesson"));
        info.setId_classroom(rs.getString("id_classroom"));
        return info;
    }
}
