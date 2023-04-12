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

    private Long siso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_major")
    @JsonIgnore
    private Major major;

    @OneToMany(mappedBy = "collegeClass")
    List<User> userss;

    public CollegeClass(String className, String homeroomTeacher,Long siso, Major major) {
        this.className = className;
        this.homeroomTeacher = homeroomTeacher;
        this.siso = siso;
        this.major = major;
    }
}
