import axios from "axios";
import React, { useEffect, useState } from "react";

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

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-9">
          <div className="card" id="contenedor-lista">
            <div className="card-header">
              <h3 className="text-center">
                <i className="fa-solid fa-clipboard-list"></i> Lista de
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
