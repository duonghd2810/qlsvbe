package com.example.doan.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long id;

    private String roleName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_user",
            joinColumns = @JoinColumn(name = "id_role",referencedColumnName = "id_role"),
            inverseJoinColumns = @JoinColumn(name = "id_user",referencedColumnName = "id_user"))
    @JsonIgnore
    private Set<User> userss;
}
