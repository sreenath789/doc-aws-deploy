package com.example.DoctorApp.Controller;

import com.example.DoctorApp.Model.Appointment;
import com.example.DoctorApp.Model.Patient;
import com.example.DoctorApp.Model.dto.SignInInput;
import com.example.DoctorApp.Model.dto.SignUpOutput;
import com.example.DoctorApp.Service.AuthenticationService;
import com.example.DoctorApp.Service.DoctorService;
import com.example.DoctorApp.Service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    AuthenticationService authenticationService;

//    @PostMapping("patient")
//    public void addPatient(@RequestBody Patient patient)
//    {
//        patientService.addPatient(patient);
//    }

    @GetMapping("patients")
    public List<Patient> getAllPatients()
    {
        return patientService.getAllPatients();
    }

    //sign-up
    @PostMapping("patient/signup")
    public SignUpOutput signUpPatient(@RequestBody @Valid Patient patient)
    {
        return patientService.signUpPatient(patient);
    }

    @PostMapping("patient/signin")
    public String signInPatient(@RequestBody @Valid SignInInput signInInput)
    {
        return patientService.signInPatient(signInInput);
    }

    @DeleteMapping("patient/signout")
    public String signOutPatient(String email,String token){
        if(authenticationService.authenticate(email, token)){
            return patientService.signOutPatient(email);
        }
        else{
            return "Sign out not allowed for non authenticated user.";
        }
    }

    @PostMapping("patient/appointment/schedule")
    public String scheduleAppointment(@RequestBody Appointment appointment,String email,String token){

        if(authenticationService.authenticate(email,token)){
            boolean status = patientService.scheduleAppointment(appointment);
            return status ? "Appointment scheduled" : "Error accoured during schedule appointment";
        }
        else{
            return "Scheduling failed because invalid authentication";
        }
    }

    @DeleteMapping("patient/appointment/cancel")
    public String cancelAppointment(String email,String token){
        if(authenticationService.authenticate(email,token)){
            patientService.cancelAppointment(email);
            return "Appointment Cancelled!";
        }
        else{
            return "Appointment Cancelled failed due to invalid authentication";
        }
    }


}
