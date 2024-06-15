package com.backend.clinicaDental.dto.entrada;



public class OdontologoEntradaDto {

    private Long id;
    private Long numeroDeMatricula;
    private String nombre;
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
