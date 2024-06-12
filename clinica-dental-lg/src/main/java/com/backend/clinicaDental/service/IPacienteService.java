package com.backend.clinicaDental.service;

import com.backend.clinicaDental.dto.entrada.PacienteEntradaDto;
import com.backend.clinicaDental.dto.salida.PacienteSalidaDto;
import com.backend.clinicaDental.exceptions.ResourceNotFoundException;


import java.util.List;

public interface IPacienteService {

    PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteEntradaDto);
    List<PacienteSalidaDto> listarPacientes();

    PacienteSalidaDto buscarPacientePorId(Long id);
    void eliminarPaciente(Long id) throws ResourceNotFoundException;
    PacienteSalidaDto actulizarPaciente(PacienteEntradaDto pacienteEntradaDto, Long id);

    PacienteSalidaDto actualizarPaciente(PacienteEntradaDto pacienteEntradaDto, Long id);
}
