package pfa.cabinet.cabinetdentaire.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ssl.SslProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pfa.cabinet.cabinetdentaire.entities.Appointment;
import pfa.cabinet.cabinetdentaire.entities.Patient;
import pfa.cabinet.cabinetdentaire.repository.AppointmentRepository;
import pfa.cabinet.cabinetdentaire.repository.PatientRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    private AppointmentRepository appointmentRepository;

    @GetMapping("/patients")
    public String listPatient(Model model){
        List<Patient> patient = patientRepository.findAll();
        model.addAttribute("patients",patient);
        return "patients";
    }

    @GetMapping("*/addP")
    public String addPatient() {
        return "addPatient";
    }



    @PostMapping("/savePatient")
    public String savePatient(@RequestParam("image") MultipartFile file, Patient patient, BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors())
            return "addPatient";
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains(".."))
            System.out.println("not a valid file");

        patient.setUrlPicture(Base64.getEncoder().encodeToString(file.getBytes()));
        patientRepository.save(patient);
        return "redirect:/patients/appointmentList?patient_id="+patient.getId();
    }

    @GetMapping("/patients/addAppointment")
    public String addAppointment(Model model, @RequestParam("patient_id") Long patient_id){
        Patient patient = patientRepository.findById(patient_id).get();
        int price = 250;
        Appointment appointment = new Appointment();
        //appointment.setPatient(patient);
        model.addAttribute("patient",patient);
        model.addAttribute("price",price);

        return "addAppointment";
    }
    @GetMapping("/deletePatient")
    public String deletePatient(Long id){

        patientRepository.deleteById(id);

        return "redirect:/patients";
    }
    @PostMapping("/editPatient")
    public String editAppointment(@RequestParam("first_name")String fName,
                                  @RequestParam("last_name")String lName,
                                  @RequestParam("birthday")LocalDate birth,
                                  @RequestParam("telp")String telp,
                                  @RequestParam("adress")String adress,
                                  @RequestParam("cin")String cin,
                                  @RequestParam("city")String city,
                                  @RequestParam("gender")String gender,Patient patient, @RequestParam("image") MultipartFile file, @RequestParam("patient_id") Long id, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors())
            return "addPatient";

        patient = patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid patient ID"));
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains(".."))
            System.out.println("not a valid file");
        else{
            patient.setFirst_name(fName);
            patient.setLast_name(lName);
            patient.setBirthday(birth);
            patient.setTelp(telp);
            patient.setAdress(adress);
            patient.setCin(cin);
            patient.setCity(city);
            patient.setGender(gender);
            patient.setUrlPicture(Base64.getEncoder().encodeToString(file.getBytes()));
            patientRepository.save(patient);
        }



        return "redirect:/patients";
    }

    @GetMapping("/patients/appointmentList/editpatient")
    public String editing(Model model,@RequestParam("patient_id") Long id){
        Patient patient = patientRepository.findById(id).get();

        model.addAttribute("patient",patient);

        return "editPatient";
    }

}
