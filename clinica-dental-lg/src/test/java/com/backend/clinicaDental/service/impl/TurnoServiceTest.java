package com.backend.clinicaDental.service.impl;

import com.backend.clinicaDental.dto.entrada.DomicilioEntradaDto;
import com.backend.clinicaDental.dto.entrada.PacienteEntradaDto;
import com.backend.clinicaDental.dto.entrada.TurnoEntradaDto;
import com.backend.clinicaDental.dto.salida.PacienteSalidaDto;
import com.backend.clinicaDental.dto.salida.TurnoSalidaDto;
import com.backend.clinicaDental.service.IOdontologoService;
import com.backend.clinicaDental.service.IPacienteService;
import com.backend.clinicaDental.service.ITurnosService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

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
    void deberiaGuardarUnTurno() {
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(LocalDateTime.now(), odontologoId, pacienteId);

        TurnoSalidaDto turnoSalidaDto = turnosService.registrarTurno(turnoEntradaDto);

        //assert
        assertNotNull(turnoSalidaDto);
        assertNotNull(turnoSalidaDto.getId());
        assertEquals(1, turnoSalidaDto.getId());
    }

    @Test
    @Order(2)
    void deberiaBuscarUnTurno() {
        assertDoesNotThrow(() -> turnosService.buscarTurnoPorId(1L));
    }

    @Test
    @Order(3)
    void deberiaListarTurnosExistentes() {
        assertFalse(turnosService.listarTurnos().isEmpty());
    }

}
