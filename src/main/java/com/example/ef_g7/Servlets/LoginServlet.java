package com.example.ef_g7.Servlets;

import com.example.ef_g7.Beans.Empleado;
import com.example.ef_g7.Daos.LoginDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") != null ? request.getParameter("action") : "login";

        HttpSession session = request.getSession();

        switch (action){
            case "login":
                Empleado usuario = (Empleado) session.getAttribute("empleadoLogueado");

                if(usuario != null && usuario.getIdEmpleado() != 0){
                    response.sendRedirect(request.getContextPath());
                }else{
                    RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
                    rd.forward(request, response);
                }
                break;
            case "logout":
                session.invalidate();
                response.sendRedirect(request.getContextPath());
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("Usuario");
        String password = request.getParameter("Contra");

        LoginDao loginDao = new LoginDao();

        Empleado empleado =loginDao.validarUsuarioPassword(username, password);
        empleado.setRol(loginDao.obtenerRol(empleado.getIdEmpleado()));

        if (empleado != null) { //existe usuario y password
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", empleado);
            session.setMaxInactiveInterval(60 * 10);

            response.sendRedirect(request.getContextPath() + "/MenuServlet");

        } else {
            response.sendRedirect(request.getContextPath() + "/LoginServlet?error");
        }

    }
}
