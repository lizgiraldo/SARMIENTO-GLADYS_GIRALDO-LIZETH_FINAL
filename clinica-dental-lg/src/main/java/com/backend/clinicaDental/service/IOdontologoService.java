package com.backend.clinicaDental.service;
import com.backend.clinicaDental.entity.Odontologo;
import java.util.List;

public interface IOdontologoService {

    Odontologo registrarOdontologo(Odontologo odontologo);
    List<Odontologo> listarOdontologos();

}
