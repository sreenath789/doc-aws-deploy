package com.example.DoctorApp.Model;

import com.example.DoctorApp.Model.enums.Qualification;
import com.example.DoctorApp.Model.enums.Specialization;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,scope=Doctor.class,property="doctorId")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    private String doctorName;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    private String contactNumber;
    @Enumerated(EnumType.STRING)
    private Qualification qualification;
    @Min(500)
    @Max(2000)
    private Double consultationFee;
    //mappings
    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointmentList;





}
