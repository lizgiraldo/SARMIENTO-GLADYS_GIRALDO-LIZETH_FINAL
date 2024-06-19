package com.backend.clinicaDental.service.impl;

import com.backend.clinicaDental.dto.entrada.OdontologoEntradaDto;
import com.backend.clinicaDental.dto.salida.OdontologoSalidaDto;
import com.backend.clinicaDental.exceptions.ResourceNotFoundException;
import com.backend.clinicaDental.service.IOdontologoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@PropertySource("classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {

@Autowired
IOdontologoService odontologoService;

@Test
    @Order(1)
    void deberiaGuardarUnOdontologo() {
    OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(2L, "Gladys", "Sarmiento");
    OdontologoSalidaDto odontologoSave = odontologoService.registrarOdontologo(odontologoEntradaDto);
    assertNotNull(odontologoSave.getId());
}

@Test
    @Order(2)
    void deberiaBuscarUnOdontologoPorId() {
    assertDoesNotThrow(() -> odontologoService.buscarOdontologo(2L));
}


@Test
    @Order(3)
    void deberiaEliminarUnOdontologo() {
    assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
}




}