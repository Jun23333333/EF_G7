package com.example.ef_g7.Daos;

import com.example.ef_g7.Beans.Cartelera;
import com.example.ef_g7.Beans.Pelicula;
import com.example.ef_g7.Beans.Cine;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieDao extends DaoBase {

    public ArrayList<Pelicula> listarPeliculas() {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM pelicula");) {

            while (rs.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt(1));
                pelicula.setNombre(rs.getString(2));

                listaPeliculas.add(pelicula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPeliculas;
    }

    public void contarTresD() {

        String sql = "SELECT count(*) FROM pelicula p \n"
                + "left join cartelera c on (c.idpelicula = p.idpelicula) \n"
                + "where c.3d = 1");) {


    }



}