
package com.example.ef_g7.Daos;

import com.example.ef_g7.Beans.Cartelera;
import com.example.ef_g7.Beans.Cine;
import com.example.ef_g7.Beans.Pelicula;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FunctionDao extends DaoBase {

    public ArrayList<Cartelera> listarFunciones() {
        ArrayList<Cartelera> listaFunciones = new ArrayList<>();

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM cartelera c \n"
                     + "left join pelicula p on (c.idpelicula = p.idpelicula) \n"
                     + "left join cines ci on (ci.idcine = c.idcine)");) {

            while (rs.next()) {
                Cartelera cartelera = new Cartelera();

                cartelera.setIdCartelera(rs.getInt(1));

                Pelicula pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt(2));
                pelicula.setNombre(rs.getString("movie_title"));

                cartelera.setPelicula(pelicula);

                Cine cine = new Cine();
                cine.setIdCine(rs.getInt(3));
                cine.setNombre(rs.getString("cine_name"));
                cartelera.setCine(cine);

                cartelera.setTresD(rs.getInt(4));
                cartelera.setDoblada(rs.getInt(5));
                cartelera.setSubtitulada(rs.getInt(6));
                cartelera.setHorario(rs.getString(7));

                listaFunciones.add(cartelera);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaFunciones;
    }

    public Cartelera obtenerFuncion(int idCartelera) {

        Cartelera cartelera = null;

        String sql = "SELECT * FROM cartelera c WHERE idCartelera = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, idCartelera);

            try (ResultSet rs = pstmt.executeQuery();) {

                if (rs.next()) {
                    cartelera.setIdCartelera(rs.getInt(1));

                    Pelicula pelicula = new Pelicula();
                    pelicula.setIdPelicula(rs.getInt(2));
                    pelicula.setNombre(rs.getString("movie_title"));

                    cartelera.setPelicula(pelicula);

                    Cine cine = new Cine();
                    cine.setIdCine(rs.getInt(3));
                    cine.setNombre(rs.getString("cine_name"));
                    cartelera.setCine(cine);

                    cartelera.setTresD(rs.getInt(4));
                    cartelera.setDoblada(rs.getInt(5));
                    cartelera.setSubtitulada(rs.getInt(6));
                    cartelera.setHorario(rs.getString(7));

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cartelera;
    }

    public void guardarFuncion(Cartelera cartelera) throws SQLException {

        String sql = "INSERT INTO cartelera (idCartelera, idpelicula, idcine, 3d, doblada, subtitulada, horario) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            this.setFunctionParams(pstmt, cartelera);
            pstmt.executeUpdate();
        }
    }

    public void actualizarFuncion(Cartelera cartelera) throws SQLException {

        String sql = "UPDATE cartelera SET idpelicula = ?, idcune = ?, 3d = ?,"
                + "doblada = ?, subtitulada = ?, horario = ? WHERE idCartelera = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            this.setFunctionParams(pstmt, cartelera);
            pstmt.setInt(7, cartelera.getIdCartelera());
            pstmt.executeUpdate();
        }
    }

    public void borrarFuncion(int IdCartelera) throws SQLException {
        String sql = "DELETE FROM cartelera WHERE employee_id = ?";
        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, IdCartelera);
            pstmt.executeUpdate();
        }
    }

    private void setFunctionParams(PreparedStatement pstmt, Cartelera cartelera) throws SQLException {


        pstmt.setInt(1, cartelera.getIdCartelera());

        pstmt.setInt(2, cartelera.getPelicula().getIdPelicula());

        pstmt.setInt(3, cartelera.getCine().getIdCine());

        pstmt.setInt(4, cartelera.getTresD());
        pstmt.setInt(5, cartelera.getDoblada());
        pstmt.setInt(6, cartelera.getSubtitulada());
        pstmt.setString(7, cartelera.getHorario());
    }

    public Cartelera obtenerFuncionesTresD() {

        Cartelera cartelera = null;

        String sql = "SELECT * FROM cartelera WHERE 3d = 1";
        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cartelera = new Cartelera();
                    cartelera.setIdCartelera(rs.getInt(1));

                    Pelicula pelicula = new Pelicula();
                    pelicula.setIdPelicula(rs.getInt(2));
                    pelicula.setNombre(rs.getString("movie_title"));

                    cartelera.setPelicula(pelicula);

                    Cine cine = new Cine();
                    cine.setIdCine(rs.getInt(3));
                    cine.setNombre(rs.getString("cine_name"));
                    cartelera.setCine(cine);

                    cartelera.setTresD(rs.getInt(4));
                    cartelera.setDoblada(rs.getInt(5));
                    cartelera.setSubtitulada(rs.getInt(6));
                    cartelera.setHorario(rs.getString(7));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cartelera;
    }


}