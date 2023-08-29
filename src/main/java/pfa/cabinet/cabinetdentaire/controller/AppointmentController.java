package pfa.cabinet.cabinetdentaire.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pfa.cabinet.cabinetdentaire.entities.Appointment;
import pfa.cabinet.cabinetdentaire.entities.Availability;
import pfa.cabinet.cabinetdentaire.entities.Patient;
import pfa.cabinet.cabinetdentaire.repository.AppointmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import pfa.cabinet.cabinetdentaire.repository.AvailableRepository;
import pfa.cabinet.cabinetdentaire.repository.PatientRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final AvailableRepository availableRepository;
    private final PatientRepository patientRepository;


    @GetMapping(value = {"/appointments","/"})
    public String getAppointments(Model model,
                                  @RequestParam(name="date" ,defaultValue ="")LocalDate date)
    {
        List<Patient> patients ;
        List<Appointment> appointment=new ArrayList<>();
        if(date == null){
            appointment= appointmentRepository.findAll();

        }else
            appointment= appointmentRepository.findByDate(date);


        model.addAttribute("appointments",appointment);
        model.addAttribute("date",date);


        List<Availability> availability = availableRepository.findAll();
        model.addAttribute("availability", availability);

        return "appointments";
    }


    @GetMapping("/delete")
    public String delete (Long id,String date){
        appointmentRepository.deleteById(id);

        return "redirect:/appointments?&date="+date;
    }


    @PostMapping("/editAppointment")
    public String editAppointment(Appointment appointment,
                                  @RequestParam("patient_id") Long id,
                                  @RequestParam("first_name") String fname,
                                  @RequestParam("last_name") String lname, BindingResult bindingResult, LocalDate date){
        if (bindingResult.hasErrors())
            return "addAppointment";
        Patient p = patientRepository.findById(id).get();
        p.setFirst_name(fname);
        p.setLast_name(lname);
        appointment.setPatient(p);
        appointmentRepository.save(appointment);

        return "redirect:/appointments?date="+date;
    }

    @PostMapping("/saveAppointment")
    public String saveAppointment(@Valid Appointment appointment ,@RequestParam("patient_id") Long patient_id, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "addAppointment";
        Patient patient = patientRepository.findById(patient_id).get();
        appointment.setPatient(patient);
        //model.addAttribute("appointment",appointment);
        appointmentRepository.save(appointment);

        return "redirect:/patients/appointmentList?patient_id="+patient.getId();
    }

    @GetMapping("*/appointmentList")
    public String appointmentList(@RequestParam(value = "patient_id") Long patient_id,Model model){
        Patient patient = patientRepository.findById(patient_id).orElseThrow(()-> new IllegalArgumentException("invalid patient ID"));
        List<Appointment> appointmentList = appointmentRepository.findByPatient(patient);
        model.addAttribute("appointmentlist",appointmentList);
        model.addAttribute("namePatient",patient);

        return "appointmentList";
    }

    @GetMapping("/appointments/edit")
    public String editing(Model model,@RequestParam("id") Long id){
        Appointment appointment = appointmentRepository.findById(id).get();
       // Patient patient = patientRepository.findById(appointment.getPatient().getId()).get();
        model.addAttribute("appointment",appointment);
        //model.addAttribute("patient",patient);
        return "editAppointment";
    }

}
