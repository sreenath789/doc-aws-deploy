package com.example.DoctorApp.Controller;

import com.example.DoctorApp.Model.Appointment;
import com.example.DoctorApp.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

//    @PostMapping("appointment")
//    public void scheduleAppointment(@RequestBody Appointment appointment)
//    {
//        appointmentService.scheduleAppointment(appointment);
//    }

    @GetMapping("appointments")
    public List<Appointment> getAllAppointments()
    {
        return appointmentService.getAllAppointments();
    }
}
