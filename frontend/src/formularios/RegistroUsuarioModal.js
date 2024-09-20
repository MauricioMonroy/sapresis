import axios from "axios";
import React, { useState, useRef } from "react";
import { toast } from "react-toastify";

/**
 * Componente funcional que renderiza el modal para agregar un usuario
 * @param {Object} props Las propiedades del componente
 * @param {Function} props.onUsuarioAdded Función que se ejecuta cuando se agrega un usuario
 * @returns El componente de formulario para agregar un usuario
 * @requires react, axios, react-toastify, useRef, useState
 * @version 1.0
 * */

export default function Registro({ onUsuarioAdded }) {
  const modalRef = useRef(null);

  const [usuario, setUsuario] = useState({
    nombreCompleto: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const { nombreCompleto, email, password, confirmPassword } = usuario;

  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);

  const onInputChange = (e) => {
    setUsuario({
      ...usuario,
      [e.target.name]: e.target.value,
    });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    // Verificación de que las contraseñas coincidan
    if (password !== confirmPassword) {
      toast.warning("Las contraseñas no coinciden");
      return;
    }
    const urlBase = "https://sipress-backend.onrender.com/sipress-app/auth/registro";
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
    toast.success("Registro agregado correctamente");
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
            <div className="modal-body">
              <div className="form-floating form-group mb-3">
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

              <div className="form-floating form-group mb-3">
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

              <div className="form-floating form-group mb-3 position-relative">
                <input
                  type={showPassword ? "text" : "password"}
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
                <button
                  type="button"
                  className="btn position-absolute end-0 top-0 mt-2 me-2"
                  onClick={() => setShowPassword(!showPassword)}
                  style={{
                    border: "none",
                    background: "transparent",
                  }}>
                  {showPassword ? (
                    <i className="fa-regular fa-eye-slash"></i>
                  ) : (
                    <i className="fa-regular fa-eye"></i>
                  )}
                </button>
              </div>

              <div className="form-floating form-group mb-3 position-relative">
                <input
                  type={showConfirmPassword ? "text" : "password"}
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
                <button
                  type="button"
                  className="btn position-absolute end-0 top-0 mt-2 me-2"
                  onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                  style={{
                    border: "none",
                    background: "transparent",
                  }}>
                  {showConfirmPassword ? (
                    <i className="fa-regular fa-eye-slash"></i>
                  ) : (
                    <i className="fa-regular fa-eye"></i>
                  )}
                </button>
              </div>
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
