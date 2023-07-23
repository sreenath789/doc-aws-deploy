package com.example.DoctorApp.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,scope=Appointment.class,property="appointmentId")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;
    private String appointmentDesc;
    private LocalDateTime appointmentScheduledTime;
    private LocalDateTime appointmentCreationTime;
    @ManyToOne
    @JoinColumn(name="fk_doctor_id")
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name="fk_patient_id")
    private Patient patient;
}
