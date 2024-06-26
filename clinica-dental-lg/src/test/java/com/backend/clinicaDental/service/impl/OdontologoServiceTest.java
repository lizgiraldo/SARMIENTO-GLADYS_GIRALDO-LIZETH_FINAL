package com.backend.clinicaDental.service.impl;

import com.backend.clinicaDental.dto.entrada.OdontologoEntradaDto;
import com.backend.clinicaDental.dto.salida.OdontologoSalidaDto;
import com.backend.clinicaDental.service.IOdontologoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {

    @Autowired
    private IOdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaGuardarUnOdontologoConNumeroDeMatricula_2_yRetornarSuId() {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(2L, "Gladys", "Sarmiento");
        OdontologoSalidaDto odontologoSave = odontologoService.registrarOdontologo(odontologoEntradaDto);


        assertNotNull(odontologoSave);
        assertNotNull(odontologoSave.getId());
        assertEquals(2L, odontologoSave.getNumeroDeMatricula());
    }

    @Test
    @Order(2)
    void deberiaBuscarUnOdontologoConId1() {
        assertDoesNotThrow(() -> odontologoService.buscarOdontologoPorId(1L));
    }


    @Test
    @Order(3)
    void deberiaEliminarUnOdontologoConId1() {
        assertDoesNotThrow(() -> odontologoService.eliminarOdontologo(1L));
    }


}
