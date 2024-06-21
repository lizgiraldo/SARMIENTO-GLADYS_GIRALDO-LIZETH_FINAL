package com.backend.clinicaDental.service;

import com.backend.clinicaDental.dto.entrada.PacienteEntradaDto;
import com.backend.clinicaDental.dto.entrada.TurnoEntradaDto;
import com.backend.clinicaDental.dto.salida.PacienteSalidaDto;
import com.backend.clinicaDental.dto.salida.TurnoSalidaDto;
import com.backend.clinicaDental.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ITurnosService {
    TurnoSalidaDto buscarTurnoPorId(Long id) throws ResourceNotFoundException;
    TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto);
    List<TurnoSalidaDto> listarTurnos();
    TurnoSalidaDto actualizarTurno(Long id, TurnoEntradaDto turnoEntradaDto) throws ResourceNotFoundException;
    void eliminarTurno(Long id) throws ResourceNotFoundException;
}
