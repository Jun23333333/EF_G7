package com.example.ef_g7.Daos;

import com.example.ef_g7.Beans.Cadena;
import com.example.ef_g7.Beans.Cine;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CinemaDao extends DaoBase {

    public ArrayList<Cine> listaCines() {
        ArrayList<Cine> listaCines = new ArrayList<>();

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM cine");) {

            while (rs.next()) {
                Cine cine = new Cine();
                cine.setIdCine(rs.getInt(1));
                cine.setNombre(rs.getString(2));

                Cadena cadena = new Cadena();
                cadena.setIdCadena(rs.getInt(3));
                cadena.setNombreComercial(rs.getString("chain_name"));
                cadena.setRuc(rs.getString("chain_name"));
                cine.setCadena(cadena);

                listaCines.add(cine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCines;
    }

    public ArrayList<Cine> listaCinesRicos() {
        ArrayList<Cine> listaCines = new ArrayList<>();

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM cine c \n"
                     + "left join empleado e on (c.idcine = e.idcine) \n"
                     + "where e.salario > 9700;");) {
            while (rs.next()) {
                Cine cine = new Cine();
                cine.setIdCine(rs.getInt(1));
                cine.setNombre(rs.getString(2));

                Cadena cadena = new Cadena();
                cadena.setIdCadena(rs.getInt(3));
                cadena.setNombreComercial(rs.getString("chain_name"));
                cadena.setRuc(rs.getString("chain_name"));
                cine.setCadena(cadena);

                listaCines.add(cine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCines;
    }
}
}