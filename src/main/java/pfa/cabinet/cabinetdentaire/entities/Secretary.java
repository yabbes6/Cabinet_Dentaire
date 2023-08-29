package pfa.cabinet.cabinetdentaire.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data@NoArgsConstructor@AllArgsConstructor
public class Secretary {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="secretary_id")
    private Long id;
    private String first_name;
    private String last_name;
    private String gender;
    private LocalDate birthday;
    private String telp;
    private String city;
    private String adress;
    private double salary;
    private String email;
    private String mdp;
    private String job;
    private String cin;


}
