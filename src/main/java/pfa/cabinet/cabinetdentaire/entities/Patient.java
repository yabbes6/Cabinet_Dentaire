package pfa.cabinet.cabinetdentaire.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Data @NoArgsConstructor@AllArgsConstructor
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="patient_id")
    private Long id;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String urlPicture;
    private String first_name;
    private String last_name;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;
    private String adress;
    private String cin;
    private String telp;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate creationDate;
    private String city;
    private String gender;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments;



}
