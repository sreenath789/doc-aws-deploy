package com.example.DoctorApp.Service;

import com.example.DoctorApp.Repo.IAdminRepo;
import com.example.DoctorApp.Repo.IDoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    IAdminRepo iAdminRepo;
}
