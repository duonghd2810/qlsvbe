package com.example.doan.models;

import com.example.doan.bases.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="days")
public class DayOfTheWeek extends BaseEntity {
    @Id
    @Nationalized
    private String dayOfWeek;

    @OneToMany(mappedBy = "dayOfWeek")
    List<ClassSection> classSectionList;

    public DayOfTheWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
