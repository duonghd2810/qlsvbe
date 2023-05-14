package com.example.doan.models;

import com.example.doan.bases.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "classrooms")
public class Classroom extends BaseEntity {
    @Id
    private String tenPhong;

    @OneToMany(mappedBy = "classroom")
    List<ClassSection> classSectionList;

    public Classroom(String tenPhong) {
        this.tenPhong = tenPhong;
    }
}
