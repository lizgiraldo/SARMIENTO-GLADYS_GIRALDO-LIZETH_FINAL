package com.backend.clinicaDental.repository;

import com.backend.clinicaDental.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Paciente findByDni(int dni);
    Paciente findByDniAndNombre(int dni, String nombre);
}
