package com.example.doan.models;

import lombok.*;
import org.hibernate.annotations.Nationalized;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "major")
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    @Column(name = "major_name")
    private String majorName;

    @Nationalized
    @Column(name = "dean")
    private String deanName;
}
