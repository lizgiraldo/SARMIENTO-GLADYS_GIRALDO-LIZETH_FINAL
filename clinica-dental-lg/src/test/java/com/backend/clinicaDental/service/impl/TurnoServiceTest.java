package com.backend.clinicaDental.service.impl;

import com.backend.clinicaDental.dto.entrada.TurnoEntradaDto;
import com.backend.clinicaDental.dto.salida.TurnoSalidaDto;
import com.backend.clinicaDental.service.IOdontologoService;
import com.backend.clinicaDental.service.IPacienteService;
import com.backend.clinicaDental.service.ITurnosService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@PropertySource("classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {
    @Qualifier("ITurnosService")

    @Autowired
    ITurnosService turnosService;

    @Autowired
    IOdontologoService odontologoService;

    @Autowired
    IPacienteService pacienteService;

    private Long pacienteId;
    private Long odontologoId;

    @Test
    @Order(1)
    void deberiaGuardarUnTurno() {
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(LocalDateTime.now(), odontologoId, pacienteId);
        assertDoesNotThrow(() -> {
            TurnoSalidaDto turnoGuardado = turnosService.registrarTurno(turnoEntradaDto);
            assertNotNull(turnoGuardado.getId());
        });
    }

    @Test
    @Order(2)
    void deberiaBuscarUnTurno() {
        assertDoesNotThrow(() -> turnosService.buscarTurno(1L));
    }

    @Test
    @Order(3)
    void deberiaListarTurnosExistentes() {
        assertFalse(turnosService.listarTurnos().isEmpty());
    }

}
