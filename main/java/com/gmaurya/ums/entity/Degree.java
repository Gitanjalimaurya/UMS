package com.gmaurya.ums.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "degree")
public class Degree {

    @Id
    @Column(name = "degreeId", nullable = false)
    private String degreeId;

    @Column(name = "degreeName", nullable = false)
    private String degreeName;
}
