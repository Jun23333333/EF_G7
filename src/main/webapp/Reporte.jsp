<%@ page import="com.example.ef_g7.Beans.Empleado" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="cant_empleados" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="pelis_3D" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="cines_salario" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="Cadena_mayor" scope="request" type="java.lang.String"/>
<jsp:useBean id="listaEmpleados_sin" scope="request" type="java.util.ArrayList<com.example.ef_g7.Beans.Empleado>" />

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.98.0">
    <title>Navbar Template · Bootstrap v5.2</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/navbars/">





    <link href="/docs/5.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

    <!-- Favicons -->
    <link rel="apple-touch-icon" href="/docs/5.2/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
    <link rel="icon" href="/docs/5.2/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
    <link rel="icon" href="/docs/5.2/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
    <link rel="manifest" href="/docs/5.2/assets/img/favicons/manifest.json">
    <link rel="mask-icon" href="/docs/5.2/assets/img/favicons/safari-pinned-tab.svg" color="#712cf9">
    <link rel="icon" href="/docs/5.2/assets/img/favicons/favicon.ico">
    <meta name="theme-color" content="#712cf9">


    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        .b-example-divider {
            height: 3rem;
            background-color: rgba(0, 0, 0, .1);
            border: solid rgba(0, 0, 0, .15);
            border-width: 1px 0;
            box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
        }

        .b-example-vr {
            flex-shrink: 0;
            width: 1.5rem;
            height: 100vh;
        }

        .bi {
            vertical-align: -.125em;
            fill: currentColor;
        }

        .nav-scroller {
            position: relative;
            z-index: 2;
            height: 2.75rem;
            overflow-y: hidden;
        }

        .nav-scroller .nav {
            display: flex;
            flex-wrap: nowrap;
            padding-bottom: 1rem;
            margin-top: -1px;
            overflow-x: auto;
            text-align: center;
            white-space: nowrap;
            -webkit-overflow-scrolling: touch;
        }
    </style>


    <!-- Custom styles for this template -->
    <link href="navbar.css" rel="stylesheet">
</head>

<body>
 <main>

         <nav class="navbar navbar-expand-md navbar-dark bg-dark" aria-label="Fourth navbar example">
             <div class="container-fluid">
                 <a class="navbar-brand" href="#">Reporte</a>
                 <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
                     <span class="navbar-toggler-icon"></span>
                 </button>

                 <div class="collapse navbar-collapse" id="navbarsExample04">
                     <ul class="navbar-nav me-auto mb-2 mb-md-0">
                         <li class="nav-item">
                             <a class="nav-link disabled">EF_G7</a>
                         </li>
                     </ul>
                 </div>
             </div>
         </nav>
     <div class="table-responsive">
         <table class="table table-striped table-sm">
             <thead>
             <tr>
                 <th scope="col">Empleados sin jefes</th>
                 <th scope="col">Peliculas en 3D</th>
                 <th scope="col">Cines con salario mayor a 9700</th>
                 <th scope="col">Cadeba con mayor pelicula en cartelera</th>

             </tr>
             </thead>
             <tbody>
             <tr>
                 <td><button
                         class="btn btn-danger py-0 px-1"
                         type="button"
                         data-bs-toggle="modal"
                         data-bs-target="no_jefe"
                 ><%=cant_empleados%></button>
                 </td>
                 <td><%=pelis_3D%></td>
                 <td><%=cines_salario%></td>
                 <td><%=Cadena_mayor%>></td>
             </tr>
             <div class="modal fade"
                  id="no_jefe"
                  tabindex="-1"
                  aria-labelledby="sin_jefe"
                  aria-hidden="true">
                 <div class="modal-dialog">
                     <div class="modal-content border-0">
                         <div class="modal-header bg-danger text-white">
                             <h5 class="modal-title" id="sin_jefe">Empleados sin jefe</h5>
                             <button
                                     type="button"
                                     class="btn-close btn-close-white"
                                     data-bs-dismiss="modal"
                                     aria-label="Close"
                             ></button>
                         </div>
                         <table class="table table-striped table-sm">
                             <thead>
                             <tr>
                                 <th scope="col">Empleados sin jefes</th>
                             </tr>
                             </thead>
                             <tbody>
                             <% int i = 1;
                             for(Empleado empleado : listaEmpleados_sin){ %>
                             <tr>
                                 <td><%=i%></td>
                             </tr>
                             <tr>
                                 <%=empleado.getNombre() + empleado.getApellido()%>
                             </tr>
                             <% i++;}%>
                             </table>
                         <div class="modal-footer">
                             <a href="<%=request.getContextPath()%>/ReporteServlet">
                                 <button type="button"
                                         class="btn btn-light"
                                         data-bs-dismiss="modal">Regresar</button>
                             </a>

                         </div>
                     </div>
                 </div>
             </div>

             </tbody>
         </table>
     </div>
 </main>
 <script src="/docs/5.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>
</html>
