package com.backend.clinicaDental.repository.impl;

import com.backend.clinicaDental.dbconnection.H2Connection;
import com.backend.clinicaDental.entity.Domicilio;
import com.backend.clinicaDental.repository.IDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DomicilioDaoH2 implements IDao<Domicilio> {
    private final Logger LOGGER = LoggerFactory.getLogger(DomicilioDaoH2.class);

    @Override
    public Domicilio registrar(Domicilio domicilio) {
        Connection connection = null;
        Domicilio domicilioRegistrado = null;

        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setInt(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            domicilioRegistrado = new Domicilio(domicilio.getCalle(), domicilio.getNumero(), domicilio.getLocalidad(), domicilio.getProvincia());

            while (resultSet.next()){
                domicilioRegistrado.setId(resultSet.getLong("id"));
            }
            connection.commit();
            LOGGER.info("Se ha registrado el domicilio: " + domicilioRegistrado);

        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Tuvimos un problema");
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }

        return domicilioRegistrado;


    }

    @Override
    public Domicilio buscarPorId(Long id) {
        Domicilio domicilioBuscado = null;
        Connection connection = null;

        try{
            connection = H2Connection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM DOMICILIOS WHERE ID = ?");
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                domicilioBuscado = new Domicilio(resultSet.getLong(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5));
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }

        if(domicilioBuscado == null) LOGGER.error("No se ha encontrado el domicilio con id: " + id);
        else LOGGER.info("Se ha encontrado el domicilio: " + domicilioBuscado);

        return domicilioBuscado;
    }

    @Override
    public List<Domicilio> listarTodos() {
        Connection connection = null;
        List<Domicilio> domicilios = new ArrayList<>();

        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM DOMICILIOS");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Domicilio domicilio = new Domicilio(resultSet.getLong("id"), resultSet.getString("calle"), resultSet.getInt("numero"),resultSet.getString("localidad"), resultSet.getString("provincia"));

                domicilios.add(domicilio);
            }



        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }

        LOGGER.info("Listado de domicilios obtenido: " + domicilios);

        return domicilios;
    }

}
