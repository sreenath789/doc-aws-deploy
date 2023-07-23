package com.example.DoctorApp.Repo;

import com.example.DoctorApp.Model.Doctor;
import com.example.DoctorApp.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepo extends JpaRepository<Patient,Long> {

    Patient findFirstByPatientEmail(String newEmail);
}
