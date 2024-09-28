import React, { useEffect, useState, useMemo } from "react";
import axios from "axios";
import AgregarDependencia from "../formularios/AgregarDependencia";
import Pagination from "../comunes/Pagination";
import { Link, useNavigate } from "react-router-dom";
import { confirmarEliminacion } from "../comunes/Notificaciones";
import { toast } from "react-toastify";

/**
 * Componente funcional que muestra la lista de dependencias registradas en el sistema.
 * @param {Function} props.cargarDependencias - Función que carga la lista de dependencias.
 * @param {Array} props.dependencias - Lista de dependencias registradas en el sistema.
 * @param {String} props.role - Rol del usuario autenticado.
 * @param {String} props.error - Mensaje de error al cargar los registros.
 * @param {Number} props.currentPage - Página actual de la lista de dependencias.
 * @param {Function} props.setCurrentPage - Función que establece la página actual de la lista de dependencias.
 * @param {Function} props.navigate - Función de navegación entre componentes.
 * @param {String} urlBase - URL base para obtener la lista de dependencias.
 * @param {Number} PageSize - Número de registros por página.
 * @returns {string} HTML con la lista de dependencias.
 * @requires react, axios, AgregarDependencia, Pagination, Link, useNavigate, confirmarEliminacion, toast.
 * @version 1.0
 * */

const PageSize = 5;

export default function ListadoDependencias() {
  const urlBase = process.env.REACT_APP_API_URL + "/sapresis/dependencias";
  const [dependencias, setDependencias] = useState([]);
  const [role, setRole] = useState("");
  const [error, setError] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);
  let navigate = useNavigate();

  const cargarDependencias = async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await axios.get(urlBase, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setDependencias(response.data);
      setError(null);
    } catch (error) {
      setError("Error al cargar los registros");
      console.error("Error al cargar los registros", error);
    }
  };

  useEffect(() => {
    cargarDependencias();
  });

  const eliminarDependencia = async (id) => {
    const token = localStorage.getItem("token");
    try {
      await axios.delete(`${urlBase}/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      cargarDependencias();
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
    const firstPageIndex = (currentPage - 1) * PageSize;
    const lastPageIndex = firstPageIndex + PageSize;
    return dependencias.slice(firstPageIndex, lastPageIndex);
  }, [dependencias, currentPage]);

  return (
    <div className="p-3 mb-2 mt-5">
      <section>
        <AgregarDependencia onDependenciaAdded={cargarDependencias} />
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
                data-bs-toggle={
                  role.nombre === "SUPERADMIN" || role.nombre === "ADMIN"
                    ? "modal"
                    : ""
                }
                data-bs-target={
                  role.nombre === "SUPERADMIN" || role.nombre === "ADMIN"
                    ? "#AgregarDependenciaModal"
                    : ""
                }
                onClick={() => {
                  if (role.nombre === "USER") {
                    toast.error(
                      "No tiene los permisos necesarios para agregar un registro."
                    );
                  }
                }}>
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
              <h3 className="text-center">
                <i className="fa-solid fa-house-medical"></i> Lista de
                Dependencias
              </h3>
            </div>
            <div className="table-responsive">
              {error && <p className="fs-5">{error}</p>}
              <table className="table table-striped table-hover align-middle">
                <thead className="table-dark">
                  <tr>
                    <th>ID Dependencia</th>
                    <th>Nombre de la Dependencia</th>
                    <th>Institución Vinculada</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  {currentTableData.map((dependencia, indice) => (
                    <tr key={indice}>
                      <th scope="row">{dependencia.idDependencia}</th>
                      <td>{dependencia.nombreDependencia}</td>
                      <td>
                        {dependencia.institucion && (
                          <div>{dependencia.institucion.nombreInstitucion}</div>
                        )}
                      </td>
                      <td>
                        <div className="textCenter">
                          {(role.nombre === "SUPERADMIN" ||
                            role.nombre === "ADMIN") && (
                            <Link
                              to={`/dependencias/editar/${dependencia.idDependencia}`}
                              className="btn btn-warning btn-sm me-2">
                              <i className="fa-regular fa-pen-to-square"></i>{" "}
                              Editar
                            </Link>
                          )}
                          {role.nombre === "SUPERADMIN" && (
                            <button
                              onClick={() =>
                                confirmarEliminacion(
                                  dependencia.idDependencia,
                                  eliminarDependencia
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
            <div className="card-footer d-flex justify-content-center">
              <Pagination
                className="pagination-bar"
                currentPage={currentPage}
                totalCount={dependencias.length}
                pageSize={PageSize}
                onPageChange={(page) => setCurrentPage(page)}
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
