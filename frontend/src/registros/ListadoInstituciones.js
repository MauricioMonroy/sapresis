import axios from "axios";
import React, { useEffect, useState } from "react";
import AgregarInstitucion from "../formularios/AgregarInstitucion";
import { Link } from "react-router-dom";

export default function ListadoInstituciones() {
  const urlBase = "http://localhost:8080/sipress-app/instituciones";
  const [instituciones, setInstituciones] = useState([]);

  useEffect(() => {
    cargarInstituciones();
  }, []);

  const cargarInstituciones = async () => {
    const resultado = await axios.get(urlBase);
    console.log("Resultado de cargar registros");
    console.log(resultado.data);
    setInstituciones(resultado.data);
  };

  const eliminarInstitucion = async (id) => {
    const confirmacion = window.confirm("¿Estás seguro de que deseas eliminar esta institución?");
    if (confirmacion) {
      await axios.delete(`${urlBase}/${id}`);
      cargarInstituciones();
    }
  }

  return (
    <div className="p-2">
      <section>
        <AgregarInstitucion onInstitucionAdded={cargarInstituciones} />
        <div id="actions">
          <div className="row justify-content-center">
            <div className="col-12 col-md-4 d-flex justify-content-center">
              <a href="/" className="btn btn-info">
                <i className="fa-solid fa-arrow-left-long"></i> Ir a la página de inicio
              </a>
            </div>
            <div className="col-12 col-md-4 d-flex justify-content-center">
              <Link
                to="#"
                className="btn btn-success"
                data-bs-toggle="modal"
                data-bs-target="#AgregarInstitucionModal">
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
                <i className="fa-regular fa-hospital"></i> Lista de
                instituciones
              </h3>
            </div>
            <div className="table-responsive">
              <table className="table table-striped table-hover align-middle">
                <thead className="table-dark">
                  <tr>
                    <th>ID Institución</th>
                    <th>Nombre de la Institución</th>
                    <th>Dirección</th>
                    <th>Teléfono</th>
                    <th>Código Postal</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  {
                    // Iterar sobre el arreglo de instituciones
                    instituciones.map((institucion, indice) => (
                      <tr key={indice}>
                        <th scope="row">{institucion.idInstitucion}</th>
                        <td>{institucion.nombreInstitucion}</td>
                        <td>{institucion.direccionInstitucion}</td>
                        <td>{institucion.telefonoInstitucion}</td>
                        <td>{institucion.codigoPostal}</td>
                        <td className="text-center">
                          <div>
                            <Link
                              to={`/editar/${institucion.idInstitucion}`}
                              className="btn btn-warning btn-sm me-2">
                              <i className="fa-regular fa-pen-to-square"></i> Editar
                            </Link>
                            <button onClick={() => eliminarInstitucion(institucion.idInstitucion)} 
                            className="btn btn-danger btn-sm">
                              <i className="fa-regular fa-trash-can"></i> Eliminar
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
