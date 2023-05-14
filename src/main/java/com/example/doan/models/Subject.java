package com.example.doan.models;

import com.example.doan.bases.BaseEntity;
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
@Table(name = "subjects")
public class Subject extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subjectCode;
    @Nationalized
    private String subjectName;

    private Integer tc;

    private double price = 380000;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_major")
    @JsonIgnore
    private Major majorSubject;

    public Subject(Major major, String subjectName, Integer tc) {
        this.majorSubject = major;
        this.subjectName = subjectName;
        this.tc = tc;
    }
    public Subject(Major major,String code,String subjectName, Integer tc) {
        this.majorSubject = major;
        this.subjectCode = code;
        this.subjectName = subjectName;
        this.tc = tc;
    }

    @OneToMany(mappedBy = "subjectt",cascade = CascadeType.ALL)
    private List<ClassSection> classSections;

}
