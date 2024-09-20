import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
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

export default function Registro() {
  const navigate = useNavigate();

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
    // Verificación de que las contraseñas coincidan
    if (password !== confirmPassword) {
      toast.warning("Las contraseñas no coinciden");
      return;
    }

    try {
      const response = await axios.post(
        "https://sipress-backend.onrender.com/sipress-app/auth/registro",
        {
          nombreCompleto,
          email,
          password,
        }
      );

      if (response.status === 200) {
        toast.success("Usuario registrado con éxito");
        const timer = setTimeout(() => {
          navigate("/login");
        }, 3000); // Redirige después de 3 segundos
        return () => clearTimeout(timer);
      }
    } catch (error) {
      console.error("Error al registrar el usuario:", error);
      toast.error("Error al registrar el usuario");
    }
  };

  return (
    <div className="p-4 mb-2 mt-5">
      <div className="row justify-content-center">
        <div className="col-lg-9">
          <div className="card mt-3" id="details">
            <div className="card-header">
              <h4>Registro de Usuarios</h4>
            </div>

            <form onSubmit={handleSubmit} id="registroForm" autoComplete="off">
              <div className="card-body">
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
                    className="btn position-absolute end-0 top-0 mt-2 me-1"
                    onClick={() => setShowPassword(!showPassword)}
                    style={{ border: "none", background: "#113733" }}>
                    {showPassword ? (
                      <i className="fa-regular fa-eye-slash"></i>
                    ) : (
                      <i className="fa-regular fa-eye"></i>
                    )}
                  </button>
                </div>

                <div className="form-floating form-group mb-3">
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
                    className="btn position-absolute end-0 top-0 mt-2 me-1"
                    onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                    style={{
                      border: "none",
                      background: "#113733",
                    }}>
                    {showConfirmPassword ? (
                      <i className="fa-regular fa-eye-slash"></i>
                    ) : (
                      <i className="fa-regular fa-eye"></i>
                    )}
                  </button>
                </div>

                <div className="d-grid">
                  <button
                    className="btn btn-lg btn-primary btn-login text-uppercase fw-bold mb-2"
                    type="submit">
                    Registrarse
                  </button>
                </div>
                <div className="text-center">
                  <a className="small" href="/login">
                    Iniciar Sesión
                  </a>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
