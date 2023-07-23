package com.example.DoctorApp.Repo;

import com.example.DoctorApp.Model.AuthenticationToken;
import com.example.DoctorApp.Model.Patient;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findFirstByTokenValue(String token);

    AuthenticationToken findFirstByPatient(Patient patient);
}
