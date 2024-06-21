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
    private Odontologo odontologo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    public Turno() {
    }

    public Turno(LocalDateTime fechaYHora, Odontologo odontologo, Paciente paciente) {
        this.fechaYHora = fechaYHora;
        this.odontologo = odontologo;
        this.paciente = paciente;
    }

    public Turno(Long id, LocalDateTime fechaYHora, Odontologo odontologo, Paciente paciente) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.odontologo = odontologo;
        this.paciente = paciente;
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
        return odontologo;
    }

    public void setOdontologoid(Odontologo odontologoid) {
        this.odontologo = odontologoid;
    }

    public Paciente getPacienteid() {
        return paciente;
    }

    public void setPacienteid(Paciente pacienteid) {
        this.paciente = pacienteid;
    }
}
