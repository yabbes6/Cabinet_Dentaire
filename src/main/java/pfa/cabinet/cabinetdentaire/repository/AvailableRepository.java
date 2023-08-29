package pfa.cabinet.cabinetdentaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfa.cabinet.cabinetdentaire.entities.Availability;

@Repository
public interface AvailableRepository extends JpaRepository<Availability,Long> {
}
