package pfa.cabinet.cabinetdentaire.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pfa.cabinet.cabinetdentaire.entities.Appointment;
import pfa.cabinet.cabinetdentaire.repository.AppointmentRepository;
import pfa.cabinet.cabinetdentaire.repository.AvailableRepository;
import pfa.cabinet.cabinetdentaire.repository.PatientRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class PaymentController {
    private final AppointmentRepository appointmentRepository;
    private final AvailableRepository availableRepository;
    private final PatientRepository patientRepository;

    @GetMapping("/payment")
    public String paymentstats(Model model,@RequestParam(name="date",defaultValue = "") LocalDate date)
    {
        Map<LocalDate,Double> payment = new HashMap<>();

        if (date == null){
            payment= appointmentRepository.findAll()
                    .stream()
                    .collect(Collectors.groupingBy(Appointment::getDate,Collectors.summingDouble(Appointment::getPrice)));
        }else {
            payment= appointmentRepository.findByDate(date)
                    .stream()
                    .collect(Collectors.groupingBy(Appointment::getDate,Collectors.summingDouble(Appointment::getPrice)));
        }
        model.addAttribute("date",date);
        model.addAttribute("payment",payment);

        //model.addAttribute("currentPage",page);
        return "payment";
    }

    @GetMapping("/payment/dateOfappointmentPricee")
    public String getPriceappointment(Model model,@RequestParam("date") LocalDate date){
        List<Appointment> appointments= appointmentRepository.findByDate(date);
        Map<LocalDate,Double> allPrice= appointmentRepository.findByDate(date)
                .stream()
                .collect(Collectors.groupingBy(Appointment::getDate,Collectors.summingDouble(Appointment::getPrice)));
        model.addAttribute("appointments",appointments);
        model.addAttribute("allPrice",allPrice);
        model.addAttribute("date",date);

        return "dateOfappointmentPricee";
    }
}
