package com.example.DoctorApp.Controller;

import com.example.DoctorApp.Service.AdminService;
import com.example.DoctorApp.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;
}
