package pfa.cabinet.cabinetdentaire.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pfa.cabinet.cabinetdentaire.entities.Availability;
import pfa.cabinet.cabinetdentaire.repository.AvailableRepository;

import java.util.List;

@Controller
public class AvailableController {
    @Autowired
    private AvailableRepository availableRepository;

    @PostMapping("/appointments/saveAvailable")
    public String saveAvailable(Availability availability) {
        availableRepository.save(availability);
        return "redirect:/appointments";
    }

    @GetMapping("/deleteAppointments")
    public String delete(Long id) {

        availableRepository.deleteById(id);
        return "appointments";
    }
}
