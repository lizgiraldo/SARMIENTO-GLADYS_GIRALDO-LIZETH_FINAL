package com.backend.clinicaDental.repository;

import com.backend.clinicaDental.entity.Domicilio;

import java.util.List;

public interface IDao<T> {
    T registrar(T t);
    T buscarPorId(Long id);
    List<T> listarTodos();
}
