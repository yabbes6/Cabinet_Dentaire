package pfa.cabinet.cabinetdentaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfa.cabinet.cabinetdentaire.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long>{
}
