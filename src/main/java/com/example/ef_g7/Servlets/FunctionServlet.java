package com.example.ef_g7.Servlets;

import com.example.ef_g7.Daos.Cadena;
import com.example.ef_g7.Daos.Cartelera;
import com.example.ef_g7.Daos.Rol;

import com.example.ef_g7.Beans.Cartelera;
import com.example.ef_g7.Beans.Cine;
import com.example.ef_g7.Beans.Cadena;
import com.example.ef_g7.Beans.Empleado;
import com.example.ef_g7.Beans.Pelicula;
import com.example.ef_g7.Beans.Rol;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;


@WebServlet(name = "FunctionServlet", urlPatterns = {"/FunctionServlet", ""})
public class FunctionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        RequestDispatcher view;
        FunctionDao functionDao = new FunctionDao();
        MovieDao movieDao = new MovieDao();
        CinemaDao cinemaDao = new CinemaDao();

        switch (action) {
            case "lista":
                request.setAttribute("listaFuncion", functionDao.listarFunciones());
                request.setAttribute("listaPeliculas",movieDao.obtenerPeliculas());
                view = request.getRequestDispatcher("functions/lista.jsp");
                view.forward(request, response);
                break;
            case "agregar":
                request.setAttribute("listaPeliculas", movieDao.listarPeliculas());
                request.setAttribute("listaCines", cinemaDao.listaCines());
                request.setAttribute("listaFunciones", functionDao.listarFunciones());

                view = request.getRequestDispatcher("functions/formularioNuevo.jsp");
                view.forward(request, response);
                break;
            case "editar":
                if (request.getParameter("id") != null) {
                    String employeeIdString = request.getParameter("id");
                    int functionId = 0;
                    try {
                        functionId = Integer.parseInt(employeeIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("EmployeeServlet");
                    }

                    Function fun = functionDao.obtenerFuncion(functionId);

                    if (fun != null) {
                        request.setAttribute("funcion", fun);
                        request.setAttribute("listaPeliculas", movieDao.listarPeliculas());
                        request.setAttribute("listaCines", cinmeaDao.listaCines());
                        request.setAttribute("listaFunciones", functionDao.listarFunciones());
                        view = request.getRequestDispatcher("functions/formularioEditar.jsp");
                        view.forward(request, response);
                    } else {
                        response.sendRedirect("FunctionServlet");
                    }

                } else {
                    response.sendRedirect("FunctionServlet");
                }

                break;
            case "borrar":
                if (request.getParameter("id") != null) {
                    String employeeIdString = request.getParameter("id");
                    int functionId = 0;
                    try {
                        functionId = Integer.parseInt(employeeIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("FunctionServlet?err=Error al borrar la funcion");
                    }

                    Function fun = functionDao.obtenerFuncion(functionId);

                    if (fun != null) {
                        try {
                            functionDao.borrarFuncion(functionId);
                            response.sendRedirect("FunctionServlet?msg=Funcion borrada exitosamente");
                        } catch (SQLException e) {
                            response.sendRedirect("FunctionServlet?err=Error al borrar la funcion");
                        }
                    }
                }else{
                    response.sendRedirect("FunctionServlet?err=Error al borrar la funcion");
                }
                break;
            default:
                response.sendRedirect("FunctionServlet");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cartelera f = new Cartelera();

        String idPelicula = request.getParameter("job_id");
        Pelicula pel = new Pelicula(Integer.parseInt(idPelicula));
        f.setPelicula(pel);

        String idCine = request.getParameter("cinema_id");
        Cine cine = new Cine(Integer.parseInt(idCine));
        f.setCine(cine);

        f.setTresD(request.getParameter("tresD"));
        f.setDoblada(request.getParameter("doblada"));
        f.setSubtitulada(request.getParameter("subtitulada"));
        f.setHorario(request.getParameter("horario"));

        FunctionDao functionDao = new FunctionDao();

        if (request.getParameter("employee_id") == null) {
            try {
                functionDao.guardarFuncion(f);
                response.sendRedirect("FunctionServlet?msg=Funcion creada exitosamente");
            } catch (SQLException ex) {
                response.sendRedirect("FunctionServlet?err=Error al crear funcion");
            }
        } else {
            f.setIdCadena(Integer.parseInt(request.getParameter("function_id")));
            try {
                functionDao.actualizarFuncion(f);
                response.sendRedirect("FunctionServlet?msg=Funcion actualizada exitosamente");
            } catch (SQLException ex) {
                response.sendRedirect("FunctionServlet?err=Error al actualizar funcion");
            }
        }

    }

}
