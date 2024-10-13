import React, { useEffect, useState, useMemo, useCallback } from "react";
import axios from "axios";
import Registro from "../formularios/RegistroUsuarioModal";
import Pagination from "../comunes/Pagination";
import { Link, useNavigate } from "react-router-dom";
import { confirmarEliminacion } from "../comunes/Notificaciones";
import { toast } from "react-toastify";

/**
 * Componente funcional que muestra la lista de usuarios registrados en el sistema.
 * @param {Function} props.cargarUsuarios - Función que carga la lista de usuarios.
 * @param {Array} props.usuarios - Lista de usuarios registrados en el sistema.
 * @param {String} props.role - Rol del usuario autenticado.
 * @param {String} props.error - Mensaje de error al cargar los registros.
 * @param {Number} props.currentPage - Página actual de la lista de usuarios.
 * @param {Function} props.setCurrentPage - Función que establece la página actual de la lista de usuarios.
 * @param {Function} props.navigate - Función de navegación entre componentes.
 * @param {String} urlBase - URL base para obtener la lista de usuarios.
 * @param {Number} PageSize - Número de registros por página.
 * @returns {string} HTML con la lista de usuarios.
 * @requires react, axios, Registro, Pagination, Link, useNavigate, confirmarEliminacion, toast.
 * @version 1.0
 * */

const GestionUsuarios = () => {
  const urlBase = process.env.REACT_APP_API_URL + "/sapresis/usuarios/todos";
  const [usuarios, setUsuarios] = useState([]);
  const [role, setRole] = useState("");
  const [error, setError] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);
  const [pageSize, setPageSize] = useState(5);
  let navigate = useNavigate();

  const cargarUsuarios = useCallback(async () => {
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
      console.error("Error al cargar los registros", error);
    }
  }, [urlBase]);

  useEffect(() => {
    cargarUsuarios();
  }, [cargarUsuarios]);

  const eliminarUsuario = async (id) => {
    const urlPath = process.env.REACT_APP_API_URL + "/sapresis/usuarios";
    const token = localStorage.getItem("token");
    try {
      await axios.delete(`${urlPath}/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      cargarUsuarios();
      toast.success("Registro eliminado correctamente");
    } catch (error) {
      console.error("Error al eliminar el registro", error);
      if (error.response && error.response.status === 401) {
        navigate("/login");
      } else {
        toast.error("Hubo un error al eliminar el registro");
      }
    }
  };

  // Limitación de funciones de acuerdo con el rol del usuario
  useEffect(() => {
    const token = localStorage.getItem("token");
    axios
      .get(process.env.REACT_APP_API_URL + "/sapresis/usuarios/perfil", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setRole(response.data.role);
        console.log("Rol del usuario:", response.data.role);
      })
      .catch((error) => {
        console.error("Error al obtener el rol del usuario", error);
      });
  }, []);

  const currentTableData = useMemo(() => {
    const firstPageIndex = (currentPage - 1) * pageSize;
    const lastPageIndex = firstPageIndex + pageSize;
    return usuarios.slice(firstPageIndex, lastPageIndex);
  }, [currentPage, usuarios, pageSize]);

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
              <h3 className="text-center">
                <i className="fa-solid fa-users"></i> Gestión de Usuarios
              </h3>
            </div>
            <div className="table-responsive">
              {error && <p className="fs-5">{error}</p>}
              <table className="table table-striped table-hover align-middle">
                <thead className="table-dark">
                  <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Email</th>
                    <th>Rol</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  {currentTableData.map((usuario, indice) => (
                    <tr key={indice}>
                      <th scope="row">{usuario.id}</th>
                      <td>{usuario.nombreCompleto}</td>
                      <td>{usuario.email}</td>
                      <td>{usuario.role.nombre}</td>
                      <td>
                        <div className="textCenter">
                          {(role.nombre === "SUPERADMIN" ||
                            role.nombre === "ADMIN") && (
                            <Link
                              to={`/usuarios/editar/${usuario.id}`}
                              className="btn btn-warning btn-sm me-2">
                              <i className="fa-regular fa-pen-to-square"></i>{" "}
                              Editar
                            </Link>
                          )}
                          {role.nombre === "SUPERADMIN" && (
                            <button
                              onClick={() =>
                                confirmarEliminacion(
                                  usuario.id,
                                  eliminarUsuario
                                )
                              }
                              className="btn btn-danger btn-sm">
                              <i className="fa-regular fa-trash-can"></i>{" "}
                              Eliminar
                            </button>
                          )}
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
            <div className="fs-6">
              <label htmlFor="pageSize">Registros por página: </label>{" "}
              <select
                id="pageSize"
                value={pageSize}
                onChange={(e) => setPageSize(Number(e.target.value))}>
                <option value="5">5</option>
                <option value="10">10</option>
                <option value="25">25</option>
                <option value="50">50</option>
                <option value="100">100</option>
              </select>
            </div>
            <div className="card-footer d-flex justify-content-center">
              <Pagination
                currentPage={currentPage}
                totalCount={usuarios.length}
                pageSize={pageSize}
                onPageChange={(page) => setCurrentPage(page)}
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default GestionUsuarios;
