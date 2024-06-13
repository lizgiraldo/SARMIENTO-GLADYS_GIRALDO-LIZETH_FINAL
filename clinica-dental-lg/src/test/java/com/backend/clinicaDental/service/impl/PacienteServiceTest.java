package com.backend.clinicaDental.service.impl;


import com.backend.clinicaDental.dto.entrada.DomicilioEntradaDto;
import com.backend.clinicaDental.dto.entrada.PacienteEntradaDto;
import com.backend.clinicaDental.dto.salida.PacienteSalidaDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@TestPropertySource(locations = "classpatch:application-test.properties")
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void deberiaRegistrarseUnPacienteDeNombreJuan_yRetornarSuId(){
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Juan", "Perez", 123456, LocalDate.of(2024, 6, 22), new DomicilioEntradaDto("Calle", 123, "Localidad","Provincia"));

        PacienteSalidaDto pacienteSalidaDto  = pacienteService.registrarPaciente(pacienteEntradaDto);

        //assert
        assertNotNull(pacienteSalidaDto);
        assertNotNull(pacienteSalidaDto.getId());
        assertEquals("Juan", pacienteSalidaDto.getNombre());
    }

    @Test
    @Order(2)
    void deberiaEliminarElPacienteConId(){
        assertDoesNotThrow(() -> pacienteService.eliminarPaciente(1L));
    }

    @Test
    @Order(3)
    void deberiaDevolverUnaListaVaciaPacientes(){
        List<PacienteSalidaDto> listadoDePacientes = pacienteService.listarPacientes();
        assertTrue(listadoDePacientes.isEmpty());
    }
}