import React from "react";

const Navbar = () => {
  return (
    <nav className="navbar navbar-expand-lg fixed-top navbar-dark" id="menu">
      <div className="container-md">
        <a className="navbar-brand" href="/">
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
            <li className="nav-item dropdown">
              <a
                className="nav-link dropdown-toggle"
                href="/instituciones"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false">
                Institución
              </a>
              <ul className="dropdown-menu">
                <li>
                  <a className="dropdown-item" href="/instituciones">
                    Sedes
                  </a>
                </li>
                <li>
                  <a className="dropdown-item" href="/dependencias">
                    Dependencias
                  </a>
                </li>
                <li>
                  <a className="dropdown-item" href="/consultorios">
                    Consultorios
                  </a>
                </li>
                <li>
                  <a className="dropdown-item" href="/epsS">
                    EPS
                  </a>
                </li>
              </ul>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/personalS">
                Personal
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/doctores">
                Doctores
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/pacientes">
                Pacientes
              </a>
            </li>
            <li className="nav-item dropdown">
              <a
                className="nav-link dropdown-toggle"
                href="/funciones"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false">
                Otras Funciones
              </a>
              <ul className="dropdown-menu">
                <li>
                  <a className="dropdown-item" href="/consultas">
                    Consultas
                  </a>
                </li>
                <li>
                  <a className="dropdown-item" href="/formulas">
                    Fórmulas médicas
                  </a>
                </li>
                <li>
                  <a className="dropdown-item" href="/facturas">
                    Facturación
                  </a>
                </li>
              </ul>
            </li>
          </ul>
          <form className="d-flex" role="search" action="buscar" method="GET">
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
