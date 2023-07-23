package com.example.DoctorApp.Repo;

import com.example.DoctorApp.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepo extends JpaRepository<Admin,Long> {
}
