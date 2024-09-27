import React, { useEffect, useState, useMemo } from "react";
import axios from "axios";
import AgregarConsultorio from "../formularios/AgregarConsultorio";
import Pagination from "../comunes/Pagination";
import { Link, useNavigate } from "react-router-dom";
import { confirmarEliminacion } from "../comunes/Notificaciones";
import { toast } from "react-toastify";

/**
 * Componente funcional que muestra la lista de consultorios registrados en el sistema.
 * @param {Function} props.cargarConsultorios - Función que carga la lista de consultorios.
 * @param {Array} props.consultorios - Lista de consultorios registrados en el sistema.
 * @param {String} props.role - Rol del usuario autenticado.
 * @param {String} props.error - Mensaje de error al cargar los registros.
 * @param {Number} props.currentPage - Página actual de la lista de consultorios.
 * @param {Function} props.setCurrentPage - Función que establece la página actual de la lista de consultorios.
 * @param {Function} props.navigate - Función de navegación entre componentes.
 * @param {String} urlBase - URL base para obtener la lista de consultorios.
 * @param {Number} PageSize - Número de registros por página.
 * @returns {string} HTML con la lista de consultorios.
 * @requires react, axios, AgregarConsultorio, Pagination, Link, useNavigate, confirmarEliminacion, toast.
 * @version 1.0
 * */

const PageSize = 5;

export default function ListadoConsultorios() {
  const urlBase = "https://sapresis-backend.onrender.com/sapresis/consultorios";
  const [consultorios, setConsultorios] = useState([]);
  const [role, setRole] = useState("");
  const [error, setError] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);
  let navigate = useNavigate();

  const cargarConsultorios = async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await axios.get(urlBase, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setConsultorios(response.data);
      setError(null);
    } catch (error) {
      setError("Error al cargar los registros");
      console.error("Error al cargar registros", error);
    }
  };

  useEffect(() => {
    cargarConsultorios();
  }, []);

  const eliminarConsultorio = async (id) => {
    const token = localStorage.getItem("token");
    try {
      await axios.delete(`${urlBase}/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      cargarConsultorios();
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
      .get("https://sapresis-backend.onrender.com/sapresis/usuarios/perfil", {
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
    return consultorios.slice(firstPageIndex, lastPageIndex);
  }, [consultorios, currentPage]);

  return (
    <div className="p-3 mb-2 mt-5">
      <section>
        <AgregarConsultorio onConsultorioAdded={cargarConsultorios} />
        <div id="actions" className="mt-3">
          <div className="row justify-content-center">
            <div className="col-12 col-md-4 d-flex justify-content-center">
              <a href="/" className="btn btn-info">
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
                    ? "#AgregarConsultorioModal"
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
                <i className="fa-solid fa-stethoscope"></i> Lista de
                Consultorios
              </h3>
            </div>
            <div className="table-responsive">
              {error && <p className="fs-5">{error}</p>}
              <table className="table table-striped table-hover align-middle">
                <thead className="table-dark">
                  <tr>
                    <th>Número de consultorio</th>
                    <th>Personal encargado</th>
                    <th>Paciente</th>
                    <th>Fecha de admisión</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  {currentTableData.map((consultorio, indice) => (
                    <tr key={indice}>
                      <th scope="row">{consultorio.numeroConsultorio}</th>
                      <td>
                        {consultorio.personal && (
                          <div>
                            <div>
                              {consultorio.personal.nombrePersonal}{" "}
                              {consultorio.personal.apellidoPersonal}
                            </div>
                            <div>ID: {consultorio.personal.idPersonal}</div>
                          </div>
                        )}
                      </td>
                      <td>
                        {consultorio.paciente && (
                          <div>
                            <div>
                              {consultorio.paciente.nombrePaciente}{" "}
                              {consultorio.paciente.apellidoPaciente}
                            </div>
                            <div>ID: {consultorio.paciente.idPaciente}</div>
                          </div>
                        )}
                      </td>
                      <td>{consultorio.fechaAdmision}</td>
                      <td>
                        <div className="textCenter">
                          {(role.nombre === "SUPERADMIN" ||
                            role.nombre === "ADMIN") && (
                            <Link
                              to={`/consultorios/editar/${consultorio.numeroConsultorio}`}
                              className="btn btn-warning btn-sm me-2">
                              <i className="fa-regular fa-pen-to-square"></i>{" "}
                              Editar
                            </Link>
                          )}
                          {role.nombre === "SUPERADMIN" && (
                            <button
                              onClick={() =>
                                confirmarEliminacion(
                                  consultorio.numeroConsultorio,
                                  eliminarConsultorio
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
                totalCount={consultorios.length}
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
