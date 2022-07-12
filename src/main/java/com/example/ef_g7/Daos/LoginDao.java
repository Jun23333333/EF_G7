package com.example.ef_g7.Daos;

import com.example.ef_g7.Beans.Cine;
import com.example.ef_g7.Beans.Empleado;
import com.example.ef_g7.Beans.Rol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao extends DaoBase{

    public Empleado validarUsuarioPassword(String username, String password) {
        Empleado empleado = null;

        String sql = "select * from empleado where dni = ?;";

        try (Connection connection = this.getConection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    empleado = new Empleado();
                    empleado.setIdEmpleado(rs.getInt(1));
                    empleado.setNombre(rs.getString(2));
                    empleado.setApellido(rs.getString(3));
                    empleado.setDni(rs.getString(4));
                    empleado.setSalario(rs.getFloat(5));
                    empleado.setFechaContrato(rs.getString(6));
                    empleado.setNombreUsuario(rs.getString(7));
                    empleado.setEdad(rs.getInt(8));
                    empleado.setActivo(rs.getBoolean(9));
                    Cine cine = new Cine();
                    cine.setIdCine(rs.getInt(10));
                    Empleado jefe = new Empleado();
                    jefe.setIdEmpleado(rs.getInt(11));
                    empleado.setCine(cine);
                    empleado.setJefe(jefe);


                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (Float.parseFloat(password) == (Float.parseFloat(empleado.getDni()) - empleado.getSalario())){
            return empleado;
        }
        else {
            return null;
        }
    }


    public Rol obtenerRol(int IdUsuario) {
        Rol rol  = new Rol();

        String sql = "select rol.nombre, rol.idrol from rol\n" +
                "inner join rolempleado r on rol.idrol = r.idrol\n" +
                "inner join empleado e on r.idempleado = e.idempleado\n" +
                "where e.idempleado = ?";

        try (Connection connection = this.getConection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {


            pstmt.setInt(1, IdUsuario);

            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    rol.setIdRol(rs.getInt(2));
                    rol.setNombre(rs.getString(1));
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rol;
    }
}
