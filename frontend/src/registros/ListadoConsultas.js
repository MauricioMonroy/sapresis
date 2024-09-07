import React, { useEffect, useState, useMemo } from "react";
import axios from "axios";
import AgregarConsulta from "../formularios/AgregarConsulta";
import Pagination from "../comunes/Pagination";
import { Link, useNavigate } from "react-router-dom";
import { confirmarEliminacion } from "../comunes/Notificaciones";
import { toast } from "react-toastify";

const PageSize = 5;

export default function ListadoConsultas() {
  const urlBase = "http://localhost:8080/sipress-app/consultas";
  const [consultas, setConsultas] = useState([]);
  const [role, setRole] = useState("");
  const [error, setError] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);
  let navigate = useNavigate();

  const cargarConsultas = async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await axios.get(urlBase, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setConsultas(response.data);
      setError(null);
    } catch (error) {
      setError("Error al cargar los registros");
      console.error("Error al cargar registros", error);
    }
  };

  useEffect(() => {
    cargarConsultas();
  }, []);

  const eliminarConsulta = async (pacienteId, doctorId) => {
    const token = localStorage.getItem("token");
    try {
      await axios.delete(`${urlBase}/${pacienteId}/${doctorId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      cargarConsultas();
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
      .get("http://localhost:8080/sipress-app/usuarios/perfil", {
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
    return consultas.slice(firstPageIndex, lastPageIndex);
  }, [consultas, currentPage]);

  return (
    <div className="p-3 mb-2 mt-5">
      <section>
        <AgregarConsulta onConsultaAdded={cargarConsultas} />

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
                    ? "#AgregarConsultaModal"
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
                <i className="fa-regular fa-calendar-check"></i> Lista de
                Consultas
              </h3>
            </div>
            <div className="table-responsive">
            {error && <p className="fs-5">{error}</p>}
              <table className="table table-striped table-hover align-middle">
                <thead className="table-dark">
                  <tr>
                    <th>Paciente</th>
                    <th>Doctor</th>
                    <th>Fecha de consulta</th>
                    <th>Hora de consulta</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  {currentTableData.map((consulta, indice) => (
                    <tr key={indice}>
                      <td>
                        {consulta.paciente && (
                          <div>
                            <div>
                              {consulta.paciente.nombrePaciente}{" "}
                              {consulta.paciente.apellidoPaciente}
                            </div>
                            <div>ID: {consulta.paciente.idPaciente}</div>
                          </div>
                        )}
                      </td>
                      <td>
                        {consulta.doctor && (
                          <div>
                            <div>
                              {consulta.doctor.nombreDoctor}{" "}
                              {consulta.doctor.apellidoDoctor}
                            </div>
                            <div>ID: {consulta.doctor.idDoctor}</div>
                          </div>
                        )}
                      </td>
                      <td>{consulta.fechaConsulta}</td>
                      <td>{consulta.horaConsulta}</td>
                      <td>
                        <div className="textCenter">
                          {(role.nombre === "SUPERADMIN" ||
                            role.nombre === "ADMIN") && (
                            <Link
                              to={`/consultas/editar/${consulta.paciente.idPaciente}/${consulta.doctor.idDoctor}`}
                              className="btn btn-warning btn-sm me-2">
                              <i className="fa-regular fa-pen-to-square"></i>{" "}
                              Editar
                            </Link>
                          )}
                          {role.nombre === "SUPERADMIN" && (
                            <button
                              onClick={() =>
                                confirmarEliminacion(
                                  consulta.paciente.idPaciente,
                                  consulta.doctor.idDoctor,
                                  eliminarConsulta
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
                totalCount={consultas.length}
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
