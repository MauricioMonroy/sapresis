import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import "../login.css";

/**
 * Componente funcional que representa la vista de inicio de sesión
 * @returns El componente con la interfaz de inicio de sesión
 * @requires react, useNavigate, toast
 * @version 1.0
 */

export default function Login() {
  const urlBase = "https://sipress-backend.onrender.com/sipress-app/auth/login";
  let navigate = useNavigate();

  const [usuario, setUsuario] = useState({
    email: "",
    password: "",
  });

  const { email, password } = usuario;

  const [showPassword, setShowPassword] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUsuario({ ...usuario, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(urlBase, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(usuario),
      });

      if (!response.ok) {
        const errorData = await response.json();
        toast.error(errorData.description || "Las credenciales no son válidas");
        return;
      }

      const loginResponse = await response.json();
      const token = loginResponse.token;
      localStorage.setItem("token", token);
      navigate("/inicio");
      toast.success("Bienvenido a Sipress");
    } catch (error) {
      console.error("Error en la autenticación", error);
      toast.error("Error de conexión");
    }
  };

  return (
    <section className="login-page">
      <div className="container-fluid ps-md-0 bg-dark text-light">
        <div className="row g-0">
          <div className="col-md-6 d-none d-md-flex bg-image"></div>
          <div className="col-md-6">
            <div className="login d-flex align-items-center py-5">
              <div className="container">
                <div className="row">
                  <div className="card-body">
                    <div className="col-md-9 col-lg-8 mx-auto">
                      <h3 className="login-heading mb-4">
                        Bienvenido a Sipress
                      </h3>
                      <p>Por favor ingrese sus credenciales</p>
                      <form
                        onSubmit={handleSubmit}
                        id="loginForm"
                        autoComplete="off">
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
                          <label htmlFor="floatingInput">
                            Correo electrónico
                          </label>
                        </div>

                        <div className="form-floating mb-3 text-dark position-relative">
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

                        <div className="d-grid">
                          <button
                            className="btn btn-lg btn-primary btn-login text-uppercase fw-bold mb-2"
                            type="submit">
                            Ingresar
                          </button>
                          <div className="text-center">
                            <a className="small" href="/registro">
                              Registrarse
                            </a>
                          </div>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
