package com.backend.clinicaDental.service.impl;

import com.backend.clinicaDental.dto.entrada.PacienteEntradaDto;
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

    private final OdontologoService odontologoService;
    private final PacienteService pacienteService;




    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, OdontologoService  odontologoService, PacienteService pacienteService) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;

        configureMapping();

    }


    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id){
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoEncontrado = null;

        if(turnoBuscado != null){
            turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
            LOGGER.info("Turno Encontrado: {}", JsonPrinter.toString(turnoEncontrado));
        } else LOGGER.error("No se ha encontrado el turno con id {}", id);

        return turnoEncontrado;
    }

    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto){
        Turno turnoRegistrado = new Turno();
        turnoRegistrado.setFechaYHora(turnoEntradaDto.getFechaYHora());

        OdontologoSalidaDto odontologoSalidaDto = null;
        PacienteSalidaDto pacienteSalidaDto = null;

        try{
            odontologoSalidaDto = odontologoService.buscarOdontologoPorId(turnoEntradaDto.getOdontologoId());

        }catch(ResourceNotFoundException e) { throw new BadRequestException(e.getMessage());}

        try{
            pacienteSalidaDto = pacienteService.buscarPacientePorId(turnoEntradaDto.getPacienteId());
        }catch(ResourceNotFoundException e) { throw new BadRequestException(e.getMessage());}

        Odontologo odontologo = modelMapper.map(odontologoSalidaDto,Odontologo.class);
        Paciente paciente = modelMapper.map(pacienteSalidaDto, Paciente.class);

        turnoRegistrado.setOdontologoid(odontologo);
        turnoRegistrado.setPacienteid(paciente);
        LOGGER.info("TurnoEntradaDto: " + JsonPrinter.toString(turnoEntradaDto));

        Turno turno = turnoRepository.save(turnoRegistrado);

        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turno, TurnoSalidaDto.class);
        OdontologoSalidaDto odontologoSalidaDto1 = modelMapper.map(turno.getOdontologoid(), OdontologoSalidaDto.class);
        DomicilioSalidaDto domicilioSalidaDto = modelMapper.map(turno.getPacienteid().getDomicilio(), DomicilioSalidaDto.class);
        PacienteSalidaDto pacienteSalidaDto1 = modelMapper.map(turno.getPacienteid(), PacienteSalidaDto.class);

        //turnoSalidaDto.setOdontologoId(odontologoSalidaDto1);
        //turnoSalidaDto.setPacienteId(pacienteSalidaDto1);

        //Turno turno = modelMapper.map(turnoEntradaDto, Turno.class);
        //LOGGER.info("TurnoEntidad: " + JsonPrinter.toString(turno));
        //TurnoSalidaDto turnoSalidaDto = modelMapper.map(turnoRepository.save(turno), TurnoSalidaDto.class);

        LOGGER.info("TurnoalidaDto: " + JsonPrinter.toString(turnoRegistrado));
        //return turnoSalidaDto;
        return turnoSalidaDto;
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

        if(turnoActualizar != null){

            turnoExistente.setId(turnoActualizar.getId());
            turnoExistente.getPacienteid().setId(turnoActualizar.getPacienteid().getId());
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
        if(buscarTurnoPorId(id) != null){
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con id {}", id);
        } else {
            throw new ResourceNotFoundException("No Existe Registro del Turno con id " + id);
        }

    }


        private void configureMapping() {


            modelMapper.typeMap(TurnoEntradaDto.class, Turno.class)
                    .addMappings(mapper -> mapper.map(TurnoEntradaDto::getOdontologoId, Turno::setOdontologoid));
            modelMapper.typeMap(TurnoEntradaDto.class, Turno.class)
                    .addMappings(mapper -> mapper.map(TurnoEntradaDto::getPacienteId, Turno::setPacienteid));

        }




}
