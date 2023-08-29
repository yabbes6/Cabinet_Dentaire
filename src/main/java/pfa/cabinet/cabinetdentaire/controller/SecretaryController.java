package pfa.cabinet.cabinetdentaire.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pfa.cabinet.cabinetdentaire.entities.Appointment;
import pfa.cabinet.cabinetdentaire.entities.Secretary;
import pfa.cabinet.cabinetdentaire.repository.AppointmentRepository;
import pfa.cabinet.cabinetdentaire.repository.SecretaryRepository;

import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
public class SecretaryController {
    SecretaryRepository secretaryRepository;
    AppointmentRepository appointmentRepository;

    @GetMapping("/secretary")
    public String appointments(Model model){

        List<Appointment> appointment=appointmentRepository.findByDate(LocalDate.now());

        int length = appointment.size();
        model.addAttribute("appointmentForToday",appointment);
        model.addAttribute("length",length);
        return "secretary";
    }

    @GetMapping("/secretary/information/editSecretary")
    public String editSecretary(Model model, Long id){
        Secretary secretary=secretaryRepository.findById(id).get();
        model.addAttribute("secretary",secretary);

        return "editSecretary";
    }

    @GetMapping("/secretary/information")
    public String secretaryprofil(Model model){
        List<Secretary> secretaries=secretaryRepository.findAll();
        model.addAttribute("secretaries",secretaries);
        return "profileSecretary";
    }

    @PostMapping("/saveSecretary")
    public String saveSecretary(@RequestParam("first_name")String fName,
                                @RequestParam("last_name")String lName,
                                @RequestParam("birthday")LocalDate birth,
                                @RequestParam("salary")double salary,
                                @RequestParam("adress")String adress,
                                @RequestParam("cin")String cin,
                                @RequestParam("city")String city,

                                @RequestParam("email")String email,
                                @RequestParam("mdp")String mdp,@RequestParam("secretary_id")Long id){
        Secretary secretary = secretaryRepository.findById(id).get();
        secretary.setFirst_name(fName);
        secretary.setLast_name(lName);
        secretary.setBirthday(birth);
        secretary.setSalary(salary);
        secretary.setAdress(adress);
        secretary.setCin(cin);
        secretary.setCity(city);
        secretary.setEmail(email);
        secretary.setMdp(mdp);
        secretaryRepository.save(secretary);
        return "redirect:/secretary/information";
    }


}
