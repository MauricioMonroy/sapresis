import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import Registro from "../formularios/RegistroUsuarioModal";

const GestionUsuarios = () => {
  const urlBase = "http://localhost:8080/sipress-app/usuarios/todos";
  const [usuarios, setUsuarios] = useState([]);
  const [error, setError] = useState(null);
  let navigate = useNavigate();

  const cargarUsuarios = async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await axios.get(urlBase, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setUsuarios(response.data);
      setError(null);
    } catch (error) {
      setError("Error al cargar los registros");
      console.error("Error al cargar registros:", error);
    }
  };

  useEffect(() => {
    cargarUsuarios();
  }, []);

  const eliminarUsuario = async (id) => {
    const confirmacion = window.confirm(
      "¿Está seguro de que desea eliminar este registro?"
    );
    if (confirmacion) {
      const token = localStorage.getItem("token");
      try {
        await axios.delete(`${urlBase}/${id}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        cargarUsuarios();
      } catch (error) {
        console.error("Error al eliminar el registro", error);
        // Manejo de errores
        if (error.response && error.response.status === 401) {
          navigate("/login");
        }
      }
    }
  };

  return (
    <div className="p-3 mb-2 mt-5">
      <section>
        <Registro onUsuarioAdded={cargarUsuarios} />
        <div id="actions" className="mt-3">
          <div className="row justify-content-center">
            <div className="col-12 col-md-4 d-flex justify-content-center">
              <a href="/inicio" className="btn btn-info">
                <i className="fa-solid fa-arrow-left-long"></i> Ir a la página
                de inicio
              </a>
            </div>
            <div className="col-12 col-md-4 d-flex justify-content-center">
              <Link
                to="#"
                className="btn btn-success"
                data-bs-toggle="modal"
                data-bs-target="#RegistroUsuarioModal">
                <i className="fa-regular fa-square-plus"></i> Agregar Registro
              </Link>
            </div>
          </div>
        </div>
      </section>
      <div className="row">
        <div className="col-md-9">
          <div className="card" id="contenedor-lista">
            <div className="card-header">
              {error && <p>Error al cargar los registros: {error.message}</p>}
              <h3 className="text-center">Gestión de Usuarios</h3>
              <table className="table">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Email</th>
                    <th>Rol</th>
                    <th>Acciones</th>
                  </tr>
                </thead>
                <tbody>
                  {usuarios.map((usuario, indice) => (
                    <tr key={indice}>
                      <td>{usuario.id}</td>
                      <td>{usuario.nombreCompleto}</td>
                      <td>{usuario.email}</td>
                      <td>{usuario.role.nombre}</td>
                      <td>
                        <div className="textCenter">
                          <Link
                            to={`/usuarios/editar/${usuario.id}`}
                            className="btn btn-warning btn-sm me-2">
                            <i className="fa-regular fa-pen-to-square"></i>{" "}
                            Editar
                          </Link>
                          <button
                            onClick={() => eliminarUsuario(usuario.id)}
                            className="btn btn-danger btn-sm">
                            <i className="fa-regular fa-trash-can"></i> Eliminar
                          </button>
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default GestionUsuarios;
