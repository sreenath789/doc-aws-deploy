package com.example.DoctorApp.Service;

import com.example.DoctorApp.Model.Appointment;
import com.example.DoctorApp.Model.Patient;
import com.example.DoctorApp.Repo.IAdminRepo;
import com.example.DoctorApp.Repo.IAppointmentRepo;
import com.example.DoctorApp.Repo.IDoctorRepo;
import com.example.DoctorApp.Repo.IPatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    IAppointmentRepo iAppointmentRepo;

    @Autowired
    IDoctorRepo iDoctorRepo;

    @Autowired
    IPatientRepo iPatientRepo;

    public void saveAppointment(Appointment appointment) {

        appointment.setAppointmentCreationTime(LocalDateTime.now());
        iAppointmentRepo.save(appointment);
    }


    public List<Appointment> getAllAppointments() {
        return iAppointmentRepo.findAll();
    }

    public Appointment getAppointmentForPatient(Patient patient) {
        return iAppointmentRepo.findFirstByPatient(patient);
    }

    public void cancelAppointment(Appointment appointment) {
        iAppointmentRepo.delete(appointment);
    }
}
