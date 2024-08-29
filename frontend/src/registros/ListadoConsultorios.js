import axios from "axios";
import React, { useEffect, useState } from "react";
import AgregarConsultorio from "../formularios/AgregarConsultorio";
import { Link, useNavigate } from "react-router-dom";

export default function ListadoConsultorios() {
  const urlBase = "http://localhost:8080/sipress-app/consultorios";
  const [consultorios, setConsultorios] = useState([]);
  const [error, setError] = useState(null);
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
      console.error("Error al cargar registros:", error);
    }
  };

  useEffect(() => {
    cargarConsultorios();
  }, []);

  const eliminarConsultorio = async (id) => {
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
        cargarConsultorios();
      } catch (error) {
        console.error("Error al eliminar el registro", error);
        if (error.response && error.response.status === 401) {
          navigate("/login");
        }
      }
    }
  };

  return (
    <div className="p-3 mb-2 mt-5">
      <section>
        <AgregarConsultorio onConsultorioAdded={cargarConsultorios} />
        {error && <p>Error al cargar los registros: {error.message}</p>}
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
                data-bs-toggle="modal"
                data-bs-target="#AgregarConsultorioModal">
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
                  {
                    // Iterar sobre el arreglo de consultorios
                    consultorios.map((consultorio, indice) => (
                      <tr key={indice}>
                        <th scope="row">{consultorio.numeroConsultorio}</th>
                        <td>
                          {consultorio.personal && (
                            <div>
                              {consultorio.personal.nombrePersonal}{" "}
                              {consultorio.personal.apellidoPersonal}
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
                            <Link
                              to={`/consultorios/editar/${consultorio.numeroConsultorio}`}
                              className="btn btn-warning btn-sm me-2">
                              <i className="fa-regular fa-pen-to-square"></i>{" "}
                              Editar
                            </Link>
                            <button
                              onClick={() =>
                                eliminarConsultorio(
                                  consultorio.numeroConsultorio
                                )
                              }
                              className="btn btn-danger btn-sm">
                              <i className="fa-regular fa-trash-can"></i>{" "}
                              Eliminar
                            </button>
                          </div>
                        </td>
                      </tr>
                    ))
                  }
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
