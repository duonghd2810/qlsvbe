package com.example.doan.models;

import com.example.doan.enums.PriceTcEnum;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String subjectName;

    private Integer tc;

    private PriceTcEnum price = PriceTcEnum.PRICE;

    @OneToMany(mappedBy = "subjectCourse")
    private List<CourseGrade> courseGrades;
}
