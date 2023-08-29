package pfa.cabinet.cabinetdentaire.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;


@Data@AllArgsConstructor @NoArgsConstructor
@Entity
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type ;
    private LocalTime startTime;
    private LocalTime endTime;

}
