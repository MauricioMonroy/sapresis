import axios from "axios";
import React, { useEffect, useState } from "react";
import AgregarConsulta from "../formularios/AgregarConsulta";
import { Link } from "react-router-dom";

export default function ListadoConsultas() {
  const urlBase = "http://localhost:8080/sipress-app/consultas";
  const [consultas, setConsultas] = useState([]);

  useEffect(() => {
    cargarConsultas();
  }, []);

  const cargarConsultas = async () => {
    const resultado = await axios.get(urlBase);
    console.log("Resultado de cargar registros");
    console.log(resultado.data);
    setConsultas(resultado.data);
  };

  const eliminarConsulta = async (idPaciente, idDoctor) => {
    const confirmacion = window.confirm(
      "¿Está seguro de eliminar el registro?"
    );
    if (confirmacion) {
      await axios.delete(`${urlBase}/${idPaciente}/${idDoctor}`);
      cargarConsultas();
    }
  };

  return (
    <div className="p-3">
      <section>
        <AgregarConsulta onConsultaAdded={cargarConsultas} />
        <div id="actions">
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
                data-bs-target="#AgregarConsultaModal">
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
                  {
                    // Iterar sobre el arreglo de consultas
                    consultas.map((consulta, indice) => (
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
                              Nombre: {consulta.doctor.nombreDoctor}{" "}
                              {consulta.doctor.apellidoDoctor}
                            </div>
                          )}
                        </td>
                        <td>{consulta.fechaConsulta}</td>
                        <td>{consulta.horaConsulta}</td>
                        <td>
                          <div className="textCenter">
                            <Link
                              to={`/consultas/editar/${consulta.paciente.idPaciente}/${consulta.doctor.idDoctor}`}
                              className="btn btn-warning btn-sm me-2">
                              <i className="fa-regular fa-pen-to-square"></i>{" "}
                              Editar
                            </Link>
                            <button
                              onClick={() =>
                                eliminarConsulta(
                                  consulta.paciente.idPaciente,
                                  consulta.doctor.idDoctor
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
