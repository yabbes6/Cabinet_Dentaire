package pfa.cabinet.cabinetdentaire.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data@AllArgsConstructor @NoArgsConstructor
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;
    private int price ;
    @ManyToOne
    @JoinColumn(name = "CODE_PAT")
    private Patient patient;

}
