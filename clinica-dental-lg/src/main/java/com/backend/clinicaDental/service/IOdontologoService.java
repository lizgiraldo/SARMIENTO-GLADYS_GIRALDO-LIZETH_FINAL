package com.backend.clinicaDental.service;
import com.backend.clinicaDental.dto.entrada.OdontologoEntradaDto;
import com.backend.clinicaDental.dto.salida.OdontologoSalidaDto;

import com.backend.clinicaDental.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOdontologoService {

    OdontologoSalidaDto buscarOdontologo(Long id) throws ResourceNotFoundException;
    OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoEntradaDto);
    List<OdontologoSalidaDto> listarOdontologos();
    OdontologoSalidaDto actulizarOdontologo(Long id, OdontologoEntradaDto odontologoEntradaDto) throws ResourceNotFoundException;
    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
}
