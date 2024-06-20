package com.backend.clinicaDental.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TURNOS")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fechaYHora;

    //REVISAR
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "odontologo_id", nullable = false)
    private Odontologo odontologoid;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente pacienteid;

    public Turno(){
    }

    public Turno(LocalDateTime fechaYHora, Odontologo odontologoid, Paciente pacienteid) {
        this.fechaYHora = fechaYHora;
        this.odontologoid = odontologoid;
        this.pacienteid = pacienteid;
    }

    public Turno(Long id, LocalDateTime fechaYHora, Odontologo odontologoid, Paciente pacienteid) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.odontologoid = odontologoid;
        this.pacienteid = pacienteid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public Odontologo getOdontologoid() {
        return odontologoid;
    }

    public void setOdontologoid(Odontologo odontologoid) {
        this.odontologoid = odontologoid;
    }

    public Paciente getPacienteid() {
        return pacienteid;
    }

    public void setPacienteid(Paciente pacienteid) {
        this.pacienteid = pacienteid;
    }
}
