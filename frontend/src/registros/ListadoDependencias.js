import axios from "axios";
import React, { useEffect, useState } from "react";

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

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-9">
          <div className="card" id="contenedor-lista">
            <div className="card-header">
              <h3 className="text-center">
                <i className="fa-solid fa-clipboard-list"></i> Lista de
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
                              <div>ID: {dependencia.institucion.idInstitucion}</div>
                              <div>Nombre: {dependencia.institucion.nombreInstitucion}</div>
                              <div>Dirección: {dependencia.institucion.direccionInstitucion}</div>
                              <div>Teléfono: {dependencia.institucion.telefonoInstitucion}</div>
                              <div>Email: {dependencia.institucion.codigoPostal}</div>
                            </div>
                          )}
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
