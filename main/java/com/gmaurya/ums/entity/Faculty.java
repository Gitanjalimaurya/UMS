package com.gmaurya.ums.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "faculty")
public class Faculty {

    @Id
    private String facultyId; // e.g., F101

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNo;

}
