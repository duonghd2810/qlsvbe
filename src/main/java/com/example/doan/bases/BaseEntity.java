package com.example.doan.bases;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public abstract class BaseEntity {
    @CreationTimestamp
    @Column(name = "create_at")
    private Timestamp createAt;
    @UpdateTimestamp
    @Column(name = "update_at")
    private Timestamp updateAt;
}
