package com.backend.clinicaDental.repository.impl;

import com.backend.clinicaDental.dbconnection.H2Connection;
import com.backend.clinicaDental.entity.Odontologo;
import com.backend.clinicaDental.repository.IDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    private final Logger LOGGER = Logger.getLogger(Odontologo.class);

   @Override
   public Odontologo registrar(Odontologo odontologo){
       Connection connection = null;
       Odontologo odontologoRegistrado = null;

       try {
           connection = H2Connection.getConnection();
           connection.setAutoCommit(false);

           PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ODONTOLOGOS (NUMERODEMATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
           preparedStatement.setLong(1, odontologo.getNumeroDeMatricula());
           preparedStatement.setString(2, odontologo.getNombre());
           preparedStatement.setString(3, odontologo.getApellido());

           preparedStatement.execute();

           odontologoRegistrado = new Odontologo(odontologo.getNumeroDeMatricula(),odontologo.getNombre(), odontologo.getApellido());

           ResultSet resultSet = preparedStatement.getGeneratedKeys();
           while(resultSet.next()) {
               odontologoRegistrado.setId(resultSet.getLong("id"));
           }

           connection.commit();

           LOGGER.info("Se ha registrado el Odontologo: " + odontologoRegistrado);
       } catch (Exception e) {
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

       return odontologoRegistrado;
     }


    @Override
    public List<Odontologo> listarTodos() {

        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();
        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                Odontologo odontologo = crearObjetoOdontologo(resultSet);
                odontologos.add(odontologo);
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
        LOGGER.info("Listado de todos los Odontologos: " + odontologos);

        return odontologos;
    }

    private Odontologo crearObjetoOdontologo(ResultSet resultSet) throws SQLException{
        return new Odontologo(resultSet.getLong("id"),resultSet.getLong("numeroDeMatricula"), resultSet.getString("nombre"), resultSet.getString("apellido"));
    }
   }

