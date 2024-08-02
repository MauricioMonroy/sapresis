import axios from "axios";
import React, { useEffect, useState } from "react";
import AgregarDoctor from "../formularios/AgregarDoctor";
import { Link } from "react-router-dom";

export default function ListadoDoctores() {
  const urlBase = "http://localhost:8080/sipress-app/doctores";
  const [doctores, setDoctores] = useState([]);

  useEffect(() => {
    cargarDoctores();
  }, []);

  const cargarDoctores = async () => {
    const resultado = await axios.get(urlBase);
    console.log("Resultado de cargar registros");
    console.log(resultado.data);
    setDoctores(resultado.data);
  };

  const eliminarDoctor = async (id) => {
    const confirmacion = window.confirm(
      "¿Está seguro de que desea eliminar este registro?"
    );
    if (confirmacion) {
      await axios.delete(`${urlBase}/${id}`);
      cargarDoctores();
    }
  };

  return (
    <div className="p-3">
      <section>
        <AgregarDoctor onDoctorAdded={cargarDoctores} />
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
                data-bs-target="#AgregarDoctorModal">
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
                <i class="fa-solid fa-user-doctor"></i> Lista de doctores
              </h3>
            </div>
            <div className="table-responsive">
              <table className="table table-striped table-hover align-middle">
                <thead className="table-dark">
                  <tr>
                    <th>ID Doctor</th>
                    <th>Nombre completo</th>
                    <th>Teléfono</th>
                    <th>Email</th>
                    <th>Dependencia</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  {
                    // Iterar sobre el arreglo de doctores
                    doctores.map((doctor, indice) => (
                      <tr key={indice}>
                        <th scope="row">{doctor.idDoctor}</th>
                        <td>
                          {doctor.nombreDoctor} {doctor.apellidoDoctor}
                        </td>
                        <td>{doctor.telefonoDoctor}</td>
                        <td>{doctor.emailDoctor}</td>
                        <td>
                          {doctor.dependencia && (
                            <div>
                              {doctor.dependencia.nombreDependencia}
                              <div>
                                Sede:{" "}
                                {doctor.dependencia.institucion && (
                                  <div>
                                    {
                                      doctor.dependencia.institucion
                                        .nombreInstitucion
                                    }
                                  </div>
                                )}
                              </div>
                            </div>
                          )}
                        </td>
                        <td className="text-center">
                          <div>
                            <Link
                              to={`/doctores/editar/${doctor.idDoctor}`}
                              className="btn btn-warning btn-sm me-2">
                              <i className="fa-regular fa-pen-to-square"></i>{" "}
                              Editar
                            </Link>
                            <button
                              onClick={() => eliminarDoctor(doctor.idDoctor)}
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
