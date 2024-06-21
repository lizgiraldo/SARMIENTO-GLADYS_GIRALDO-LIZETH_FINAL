package com.backend.clinicaDental.service.impl;

import com.backend.clinicaDental.dto.entrada.TurnoEntradaDto;
import com.backend.clinicaDental.dto.salida.DomicilioSalidaDto;
import com.backend.clinicaDental.dto.salida.OdontologoSalidaDto;
import com.backend.clinicaDental.dto.salida.PacienteSalidaDto;
import com.backend.clinicaDental.dto.salida.TurnoSalidaDto;
import com.backend.clinicaDental.entity.Odontologo;
import com.backend.clinicaDental.entity.Paciente;
import com.backend.clinicaDental.entity.Turno;
import com.backend.clinicaDental.exceptions.BadRequestException;
import com.backend.clinicaDental.exceptions.ResourceNotFoundException;
import com.backend.clinicaDental.repository.TurnoRepository;
import com.backend.clinicaDental.service.ITurnosService;
import com.backend.clinicaDental.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class TurnoService implements ITurnosService {

    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final ModelMapper modelMapper;

    private final OdontologoService odontologoService;
    private final PacienteService pacienteService;

    @PersistenceContext
    private EntityManager entityManager;

    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @Override
    @Transactional
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) {
        Turno turnoRegistrado = new Turno();
        turnoRegistrado.setFechaYHora(turnoEntradaDto.getFechaYHora());
        OdontologoSalidaDto odontologoDto = null;
        PacienteSalidaDto pacienteDto = null;

        try {
            odontologoDto = odontologoService.buscarOdontologoPorId(turnoEntradaDto.getOdontologoId());

        } catch (ResourceNotFoundException e) {
            throw new BadRequestException(e.getMessage());
        }

        try {
            pacienteDto = pacienteService.buscarPacientePorId(turnoEntradaDto.getPacienteId());
        } catch (ResourceNotFoundException e) {
            throw new BadRequestException(e.getMessage());
        }

        Odontologo odontologo = modelMapper.map(odontologoDto, Odontologo.class);
        Paciente paciente = modelMapper.map(pacienteDto, Paciente.class);

        odontologo = entityManager.merge(odontologo);
        paciente = entityManager.merge(paciente);

        turnoRegistrado.setOdontologoid(odontologo);
        turnoRegistrado.setPacienteid(paciente);

        Turno turno = turnoRepository.save(turnoRegistrado);
        LOGGER.info("Turno registrado satisfactoriamente: " + JsonPrinter.toString(turno));
        return turnoATurnoSalidaDto(turno);

    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoEncontrado = null;

        if (turnoBuscado != null) {
            turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
            LOGGER.info("Turno Encontrado: {}", JsonPrinter.toString(turnoEncontrado));
        } else LOGGER.error("No se ha encontrado el turno con id {}", id);

        return turnoEncontrado;
    }


    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<TurnoSalidaDto> turnos = turnoRepository.findAll()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoSalidaDto.class))
                .toList();
        LOGGER.info("Estos son los Turnos encontrados" + JsonPrinter.toString(turnos));
        return turnos;
    }

    @Override
    public TurnoSalidaDto actualizarTurno(Long id, TurnoEntradaDto turnoEntradaDto) {
        Turno turnoExistente = modelMapper.map(turnoEntradaDto, Turno.class);
        Turno turnoActualizar = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoSalidaDto = null;

        if (turnoActualizar != null) {

            turnoExistente.setId(turnoActualizar.getId());
            turnoExistente.getPacienteid().setId(turnoActualizar.getPacienteid().getId());
            turnoActualizar = turnoExistente;

            turnoRepository.save(turnoActualizar);
            turnoSalidaDto = modelMapper.map(turnoActualizar, TurnoSalidaDto.class);
            LOGGER.warn("Turno actualizado {}", JsonPrinter.toString(turnoSalidaDto));
        } else {
            LOGGER.error("No fue posible actualizar el turno");
        }

        return turnoSalidaDto;

    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if (buscarTurnoPorId(id) != null) {
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con id {}", id);
        } else {
            throw new ResourceNotFoundException("No Existe Registro del Turno con id " + id);
        }

    }

    private TurnoSalidaDto turnoATurnoSalidaDto(Turno turno) {
        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turno, TurnoSalidaDto.class);
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(turno.getOdontologoid(), OdontologoSalidaDto.class);
        DomicilioSalidaDto domicilioSalidaDto = modelMapper.map(turno.getPacienteid().getDomicilio(), DomicilioSalidaDto.class);
        PacienteSalidaDto pacienteSalidaDto = modelMapper.map(turno.getPacienteid(), PacienteSalidaDto.class);
        pacienteSalidaDto.setDomicilioSalidaDto(domicilioSalidaDto);

        turnoSalidaDto.setOdontologoId(odontologoSalidaDto);
        turnoSalidaDto.setPacienteId(pacienteSalidaDto);

        return turnoSalidaDto;
    }


}
