package com.example.DoctorApp.Service;

import com.example.DoctorApp.Model.AuthenticationToken;
import com.example.DoctorApp.Repo.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    IAuthenticationRepo iAuthenticationRepo;

    public boolean authenticate(String email,String token){
        AuthenticationToken authenticationToken = iAuthenticationRepo.findFirstByTokenValue(token);
        if(authenticationToken == null){
            return false;
        }
        String tokenConnectedEmail = authenticationToken.getPatient().getPatientEmail();
        return tokenConnectedEmail.equals(email);
    }
}
