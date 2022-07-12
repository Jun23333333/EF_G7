<%@ page import="com.example.ef_g7.Beans.Pelicula" %>
<%@ page import="com.example.ef_g7.Beans.Cine" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaPeliculas" scope="request" type="java.util.ArrayList<com.example.ef_g7.Beans.Pelicula>" />
<jsp:useBean id="listaCines" scope="request" type="java.util.ArrayList<com.example.ef_g7.Beans.Cine>" />
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
          crossorigin="anonymous">


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
            <a class="navbar-brand" href="#">Crear nueva funcion</a>
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

    <div class="card-body p-4 p-md-5">
        <form method="POST" action="<%=request.getContextPath()%>/FuncionServlet?action=crear">
            <div class="row">
                <div class="col-md-6 mb-1">

                    <div class="form-outline mb-4">
                        <label class="form-label" for="pelicula">Pelicula</label>
                        <select name="pelicula"
                                id="pelicula">
                            <option selected>Seleccionar pelicula</option>
                            <% for (Pelicula peli : listaPeliculas){%>
                                <option><%=peli.getNombre()%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="form-outline mb-4">
                        <label class="form-label" for="Cine">Cine</label>
                        <select name="Cine"
                                id="Cine">
                            <option selected>Seleccionar Cine</option>
                            <% for (Cine cine : listaCines){%>
                            <option><%=cine.getNombre()%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="form-outline mb-4">
                        <label class="form-label" for="3d">Es 3D?</label>
                        <select name="3d"
                                id="3d">
                            <option>Si</option>
                            <option>No</option>
                        </select>
                    </div>
                    <div class="form-outline mb-4">
                        <label class="form-label" for="doblada">Es Doblada?</label>
                        <select name="doblada"
                                id="doblada">
                            <option>Si</option>
                            <option>No</option>

                        </select>
                    </div>
                    <div class="form-outline mb-4">
                        <label class="form-label" for="horario">Horario</label>
                        <input name="horario"
                               type="text"
                               id="horario"
                               class="form-control"
                               placeholder="Ingrese el horario"/>
                    </div>
                    <div class="">
                        <a href="<%=request.getContextPath()%>/FuncionServlet" class="btn btn-danger">Regresar</a>
                        <button type="submit" class="btn btn-tele">Crear Funcion</button>
                    </div>

        </form>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
                crossorigin="anonymous"></script>

</body>
</html>
