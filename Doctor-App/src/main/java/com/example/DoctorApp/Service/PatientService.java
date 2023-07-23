package com.example.DoctorApp.Service;

import com.example.DoctorApp.Model.Appointment;
import com.example.DoctorApp.Model.AuthenticationToken;
import com.example.DoctorApp.Model.Patient;
import com.example.DoctorApp.Model.dto.SignInInput;
import com.example.DoctorApp.Model.dto.SignUpOutput;
import com.example.DoctorApp.Repo.IAppointmentRepo;
import com.example.DoctorApp.Repo.IAuthenticationRepo;
import com.example.DoctorApp.Repo.IDoctorRepo;
import com.example.DoctorApp.Repo.IPatientRepo;
import com.example.DoctorApp.Service.utility.EmailUtility.EmailHandler;
import com.example.DoctorApp.Service.utility.hashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    IPatientRepo iPatientRepo;

    @Autowired
    IAuthenticationRepo iAuthenticationRepo;

    @Autowired
    IDoctorRepo iDoctorRepo;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    IAppointmentRepo iAppointmentRepo;

    public void addPatient(Patient patient) {
        iPatientRepo.save(patient);
    }

    public List<Patient> getAllPatients() {
        return iPatientRepo.findAll();
    }

    public SignUpOutput signUpPatient(Patient patient) {

        boolean signUpStatus = true;
        String signUpStatusMessage=null;

        String newEmail = patient.getPatientEmail();

//        if(newEmail==null){
//            signUpStatusMessage="Invalid Mail";
//            signUpStatus=false;
//            return new SignUpOutput(signUpStatus,signUpStatusMessage);
//        }

        Patient existingPatient = iPatientRepo.findFirstByPatientEmail(newEmail);

        if(existingPatient!=null){
            signUpStatusMessage="Email already Registered!!!";
            signUpStatus=false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        try{
            String encryptedPassword= PasswordEncryptor.getEncryptedPassword(patient.getPassword());
            patient.setPassword(encryptedPassword);
            iPatientRepo.save(patient);
            return new SignUpOutput(signUpStatus,"Patient Registered Successfully!!!");
        }
        catch (Exception e){
            signUpStatus=false;
            signUpStatusMessage="Internal error occurred during sign up";
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }


    }

    public String signInPatient(SignInInput signInInput) {
        String signInStatusMessage = null;
        String signInEmail = signInInput.getEmail();

        if(signInEmail==null){
            signInStatusMessage = "Invalid Email";
            return signInStatusMessage;
        }

        Patient existingPatient = iPatientRepo.findFirstByPatientEmail(signInEmail);

        if(existingPatient == null){
            signInStatusMessage = "Email not registered";
            return signInStatusMessage;
        }

        //Match Passwords

        try{
            String encryptedPassword = PasswordEncryptor.getEncryptedPassword(signInInput.getPassword());

            if(existingPatient.getPassword().equals(encryptedPassword)){

                //create a session for authentication
                AuthenticationToken authenticationToken = new AuthenticationToken(existingPatient);
                iAuthenticationRepo.save(authenticationToken);

                EmailHandler.sendEmail(signInEmail,"Authentication Email",authenticationToken.getTokenValue());

                return "Authentication Token sent to your mail";


            }
            else{
                signInStatusMessage = "Invalid credentials";
                return signInStatusMessage;
            }


        }
        catch(Exception e){
            signInStatusMessage = "Internal error occurred";
            return signInStatusMessage;
        }

    }

    public boolean scheduleAppointment(Appointment appointment) {

        //id of doctor
        Long doctorId = appointment.getDoctor().getDoctorId();
        boolean isDoctorValid = iDoctorRepo.existsById(doctorId);

        //id of patient
        Long patientId = appointment.getPatient().getPatientId();
        boolean isPatientValid = iPatientRepo.existsById(patientId);



        if(isDoctorValid && isPatientValid)
        {
            appointmentService.saveAppointment(appointment);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void cancelAppointment(String email) {

        Patient patient = iPatientRepo.findFirstByPatientEmail(email);

        Appointment appointment = appointmentService.getAppointmentForPatient(patient);

        appointmentService.cancelAppointment(appointment);
    }

    public String signOutPatient(String email) {
        Patient patient = iPatientRepo.findFirstByPatientEmail(email);

        iAuthenticationRepo.delete(iAuthenticationRepo.findFirstByPatient(patient));

        return "Patient signed out Successfully!";
    }
}
