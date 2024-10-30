package com.gmaurya.ums.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subject")  // Optional, ensure the table name matches your database
public class Subject {

    @Id
    private String id;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    //added by me
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Document> documents=new ArrayList<>();
}
