package com.backend.clinicaDental.service.impl;

import com.backend.clinicaDental.dto.entrada.DomicilioEntradaDto;
import com.backend.clinicaDental.dto.entrada.OdontologoEntradaDto;
import com.backend.clinicaDental.dto.entrada.PacienteEntradaDto;
import com.backend.clinicaDental.dto.entrada.TurnoEntradaDto;
import com.backend.clinicaDental.dto.salida.OdontologoSalidaDto;
import com.backend.clinicaDental.dto.salida.PacienteSalidaDto;
import com.backend.clinicaDental.dto.salida.TurnoSalidaDto;
import com.backend.clinicaDental.service.IOdontologoService;
import com.backend.clinicaDental.service.IPacienteService;
import com.backend.clinicaDental.service.ITurnosService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TurnoServiceTest {

    @Autowired
    private ITurnosService turnosService;

    @Autowired
    private IOdontologoService odontologoService;

    @Autowired
    private IPacienteService pacienteService;

    private Long pacienteId;
    private Long odontologoId;

    @Test
    @Order(1)
    void deberiaGuardarUnOdontologo() {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(2L, "Gladys", "Sarmiento");
        OdontologoSalidaDto odontologoSave = odontologoService.registrarOdontologo(odontologoEntradaDto);


        assertNotNull(odontologoSave);
        assertNotNull(odontologoSave.getId());
        odontologoId = odontologoSave.getId();
        assertEquals(2L, odontologoSave.getNumeroDeMatricula());
    }

    @Test
    @Order(2)
    void deberiaRegistrarseUnPacienteDeNombreJuan_yRetornarSuId(){

        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Juan", "Perez", 123456, LocalDate.of(2024, 6, 22), new DomicilioEntradaDto("Calle", 123, "Localidad", "Provincia"));

        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);


        assertNotNull(pacienteSalidaDto);
        assertNotNull(pacienteSalidaDto.getId());
        pacienteId = pacienteSalidaDto.getId();
        assertEquals("Juan", pacienteSalidaDto.getNombre());
    }


    @Test
    @Order(3)
    void deberiaGuardarUnTurnoYDevolverSuId() {
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(LocalDateTime.now(), odontologoId, pacienteId);
        assertDoesNotThrow(()->{
            TurnoSalidaDto turnoSave = turnosService.registrarTurno(turnoEntradaDto);
            assertNotNull(turnoSave.getId());
       });

    }

    @Test
    @Order(4)
    void deberiaBuscarUnTurnoConId1() {
        assertDoesNotThrow(() -> turnosService.buscarTurnoPorId(1L));
    }

    @Test
    @Order(5)
    void deberiaListarTurnosExistentes() {
        assertFalse(turnosService.listarTurnos().isEmpty());
    }

}
