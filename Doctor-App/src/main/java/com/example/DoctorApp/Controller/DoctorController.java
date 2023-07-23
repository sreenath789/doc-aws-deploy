package com.example.DoctorApp.Controller;

import com.example.DoctorApp.Model.Doctor;
import com.example.DoctorApp.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("doctor")
    public void addDoctor(@RequestBody Doctor doctor){
        doctorService.addDoctor(doctor);
    }
    @GetMapping("doctors")
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }
}
