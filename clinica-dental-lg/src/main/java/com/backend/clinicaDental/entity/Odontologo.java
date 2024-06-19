package com.backend.clinicaDental.entity;

import javax.persistence.*;


@Entity
@Table(name = "ODONTOLOGOS")
public class Odontologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10)
    private Long numeroDeMatricula;
    @Column(length = 50)
    private String nombre;
    @Column(length = 50)
    private String apellido;

    public Odontologo(){
    }

    public Odontologo(Long id, Long numeroDeMatricula, String nombre, String apellido) {
        this.id = id;
        this.numeroDeMatricula = numeroDeMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Odontologo(Long numeroDeMatricula, String nombre, String apellido) {
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

    /*
    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", numeroDeMatricula=" + numeroDeMatricula +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }*/
}


