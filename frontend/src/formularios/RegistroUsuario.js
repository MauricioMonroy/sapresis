import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Registro() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    nombreCompleto: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const { nombreCompleto, email, password, confirmPassword } = formData;

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
      alert("Las contraseñas no coinciden");
      return;
    }

    try {
      const response = await axios.post(
        "http://localhost:8080/sipress-app/auth/registro",
        {
          nombreCompleto,
          email,
          password,
        }
      );

      if (response.status === 200) {
        alert("Usuario registrado con éxito");
        const timer = setTimeout(() => {
          navigate("/login");
        }, 3000); // Redirige después de 3 segundos
        return () => clearTimeout(timer);
      }
    } catch (error) {
      console.error("Error al registrar el usuario:", error);
      alert("Hubo un problema al registrar el usuario");
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
              <div className="form-floating mb-3 text-dark">
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

              <div className="form-floating mb-3 text-dark">
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

              <div className="form-floating mb-3 text-dark">
                <input
                  type="password"
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
                  onChange={handleChange}
                  autoComplete="off"
                  inputMode="password"
                />
                <label htmlFor="confirmPassword">Confirmar Contraseña</label>
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
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
