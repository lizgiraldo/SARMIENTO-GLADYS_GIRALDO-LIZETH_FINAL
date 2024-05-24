package com.backend.clinicaDental.repository.impl;

import com.backend.clinicaDental.entity.Odontologo;
import com.backend.clinicaDental.repository.IDao;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class OdontologoDaoEnMemoria implements IDao<Odontologo> {

    private final Logger LOGGER = Logger.getLogger(OdontologoDaoEnMemoria.class);
    List<Odontologo> listaOdontologos = new ArrayList<>();

    public OdontologoDaoEnMemoria() {
        Odontologo odontologoUno = new Odontologo(1L, 5609L, "Andrea", "Sanchez");
        Odontologo odontologoDos = new Odontologo(2L, 7890L, "Luz", "Dano");
        Odontologo odontologoTres = new Odontologo(3L, 2508L, "Fabio", "Sans");
        Odontologo odontologoCuatro = new Odontologo(4L, 5968L, "Eduard", "Hernandez");
        Odontologo odontologoCinco = new Odontologo(5L, 6789L, "Javier", "Girado");

        listaOdontologos.add(odontologoUno);
        listaOdontologos.add(odontologoDos);
        listaOdontologos.add(odontologoTres);
        listaOdontologos.add(odontologoCuatro);
        listaOdontologos.add(odontologoCinco);
    }

    @Override
    public Odontologo registrar(Odontologo odontologo){
        Long id = Long.valueOf(listaOdontologos.size() + 1);
        Odontologo odontologoARegistrar = new Odontologo(id, odontologo.getNumeroDeMatricula(), odontologo.getNombre(), odontologo.getApellido());
        LOGGER.info("Se guardó correctamente" + odontologoARegistrar);
        return odontologoARegistrar;
    }

    @Override
    public List<Odontologo> listarTodos(){
        LOGGER.info("Estos son los odontologos obtenidos correctamente" + listaOdontologos);
        return listaOdontologos;
    }

}
