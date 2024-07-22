import React from "react";

const Navbar = () => {
  return (
    <nav className="navbar navbar-expand-lg fixed-top navbar-dark" id="menu">
      <div className="container-md">
        <a className="navbar-brand" href="index.jsp">
          <img
            src={`${process.env.PUBLIC_URL}/images/nb-logo.png`}
            width="384"
            height="116"
            className="logo-imagen"
            alt="nb-logo"
          />
        </a>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <a className="nav-link" aria-current="page" href="index.jsp">
                Inicio
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="ServletUsuario">
                Usuarios
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="ServletPaciente">
                Pacientes
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="ServletEmpleado">
                Personal
              </a>
            </li>
            <li className="nav-item dropdown">
              <a
                className="nav-link dropdown-toggle"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false">
                Servicios
              </a>
              <ul className="dropdown-menu">
                <li>
                  <a className="dropdown-item" href="ServletHistorial">
                    Gestión de Historias Clínicas
                  </a>
                </li>
                <li>
                  <a
                    className="dropdown-item disabled"
                    aria-disabled="true"
                    href="#">
                    Gestión de Citas médicas
                  </a>
                </li>
              </ul>
            </li>
          </ul>
          <form
            className="d-flex"
            role="search"
            action="ServletBuscar"
            method="GET">
            <input
              className="form-control me-2"
              type="search"
              name="query"
              placeholder="Buscar registro..."
              aria-label="Search"
            />
            <button className="btn btn-outline-success" type="submit">
              <i className="fa-solid fa-magnifying-glass"></i>
            </button>
          </form>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
