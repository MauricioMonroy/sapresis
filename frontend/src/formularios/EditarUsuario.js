import axios from "axios";
import React, { useEffect, useState, useCallback } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditarUsuario() {
  const urlBase = "http://localhost:8080/sipress-app/usuarios";

  let navigate = useNavigate();
  const { id } = useParams();

  const [usuario, setUsuario] = useState({
    id: "",
    nombreCompleto: "",
    email: "",
    password: "",
    rol: {
      id: "",
      nombre: "",
    },
  });

  const {
    nombreCompleto,
    email,
    password,
    rol: { nombre },
  } = usuario;

  const [roles, setRoles] = useState([]);

  const cargarRoles = useCallback(async () => {
    const token = localStorage.getItem("token");
    try {
      const resultado = await axios.get(
        "http://localhost:8080/sipress-app/roles",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setRoles(resultado.data);
    } catch (error) {
      console.error("Error al cargar los roles:", error);
    }
  }, []);

  const cargarUsuario = useCallback(async () => {
    const token = localStorage.getItem("token");
    try {
      const resultado = await axios.get(`${urlBase}/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setUsuario(resultado.data);
    } catch (error) {
      console.error("Error al cargar el usuario:", error);
    }
  }, [id]);

  useEffect(() => {
    cargarRoles();
    cargarUsuario();
  }, [cargarRoles, cargarUsuario]);

  const onInputChange = (e) => {
    const { name, value } = e.target;
    setUsuario((prevUsuario) => ({
      ...prevUsuario,
      [name]: value,
    }));
  };

  const onSubmit = async (e) => {
    const token = localStorage.getItem("token");
    e.preventDefault();
    await axios.put(`${urlBase}/${id}`, usuario, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    navigate("/usuarios");
  };

  return (
    <div className="p-4 mb-2 mt-5">
      <div className="row justify-content-center">
        <div className="col-lg-9">
          <div className="card mt-3" id="details">
            <div className="card-header">
              <h4>Modificar Registro</h4>
            </div>
            <form onSubmit={(e) => onSubmit(e)}>
              <div className="card-body">
                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="nombreCompleto"
                    name="nombreCompleto"
                    placeholder="Nombre completo del Usuario"
                    required={true}
                    value={nombreCompleto}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="nombreCompleto">
                    Nombre completo del Usuario
                  </label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="email"
                    className="form-control"
                    id="email"
                    name="email"
                    placeholder="Correo Electrónico"
                    required={true}
                    value={email}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="email">Correo Electrónico</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="password"
                    className="form-control"
                    id="password"
                    name="password"
                    placeholder="Contraseña"
                    required={true}
                    value={password}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="password">Contraseña</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <select
                    className="form-select"
                    id="rol"
                    name="rol"
                    value={nombre}
                    onChange={(e) => onInputChange(e)}>
                    <option value="">Seleccione un Rol</option>
                    {roles.map((rol) => (
                      <option key={rol.id} value={rol.nombre}>
                        {rol.nombre}
                      </option>
                    ))}
                  </select>
                  <label htmlFor="rol">Rol</label>
                </div>

                <button type="submit" className="btn btn-primary">
                  <i className="fa-regular fa-floppy-disk"></i> Guardar Cambios
                </button>
                <Link to="/gestion-usuarios">
                  <i className="fa-solid fa-triangle-exclamation"></i> Cancelar
                </Link>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
