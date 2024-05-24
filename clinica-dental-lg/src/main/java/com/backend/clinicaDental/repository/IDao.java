package com.backend.clinicaDental.repository;

import java.util.List;

public interface IDao<T> {
    T registrar(T t);
    List<T> listarTodos();
}
