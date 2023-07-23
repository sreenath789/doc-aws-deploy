package com.example.DoctorApp.Service;

import com.example.DoctorApp.Model.Doctor;
import com.example.DoctorApp.Repo.IDoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    IDoctorRepo iDoctorRepo;

    public void addDoctor(Doctor doctor) {
        iDoctorRepo.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return iDoctorRepo.findAll();
    }
}
