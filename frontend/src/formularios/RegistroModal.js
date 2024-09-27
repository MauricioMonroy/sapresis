import React, { useState, useRef } from "react";
import axios from "axios";
import { toast } from "react-toastify";

/**
 * Componente funcional que permite registrar un usuario
 * @param {Function} props.handleChange - Función para manejar el cambio en los campos del formulario
 * @param {Function} props.handleSubmit - Función para manejar el envío del formulario
 * @param {Function} props.showPassword - Función para mostrar u ocultar la contraseña
 * @param {Function} props.showConfirmPassword - Función para mostrar u ocultar la confirmación de contraseña
 * @param {Function} props.response - Respuesta de la petición al servidor
 * @param {Object} props.formData - Objeto con los datos del formulario
 * @returns El componente de formulario para registrar un usuario
 * @requires react, axios, react-toastify, useNavigate, useState
 * @version 1.0
 * */

export default function Registro(onUsuarioRegistered) {
  const modalRef = useRef(null);

  const [formData, setFormData] = useState({
    nombreCompleto: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const { nombreCompleto, email, password, confirmPassword } = formData;

  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    // Verificación de que los campos no estén vacíos
    if (!nombreCompleto || !email || !password || !confirmPassword) {
      toast.warning("Por favor, complete todos los campos");
      return;
    }
    // Verificación de que las contraseñas coincidan
    if (password !== confirmPassword) {
      toast.warning("Las contraseñas no coinciden");
      return;
    }

    try {
      const response = await axios.post(
        process.env.REACT_APP_API_URL + "/sapresis/auth/registro",
        {
          nombreCompleto,
          email,
          password,
        }
      );

      if (response.status === 200) {
        toast.success("Usuario registrado con éxito");
        return;
      }
    } catch (error) {
      console.error("Error al registrar el usuario:", error);
      toast.error("Error al registrar el usuario");
    }

    // Cerrar el modal manualmente
    const modalElement = modalRef.current;
    if (modalElement) {
      const modalInstance = new window.bootstrap.Modal(modalElement);
      modalInstance.hide();
    }
    onUsuarioRegistered();
  };

  return (
    <div
      className="modal fade"
      id="RegistroModal"
      tabIndex="-1"
      aria-labelledby="RegistroModalLabel"
      aria-hidden="true"
      ref={modalRef}>
      <div className="modal-dialog modal-lg">
        <div className="modal-content bg-light">
          <div className="modal-header">
            <h1 className="modal-title fs-5" id="RegistroModalLabel">
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

          <form onSubmit={handleSubmit} id="registroForm" autoComplete="off">
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
                  onChange={handleChange}
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
                  onChange={handleChange}
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
                  onChange={handleChange}
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
                  onChange={handleChange}
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
                <i className="fa-solid fa-folder-plus"></i> Registrarse
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
