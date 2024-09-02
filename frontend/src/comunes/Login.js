import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../login.css";

export default function Login() {
  const urlBase = "http://localhost:8080/sipress-app/auth/login";
  let navigate = useNavigate();

  const [usuario, setUsuario] = useState({
    email: "",
    password: "",
  });

  const { email, password } = usuario;

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
        throw new Error("Error en la autenticación");
      }

      const loginResponse = await response.json();
      const token = loginResponse.token;
      localStorage.setItem("token", token);
      navigate("/inicio");
    } catch (error) {
      console.error("Error en la autenticación", error);
    }
  };

  return (
    <section className="login-page">
      <div className="container-fluid ps-md-0 bg-dark text-light">
        <div className="row g-0">
          <div className="col-md-6 bg-image"></div>
          <div className="col-md-6">
            <div className="login d-flex align-items-center py-5">
              <div className="container">
                <div className="row">
                  <div className="col-md-9 col-lg-8 mx-auto">
                    <h3 className="login-heading mb-4">Bienvenido a Sipress</h3>
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

                      <div className="form-floating mb-3 text-dark">
                        <input
                          type="password"
                          className="form-control"
                          id="password"
                          name="password"
                          placeholder="Contrasena"
                          required={true}
                          value={password}
                          onChange={handleChange}
                          autoComplete="off"
                          inputMode="password"
                        />
                        <label htmlFor="floatingPassword">Contraseña</label>
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
    </section>
  );
}
