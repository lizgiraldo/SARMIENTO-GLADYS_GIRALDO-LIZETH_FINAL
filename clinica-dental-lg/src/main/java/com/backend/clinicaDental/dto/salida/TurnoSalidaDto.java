package com.backend.clinicaDental.dto.salida;

import java.time.LocalDateTime;

public class TurnoSalidaDto {

    private Long id;
    private LocalDateTime fechaYHora;
    //Tener en cuenta que deben almacenarse por el id
    private OdontologoSalidaDto odontologoId;
    private PacienteSalidaDto pacienteId;

    public TurnoSalidaDto() {

    }

    public TurnoSalidaDto(Long id, LocalDateTime fechaYHora, OdontologoSalidaDto odontologoId, PacienteSalidaDto pacienteId) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.odontologoId = odontologoId;
        this.pacienteId = pacienteId;
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

    public OdontologoSalidaDto getOdontologoId() {
        return odontologoId;
    }

    public void setOdontologoId(OdontologoSalidaDto odontologoId) {
        this.odontologoId = odontologoId;
    }

    public PacienteSalidaDto getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(PacienteSalidaDto pacienteId) {
        this.pacienteId = pacienteId;
    }

}
