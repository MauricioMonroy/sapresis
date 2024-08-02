import axios from "axios";
import React, { useEffect, useState } from "react";
import AgregarConsultorio from "../formularios/AgregarConsultorio";
import { Link } from "react-router-dom";

export default function ListadoConsultorios() {
  const urlBase = "http://localhost:8080/sipress-app/consultorios";
  const [consultorios, setConsultorios] = useState([]);

  useEffect(() => {
    cargarConsultorios();
  }, []);

  const cargarConsultorios = async () => {
    const resultado = await axios.get(urlBase);
    console.log("Resultado de cargar registros");
    console.log(resultado.data);
    setConsultorios(resultado.data);
  };

  const eliminarConsultorio = async (id) => {
    const confirmacion = window.confirm(
      "¿Está seguro de eliminar el registro?"
    );
    if (confirmacion) {
      await axios.delete(`${urlBase}/${id}`);
      cargarConsultorios();
    }
  };

  return (
    <div className="p-3">
      <section>
        <AgregarConsultorio onConsultorioAdded={cargarConsultorios} />
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
                <i class="fa-solid fa-stethoscope"></i> Lista de Consultorios
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
                        <td className="text-center">
                          <div>
                            <Link
                              to={`/consultorios/editar/${consultorio.numeroConsultorio}`}
                              className="btn btn-warning btn-sm me-2">
                              <i className="fa-regular fa-pen-to-square"></i>{" "}
                              Editar
                            </Link>
                            <button
                              onClick={() =>
                                eliminarConsultorio(consultorio.numeroConsultorio)
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
