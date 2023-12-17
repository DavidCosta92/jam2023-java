package com.example.JAM23.demo.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "asistencia")
public class AttendanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer attendance_id;



    @ManyToOne(targetEntity = InscriptionEntity.class , fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_INSCRIPCION" , referencedColumnName = "id_insc", nullable = false)
    @JsonIgnore
    private InscriptionEntity inscription;


    @Column(name = "Fecha")
    private LocalDate date;

    @Column(name = "Asistencia")
    private boolean present;

}
