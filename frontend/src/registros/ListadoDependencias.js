import axios from "axios";
import React, { useEffect, useState } from "react";
import AgregarDependencia from "../formularios/AgregarDependencia";
import { Link } from "react-router-dom";

export default function ListadoDependencias() {
  const urlBase = "http://localhost:8080/sipress-app/dependencias";
  const [dependencias, setDependencias] = useState([]);

  useEffect(() => {
    cargarDependencias();
  }, []);

  const cargarDependencias = async () => {
    const resultado = await axios.get(urlBase);
    console.log("Resultado de cargar registros");
    console.log(resultado.data);
    setDependencias(resultado.data);
  };

  const eliminarDependencia = async (id) => {
    const confirmacion = window.confirm(
      "¿Está seguro de que desea eliminar este registro?"
    );
    if (confirmacion) {
      await axios.delete(`${urlBase}/${id}`);
      cargarDependencias();
    }
  };

  return (
    <div className="p-3">
      <section>
        <AgregarDependencia onDependenciaAdded={cargarDependencias} />
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
                data-bs-target="#AgregarDependenciaModal">
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
                <i class="fa-solid fa-house-medical"></i> Lista de Dependencias
              </h3>
            </div>
            <div className="table-responsive">
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
                  {
                    // Iterar sobre el arreglo de dependencias
                    dependencias.map((dependencia, indice) => (
                      <tr key={indice}>
                        <th scope="row">{dependencia.idDependencia}</th>
                        <td>{dependencia.nombreDependencia}</td>
                        <td>
                          {dependencia.institucion && (
                            <div>
                              {dependencia.institucion.nombreInstitucion}
                            </div>
                          )}
                        </td>
                        <td className="text-center">
                          <div>
                            <Link
                              to={`/dependencias/editar/${dependencia.idDependencia}`}
                              className="btn btn-warning btn-sm me-2">
                              <i className="fa-regular fa-pen-to-square"></i>{" "}
                              Editar
                            </Link>
                            <button
                              onClick={() =>
                                eliminarDependencia(dependencia.idDependencia)
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
