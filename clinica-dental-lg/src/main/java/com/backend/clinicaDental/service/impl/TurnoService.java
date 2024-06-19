package com.backend.clinicaDental.service.impl;

import com.backend.clinicaDental.dto.entrada.TurnoEntradaDto;
import com.backend.clinicaDental.dto.salida.TurnoSalidaDto;
import com.backend.clinicaDental.entity.Turno;
import com.backend.clinicaDental.exceptions.ResourceNotFoundException;
import com.backend.clinicaDental.repository.OdontologoRepository;
import com.backend.clinicaDental.repository.PacienteRepository;
import com.backend.clinicaDental.repository.TurnoRepository;
import com.backend.clinicaDental.service.ITurnosService;
import com.backend.clinicaDental.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class TurnoService implements ITurnosService {

    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final ModelMapper modelMapper;


    @PersistenceContext
    private EntityManager entityManager;

    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, OdontologoRepository ontologoRepository, PacienteRepository pacienteRepository, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public TurnoSalidaDto buscarTurno(Long id) throws ResourceNotFoundException {
        Turno turno = turnoRepository.findById(id).orElseThrow(null);
        if (turno == null) {
            throw new ResourceNotFoundException("No se encontro el turno" + turno);
        }
        LOGGER.info("Turno encontrado" + JsonPrinter.toString(turno));
        return modelMapper.map(turno, TurnoSalidaDto.class);
    }

    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) throws ResourceNotFoundException {
        Turno turno = modelMapper.map(turnoEntradaDto, Turno.class);
        Turno turnoRegistrado = turnoRepository.save(turno);
        LOGGER.info("Turno registrado " + JsonPrinter.toString(turnoRegistrado));
        return modelMapper.map(turnoRegistrado, TurnoSalidaDto.class);
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<TurnoSalidaDto> turnos = turnoRepository.findAll()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoSalidaDto.class))
                .toList();
        LOGGER.info("Estos son loss Turnos encontrados" + JsonPrinter.toString(turnos));
        return turnos;
    }

    @Override
    public TurnoSalidaDto actualizarTurno(Long id, TurnoEntradaDto turnoEntradaDto) {
        Turno turnoExistente = modelMapper.map(turnoEntradaDto, Turno.class);
        Turno turnoActualizar = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoSalidaDto = null;

        if(turnoActualizar != null){

            turnoExistente.setId(turnoActualizar.getId());
            turnoExistente.getPaciente().setId(turnoActualizar.getPaciente().getId());
            turnoActualizar = turnoExistente;

            turnoRepository.save(turnoActualizar);
            turnoSalidaDto = modelMapper.map(turnoActualizar, TurnoSalidaDto.class);
            LOGGER.warn("Turno actualizado {}", JsonPrinter.toString(turnoSalidaDto));
        } else{
            LOGGER.error("No fue posible actulizar el turno");
        }

      return turnoSalidaDto;

    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
   if (turnoRepository.existsById(id)){
       turnoRepository.deleteById(id);
   } else {
       LOGGER.warn("No se encontro el turno con el id " + id);
       throw new ResourceNotFoundException("No se encontro el turno con id " + id);
   }
    }
}