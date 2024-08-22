import axios from "axios";
import React, { useEffect, useState } from "react";
import AgregarDependencia from "../formularios/AgregarDependencia";
import { Link, useNavigate } from "react-router-dom";

export default function ListadoDependencias() {
  const urlBase = "http://localhost:8080/sipress-app/dependencias";
  const [dependencias, setDependencias] = useState([]);
  const [error, setError] = useState(null);
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
      setError("Error al cargar los pacientes");
      console.error("Error al cargar pacientes:", error);
    }
  };

  useEffect(() => {
    cargarDependencias();
  }, []);

  const eliminarDependencia = async (id) => {
    const confirmacion = window.confirm(
      "¿Está seguro de que desea eliminar este registro?"
    );
    if (confirmacion) {
      const token = localStorage.getItem("token"); 
      try {
        await axios.delete(`${urlBase}/${id}`, {
          headers: {
            Authorization: `Bearer ${token}` 
          }
        });
        cargarDependencias();
      } catch (error) {
        console.error("Error al eliminar la EPS", error);
        // Manejo de errores
        if (error.response && error.response.status === 401) {
          navigate("/login");
        }
      }
    }
  };

  return (
    <div className="p-3 mb-2 mt-5">
      <section>
        <AgregarDependencia onDependenciaAdded={cargarDependencias} />
        {error && <p>Error al cargar registros: {error.message}</p>}
        <div id="actions">
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
                <i className="fa-solid fa-house-medical"></i> Lista de
                Dependencias
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
                        <td>
                          <div className="textCenter">
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
