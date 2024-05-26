package com.backend.clinicaDental.service.impl;

import com.backend.clinicaDental.entity.Odontologo;
import com.backend.clinicaDental.repository.IDao;
import com.backend.clinicaDental.service.IOdontologoService;

import java.util.List;

public class OdontologoService implements IOdontologoService {

    private IDao<Odontologo> odontologoIDao;

    public  OdontologoService(IDao<Odontologo> odontologoIDao){
        this.odontologoIDao = odontologoIDao;
    }

    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo){
        return odontologoIDao.registrar(odontologo);
    }

    @Override
    public List<Odontologo> listarOdontologos(){
        return odontologoIDao.listarTodos();
    }



}
