package com.example.DoctorApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationDate;

    @OneToOne
    @JoinColumn(name="fk_patient_id")
    Patient patient;

    public AuthenticationToken(Patient patient){
        this.patient=patient;
        this.tokenCreationDate=LocalDateTime.now();
        this.tokenValue= UUID.randomUUID().toString();
    }
}

