package com.backend.clinicaDental.service;

import com.backend.clinicaDental.dto.entrada.TurnoEntradaDto;
import com.backend.clinicaDental.dto.salida.TurnoSalidaDto;
import com.backend.clinicaDental.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITurnosService {
    TurnoSalidaDto buscarTurno(Long id) throws ResourceNotFoundException;
    TurnoSalidaDto registrarTurno(TurnoEntradaDto turno) throws ResourceNotFoundException;
    List<TurnoSalidaDto> listarTurnos();
    TurnoSalidaDto actualizarTurno(Long id, TurnoEntradaDto turnoEntradaDto);
    void eliminarTurno(Long id) throws ResourceNotFoundException;
}
