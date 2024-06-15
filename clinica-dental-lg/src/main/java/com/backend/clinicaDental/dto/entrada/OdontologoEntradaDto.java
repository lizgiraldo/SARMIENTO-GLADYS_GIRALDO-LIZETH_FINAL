package com.backend.clinicaDental.dto.entrada;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class OdontologoEntradaDto {

    private Long id;
    @Positive(message = "La Matricula del Odontologo no puede ser nulo o menor a cero")
    private Long numeroDeMatricula;
    @NotBlank(message = "Debe especificarse el nombre del Odontologo")
    @Size(max = 50, message = "El nombre debe tener hasta 50 caracteres")
    private String nombre;
    @Size(max = 50, message = "El apellido debe tener hasta 50 caracteres")
    @NotBlank(message = "Debe especificarse el apellido del Odontologo")
    private String apellido;

    public OdontologoEntradaDto(Long numeroDeMatricula, String nombre, String apellido) {
        this.numeroDeMatricula = numeroDeMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getNumeroDeMatricula() {
        return numeroDeMatricula;
    }

    public void setNumeroDeMatricula(Long numeroDeMatricula) {
        this.numeroDeMatricula = numeroDeMatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
