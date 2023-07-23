package com.example.DoctorApp.Repo;

import com.example.DoctorApp.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDoctorRepo extends JpaRepository<Doctor,Long> {
}
