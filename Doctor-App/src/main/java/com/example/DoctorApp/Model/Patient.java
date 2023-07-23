package com.example.DoctorApp.Model;

import com.example.DoctorApp.Model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,scope=Patient.class,property="patientId")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String patientName;
    @Pattern(regexp = "^.+@(?![Hh][Oo][Ss][Pp][Aa][Dd][Mm][Ii][Nn]\\.[Cc][Oo][Mm]$).+$")
    @Column(unique = true)
    private String patientEmail;
    @NotBlank
    private String password;
    private Integer age;
    private String address;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(mappedBy = "patient")
    private Appointment appointment;




}
