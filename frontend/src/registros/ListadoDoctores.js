import axios from "axios";
import React, { useEffect, useState } from "react";

export default function Listadodoctores() {
  const urlBase = "http://localhost:8080/sipress-app/doctores";
  const [doctores, setDoctores] = useState([]);

  useEffect(() => {
    cargardoctores();
  }, []);

  const cargardoctores = async () => {
    const resultado = await axios.get(urlBase);
    console.log("Resultado de cargar registros");
    console.log(resultado.data);
    setDoctores(resultado.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-9">
          <div className="card" id="contenedor-lista">
            <div className="card-header">
              <h3 className="text-center">
                <i className="fa-solid fa-clipboard-list"></i> Lista de doctores
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
                              <div>
                                ID Dependencia:{" "}
                                {doctor.dependencia.idDependencia}
                              </div>
                              <div>
                                Nombre de la dependencia:{" "}
                                {doctor.dependencia.nombreDependencia}
                              </div>
                              <div>
                                Institución:{" "}
                                {doctor.dependencia.institucion && (
                                  <div>
                                    <div>
                                      ID Institución:{" "}
                                      {
                                        doctor.dependencia.institucion
                                          .idInstitucion
                                      }
                                    </div>
                                    <div>
                                      Nombre de la institución:{" "}
                                      {
                                        doctor.dependencia.institucion
                                          .nombreInstitucion
                                      }
                                    </div>
                                    <div>
                                      Dirección:{" "}
                                      {
                                        doctor.dependencia.institucion
                                          .direccionInstitucion
                                      }
                                    </div>
                                    <div>
                                      Teléfono:{" "}
                                      {
                                        doctor.dependencia.institucion
                                          .telefonoInstitucion
                                      }
                                    </div>
                                    <div>
                                      Código postal:{" "}
                                      {
                                        doctor.dependencia.institucion
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
