import axios from "axios";
import React, { useState, useRef } from "react";

export default function Registro({ onUsuarioAdded }) {
  const modalRef = useRef(null);

  const [usuario, setUsuario] = useState({
    nombreCompleto: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const { nombreCompleto, email, password, confirmPassword } = usuario;

  const onInputChange = (e) => {
    setUsuario({
      ...usuario,
      [e.target.name]: e.target.value,
    });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    const urlBase = "http://localhost:8080/sipress-app/auth/registro";
    const token = localStorage.getItem("token");
    await axios.post(urlBase, usuario, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    // Cerrar el modal manualmente
    const modalElement = modalRef.current;
    if (modalElement) {
      const modalInstance = new window.bootstrap.Modal(modalElement);
      modalInstance.hide();
    }

    // Llamar a la función de actualización de la lista
    onUsuarioAdded();
  };

  return (
    <div
      className="modal fade"
      id="RegistroUsuarioModal"
      tabIndex="-1"
      aria-labelledby="RegistroUsuarioModalLabel"
      aria-hidden="true"
      ref={modalRef}>
      <div className="modal-dialog modal-lg">
        <div className="modal-content bg-light">
          <div className="modal-header">
            <h1 className="modal-title fs-5" id="RegistroUsuarioModalLabel">
              Registro de Usuarios
            </h1>
            <button
              type="button"
              className="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close">
              <span></span>
            </button>
          </div>
          <form onSubmit={(e) => onSubmit(e)} autoComplete="off">
            <div className="form-floating mb-3 text-dark">
              <input
                type="text"
                className="form-control"
                id="nombreCompleto"
                name="nombreCompleto"
                placeholder="Nombre completo"
                required={true}
                value={nombreCompleto}
                onChange={(e) => onInputChange(e)}
                autoComplete="off"
                inputMode="text"
              />
              <label htmlFor="nombreCompleto">Nombre completo</label>
            </div>

            <div className="form-floating mb-3 text-dark">
              <input
                type="email"
                className="form-control"
                id="email"
                name="email"
                placeholder="nombre@ejemplo.com"
                required={true}
                value={email}
                onChange={(e) => onInputChange(e)}
                autoComplete="off"
                inputMode="email"
              />
              <label htmlFor="email">Correo electrónico</label>
            </div>

            <div className="form-floating mb-3 text-dark">
              <input
                type="password"
                className="form-control"
                id="password"
                name="password"
                placeholder="Contraseña"
                required={true}
                value={password}
                onChange={(e) => onInputChange(e)}
                autoComplete="off"
                inputMode="password"
              />
              <label htmlFor="password">Contraseña</label>
            </div>

            <div className="form-floating mb-3 text-dark">
              <input
                type="password"
                className="form-control"
                id="confirmPassword"
                name="confirmPassword"
                placeholder="Confirmar Contraseña"
                required={true}
                value={confirmPassword}
                onChange={(e) => onInputChange(e)}
                autoComplete="off"
                inputMode="password"
              />
              <label htmlFor="confirmPassword">Confirmar Contraseña</label>
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary btn-sm me-3"
                data-bs-dismiss="modal">
                <i className="fa-regular fa-rectangle-xmark"></i> Cerrar
              </button>
              <button
                type="submit"
                className="btn btn-primary"
                data-bs-dismiss="modal">
                <i className="fa-solid fa-folder-plus"></i> Guardar registro
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
