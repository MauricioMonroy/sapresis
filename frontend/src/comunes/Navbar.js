import React from "react";
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";

/**
 * Componente funcional que representa la barra de navegación de la aplicación.
 * @returns El componente con la barra de navegación
 * @requires react, Link, useEffect, useState, axios
 * @version 1.0
 */

const Navbar = () => {
  const [role, setRole] = useState("");

  useEffect(() => {
    const token = localStorage.getItem("token");
    axios
      .get("https://sipress-backend.onrender.com/sipress-app/usuarios/perfil", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setRole(response.data.role);
        console.log("Rol del usuario:", response.data.role);
      })
      .catch((error) => {
        console.error("Error al obtener el rol del usuario", error);
      });
  }, []);

  return (
    <nav className="navbar navbar-expand-lg fixed-top navbar-dark" id="menu">
      <div className="container-md">
        <a className="navbar-brand" href="/inicio">
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
            {role.nombre === "SUPERADMIN" && (
              <li className="nav-item">
                <Link className="nav-link" to="/gestion-usuarios">
                  Usuarios
                </Link>
              </li>
            )}
          </ul>
          <div className="container">
            <button
              onClick={() => {
                localStorage.removeItem("token");
                sessionStorage.removeItem("token");
                window.location.href = "/logout-success";
              }}
              className="btn btn-outline-light">
              <span className="h6">
                Salir de la aplicación{" "}
                <i className="fa-solid fa-arrow-right-from-bracket"></i>{" "}
              </span>
            </button>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
