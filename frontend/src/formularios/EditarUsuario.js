import React, { useEffect, useState, useCallback } from "react";
import axios from "axios";
import { Link, useNavigate, useParams } from "react-router-dom";
import { toast } from "react-toastify";

/**
 * Componente funcional que permite editar un usuario
 * @returns El componente de formulario para editar un usuario
 * @requires react, axios, react-toastify, useNavigate, useParams, Link, useState, useCallback, useEffect
 * @version 1.0
 * */

export default function EditarUsuario() {
  const urlBase = "https://sipress-backend.onrender.com/sipress-app/usuarios";

  let navigate = useNavigate();
  const { id } = useParams();

  const [usuario, setUsuario] = useState({
    id: "",
    nombreCompleto: "",
    email: "",
    password: "",
    confirmPassword: "",
    createdAt: "",
    updatedAt: "",
    role: {
      id: "",
      nombre: "",
      descripcion: "",
      createdAt: "",
      updatedAt: "",
    },
  });

  const {
    nombreCompleto,
    email,
    password,
    confirmPassword,
    createdAt,
    role: { nombre },
  } = usuario;

  const cargarUsuario = useCallback(async () => {
    const token = localStorage.getItem("token");
    try {
      const resultado = await axios.get(`${urlBase}/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      setUsuario({ ...resultado.data, password: "", confirmPassword: "" });
    } catch (error) {
      console.error("Error al cargar los registros de Usuario:", error);
      toast.error("Error al cargar los datos del registro solicitado");
    }
  }, [id]);

  useEffect(() => {
    cargarUsuario();
  }, [cargarUsuario]);

  const onInputChange = (e) => {
    const { name, value } = e.target;
    if (name === "role.nombre") {
      setUsuario((prevUsuario) => ({
        ...prevUsuario,
        role: {
          ...prevUsuario.role,
          nombre: value,
        },
      }));
    } else {
      setUsuario((prevUsuario) => ({
        ...prevUsuario,
        [name]: value,
      }));
    }
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem("token");

    if (password !== confirmPassword) {
      toast.warning("Las contraseñas no coinciden");
      return;
    }

    const usuarioActualizado = { ...usuario };
    if (!password) {
      delete usuarioActualizado.password;
    }
    try {
      await axios.put(`${urlBase}/${id}`, usuarioActualizado, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      toast.success("Registro actualizado con éxito");
      navigate("/gestion-usuarios");
    } catch (error) {
      console.error("Error al actualizar el registro:", error);
      toast.error("Error al actualizar el registro");
    }
  };

  return (
    <div className="p-4 mb-2 mt-5">
      <div className="row justify-content-center">
        <div className="col-lg-9">
          <div className="card mt-3" id="details">
            <div className="card-header">
              <h4>Modificar Registro</h4>
            </div>
            <form onSubmit={(e) => onSubmit(e)} autoComplete="off">
              <div className="card-body">
                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="idUsuario"
                    name="id"
                    placeholder="ID Usuario"
                    required={true}
                    value={id}
                    onChange={(e) => onInputChange(e)}
                    readOnly={true}
                  />
                  <label htmlFor="idUsuario">ID del Usuario</label>
                </div>

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
                    value={password}
                    onChange={(e) => onInputChange(e)}
                    autoComplete="off"
                  />
                  <label htmlFor="password">Nueva Contraseña</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="password"
                    className="form-control"
                    id="confirmPassword"
                    name="confirmPassword"
                    placeholder="Confirmar Contraseña"
                    value={confirmPassword}
                    onChange={(e) => onInputChange(e)}
                    autoComplete="off"
                  />
                  <label htmlFor="confirmPassword">
                    Confirmar Nueva Contraseña
                  </label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="createdAt"
                    name="createdAt"
                    placeholder="Fecha de Creación"
                    required={true}
                    value={createdAt}
                    onChange={(e) => onInputChange(e)}
                    readOnly={true}
                  />
                  <label htmlFor="createdAt">Fecha de Creación</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="updatedAt"
                    name="updatedAt"
                    placeholder="Fecha de Modificación"
                    required={true}
                    value={new Date().toLocaleString()}
                    onChange={(e) => onInputChange(e)}
                    readOnly={true}
                  />
                  <label htmlFor="updatedAt">Fecha de Modificación</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <select
                    className="form-select"
                    id="role"
                    name="role.nombre"
                    value={nombre}
                    onChange={(e) => onInputChange(e)}>
                    <option value="USER">USER</option>
                    <option value="ADMIN">ADMIN</option>
                    <option value="SUPERADMIN">SUPERADMIN</option>
                  </select>
                  <label htmlFor="role">Rol</label>
                </div>

                <button type="submit" className="btn btn-primary">
                  <i className="fa-regular fa-floppy-disk"></i> Guardar Cambios
                </button>
                <Link to="../gestion-usuarios">
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
