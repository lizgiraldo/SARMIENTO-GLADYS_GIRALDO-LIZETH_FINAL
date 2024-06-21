package com.backend.clinicaDental.service.impl;

import com.backend.clinicaDental.dto.entrada.OdontologoEntradaDto;
import com.backend.clinicaDental.dto.salida.OdontologoSalidaDto;
import com.backend.clinicaDental.dto.salida.PacienteSalidaDto;
import com.backend.clinicaDental.entity.Odontologo;
import com.backend.clinicaDental.entity.Paciente;
import com.backend.clinicaDental.exceptions.ResourceNotFoundException;
import com.backend.clinicaDental.repository.OdontologoRepository;
import com.backend.clinicaDental.service.IOdontologoService;
import com.backend.clinicaDental.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {
    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private final OdontologoRepository odontologoRepository;
    private final ModelMapper modelMapper;

    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(Long id) throws ResourceNotFoundException {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoSalidaDto odontologoEncontrado = null;

        if (odontologoBuscado != null) {
            odontologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
            LOGGER.info("Odontologo Encontrado: {}", JsonPrinter.toString(odontologoEncontrado));
        } else LOGGER.error("No se ha encontrado el odontologo con id {}", id);

        return odontologoEncontrado;
    }


    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoEntradaDto) {
        Odontologo odontologo = modelMapper.map(odontologoEntradaDto, Odontologo.class);
        Odontologo odontologoRegistrado = odontologoRepository.save(odontologo);
        LOGGER.info("Odontologo registrado con exito{}", JsonPrinter.toString(odontologoRegistrado));
        return modelMapper.map(odontologoRegistrado, OdontologoSalidaDto.class);
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
        List<OdontologoSalidaDto> odontologos = odontologoRepository.findAll()
                .stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class))
                .toList();
        LOGGER.info("Estos son los odontologos encontrados" + JsonPrinter.toString(odontologos));
        return odontologos;
    }

    @Override
    public OdontologoSalidaDto actualizarOdontologo(Long id, OdontologoEntradaDto odontologoEntradaDto) throws ResourceNotFoundException {
        Odontologo odontologoExistente = odontologoRepository.findById(id).orElse(null);
        if (odontologoExistente == null) {
            throw new ResourceNotFoundException("Odontologo no encontrado con id" + id);
        }

        odontologoExistente.setNumeroDeMatricula(odontologoEntradaDto.getNumeroDeMatricula());
        odontologoExistente.setNombre(odontologoEntradaDto.getNombre());
        odontologoExistente.setApellido(odontologoEntradaDto.getApellido());

        Odontologo odontologoActulizado = odontologoRepository.save(odontologoExistente);
        LOGGER.info("Odontologo actualizado con éxito " + JsonPrinter.toString(odontologoActulizado));

        return modelMapper.map(odontologoActulizado, OdontologoSalidaDto.class);
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if (odontologoRepository.existsById(id)) {
            odontologoRepository.deleteById(id);
        } else {
            LOGGER.warn("Odontologo no encontrado con id " + id);
            throw new ResourceNotFoundException("Odontologo no encontrado con id " + id);
        }
    }

}
