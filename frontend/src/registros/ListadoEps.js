import axios from "axios";
import React, { useEffect, useState } from "react";
import AgregarEps from "../formularios/AgregarEps";
import { Link } from "react-router-dom";

export default function ListadoEps() {
  const urlBase = "http://localhost:8080/sipress-app/epsS";
  const [eps, setEpsS] = useState([]);

  useEffect(() => {
    cargarEpsS();
  }, []);

  const cargarEpsS = async () => {
    const resultado = await axios.get(urlBase);
    console.log("Resultado de cargar registros");
    console.log(resultado.data);
    setEpsS(resultado.data);
  };

  const eliminarEps = async (id) => {
    const confirmacion = window.confirm(
      "¿Está seguro de que desea eliminar este registro?"
    );
    if (confirmacion) {
      await axios.delete(`${urlBase}/${id}`);
      cargarEpsS();
    }
  };

  return (
    <div className="p-3">
      <section>
        <AgregarEps onEpsAdded={cargarEpsS} />
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
                data-bs-target="#AgregarEpsModal">
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
                <i className="fa-solid fa-staff-snake"></i> Lista de EPS
              </h3>
            </div>
            <div className="table-responsive">
              <table className="table table-striped table-hover align-middle">
                <thead className="table-dark">
                  <tr>
                    <th>ID EPS</th>
                    <th>Nombre de la EPS</th>
                    <th>Teléfono</th>
                    <th>Email</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  {
                    // Iterar sobre el arreglo de EPS
                    eps.map((eps, indice) => (
                      <tr key={indice}>
                        <th scope="row">{eps.idEps}</th>
                        <td>{eps.nombreEps}</td>
                        <td>{eps.telefonoEps}</td>
                        <td>{eps.emailEps}</td>
                        <td>
                          <div className="textCenter">
                            <Link
                              to={`/epsS/editar/${eps.idEps}`}
                              className="btn btn-warning btn-sm me-2">
                              <i className="fa-regular fa-pen-to-square"></i>{" "}
                              Editar
                            </Link>
                            <button
                              onClick={() => eliminarEps(eps.idEps)}
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
