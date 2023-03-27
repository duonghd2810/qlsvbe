package com.example.doan.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "college_class")
public class CollegeClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String className;

    @Nationalized
    private String homeroomTeacher;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_category")
    @JsonIgnore
    private Major major;

    @OneToMany(mappedBy = "collegeClass")
    List<User> userss;
}
