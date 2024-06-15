package com.backend.clinicaDental.dto.salida;

public class OdontologoSalidaDto {
    private Long id;
    private Long numeroDeMatricula;
    private String nombre;
    private String apellido;

    public OdontologoSalidaDto(Long id, Long numeroDeMatricula, String nombre, String apellido) {
        this.id = id;
        this.numeroDeMatricula = numeroDeMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
