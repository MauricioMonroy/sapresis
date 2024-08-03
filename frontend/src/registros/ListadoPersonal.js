import axios from "axios";
import React, { useEffect, useState } from "react";
import AgregarPersonal from "../formularios/AgregarPersonal";
import { Link } from "react-router-dom";

export default function ListadoPersonal() {
  const urlBase = "http://localhost:8080/sipress-app/personalS";
  const [personal, setPersonalS] = useState([]);

  useEffect(() => {
    cargarPersonalS();
  }, []);

  const cargarPersonalS = async () => {
    const resultado = await axios.get(urlBase);
    console.log("Resultado de cargar registros");
    console.log(resultado.data);
    setPersonalS(resultado.data);
  };

  const eliminarPersonal = async (id) => {
    const confirmacion = window.confirm(
      "¿Está seguro de que desea eliminar este registro?"
    );
    if (confirmacion) {
      await axios.delete(`${urlBase}/${id}`);
      cargarPersonalS();
    }
  };

  return (
    <div className="p-3">
      <section>
        <AgregarPersonal onPersonalAdded={cargarPersonalS} />
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
                data-bs-target="#AgregarPersonalModal">
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
                <i className="fa-solid fa-user-nurse"></i> Lista de personal
              </h3>
            </div>
            <div className="table-responsive">
              <table className="table table-striped table-hover align-middle">
                <thead className="table-dark">
                  <tr>
                    <th>ID Personal</th>
                    <th>Nombre completo</th>
                    <th>Teléfono</th>
                    <th>Email</th>
                    <th>Dependencia</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  {
                    // Iterar sobre el arreglo de personal
                    personal.map((personal, indice) => (
                      <tr key={indice}>
                        <th scope="row">{personal.idPersonal}</th>
                        <td>
                          {personal.nombrePersonal} {personal.apellidoPersonal}
                        </td>
                        <td>{personal.telefonoPersonal}</td>
                        <td>{personal.emailPersonal}</td>
                        <td>
                          {personal.dependencia && (
                            <div>
                              {personal.dependencia.nombreDependencia}
                              <div>
                                Sede:{" "}
                                {personal.dependencia.institucion && (
                                  <div>
                                    {
                                      personal.dependencia.institucion
                                        .nombreInstitucion
                                    }
                                  </div>
                                )}
                              </div>
                            </div>
                          )}
                        </td>
                        <td>
                          <div className="textCenter">
                            <Link
                              to={`/personalS/editar/${personal.idPersonal}`}
                              className="btn btn-warning btn-sm me-2">
                              <i className="fa-regular fa-pen-to-square"></i>{" "}
                              Editar
                            </Link>
                            <button
                              onClick={() =>
                                eliminarPersonal(personal.idPersonal)
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
