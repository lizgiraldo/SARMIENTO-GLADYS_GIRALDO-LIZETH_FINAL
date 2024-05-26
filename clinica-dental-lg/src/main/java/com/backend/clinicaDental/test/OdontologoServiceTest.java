package com.backend.clinicaDental.test;

import com.backend.clinicaDental.repository.impl.OdontologoDaoEnMemoria;
import com.backend.clinicaDental.repository.impl.OdontologoDaoH2;
import com.backend.clinicaDental.service.impl.OdontologoService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;

public class OdontologoServiceTest {

    private OdontologoService odontologoService;

    @Test
    void deberiaRetornarUnaListaVaciaDeOdontologosEnH2(){
        odontologoService = new OdontologoService(new OdontologoDaoH2());
        assertFalse(odontologoService.listarOdontologos().isEmpty());
    }

    @Test
    void deberiaRetornarUnaListaNoVaciaEnMemoria(){
        odontologoService = new OdontologoService(new OdontologoDaoEnMemoria());
        assertFalse(odontologoService.listarOdontologos().isEmpty());
    }




}
