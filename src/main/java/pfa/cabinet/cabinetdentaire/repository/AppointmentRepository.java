package pfa.cabinet.cabinetdentaire.repository;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Query;
import pfa.cabinet.cabinetdentaire.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfa.cabinet.cabinetdentaire.entities.Patient;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    //Page<Appointment> findByDate(LocalDate date, Pageable pageable);
    List<Appointment> findByDate(LocalDate date);

    List<Appointment> findByPatient(Patient patient);

}
