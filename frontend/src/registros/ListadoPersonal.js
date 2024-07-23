import axios from "axios";
import React, { useEffect, useState } from "react";

export default function ListadoPersonal() {
  const urlBase = "http://localhost:8080/sipress-app/personalS";
  const [personal, setPersonalS] = useState([]);

  useEffect(() => {
    cargarPersonal();
  }, []);

  const cargarPersonal = async () => {
    const resultado = await axios.get(urlBase);
    console.log("Resultado de cargar registros");
    console.log(resultado.data);
    setPersonalS(resultado.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-9">
          <div className="card" id="contenedor-lista">
            <div className="card-header">
              <h3 className="text-center">
                <i className="fa-solid fa-clipboard-list"></i> Lista de personal
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
                  </tr>
                </thead>
                <tbody>
                  {
                    // Iterar sobre el arreglo de personal
                    personal.map((personal, indice) => (
                      <tr key={indice}>
                        <th scope="row">{personal.idPersonal}</th>
                        <td>
                          {personal.nombrepersonal} {personal.apellidoPersonal}
                        </td>
                        <td>{personal.telefonoPersonal}</td>
                        <td>{personal.emailPersonal}</td>
                        <td>
                          {personal.dependencia && (
                            <div>
                              <div>
                                ID Dependencia:{" "}
                                {personal.dependencia.idDependencia}
                              </div>
                              <div>
                                Nombre de la dependencia:{" "}
                                {personal.dependencia.nombreDependencia}
                              </div>
                              <div>
                                Institución:{" "}
                                {personal.dependencia.institucion && (
                                  <div>
                                    <div>
                                      ID Institución:{" "}
                                      {
                                        personal.dependencia.institucion
                                          .idInstitucion
                                      }
                                    </div>
                                    <div>
                                      Nombre de la institución:{" "}
                                      {
                                        personal.dependencia.institucion
                                          .nombreInstitucion
                                      }
                                    </div>
                                    <div>
                                      Dirección:{" "}
                                      {
                                        personal.dependencia.institucion
                                          .direccionInstitucion
                                      }
                                    </div>
                                    <div>
                                      Teléfono:{" "}
                                      {
                                        personal.dependencia.institucion
                                          .telefonoInstitucion
                                      }
                                    </div>
                                    <div>
                                      Código postal:{" "}
                                      {
                                        personal.dependencia.institucion
                                          .codigoPostal
                                      }
                                    </div>
                                  </div>
                                )}
                              </div>
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
