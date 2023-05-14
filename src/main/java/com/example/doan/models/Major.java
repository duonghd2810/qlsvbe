package com.example.doan.models;

import com.example.doan.bases.BaseEntity;
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
@Table(name = "major")
public class Major extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    @Column(name = "major_name")
    private String majorName;

    @Nationalized
    @Column(name = "dean_name")
    private String deanName;

    @OneToMany(mappedBy = "major",cascade = CascadeType.ALL)
    List<User> students;

    @OneToMany(mappedBy = "majorSubject")
    List<Subject> subjects;
}
