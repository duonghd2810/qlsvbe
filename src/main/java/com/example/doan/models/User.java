package com.example.doan.models;

import com.example.doan.bases.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String fullName;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String phone;

    @Nationalized
    private String address;

    private String email;

    @Nationalized
    private String gender;

    private String username;

    private String password;

    private String avatar;

    //student
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_major")
    @JsonIgnore
    private Major major;

    @ManyToMany(mappedBy = "userss",fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Role> roless;

    //teacher
    @OneToMany(mappedBy = "teacher")
    private List<ClassSection> classSections;
}
