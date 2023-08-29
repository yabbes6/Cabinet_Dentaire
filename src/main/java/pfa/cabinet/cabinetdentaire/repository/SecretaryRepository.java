package pfa.cabinet.cabinetdentaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfa.cabinet.cabinetdentaire.entities.Secretary;

@Repository
public interface SecretaryRepository extends JpaRepository<Secretary,Long> {
}
