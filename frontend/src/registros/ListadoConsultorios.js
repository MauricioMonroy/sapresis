import axios from "axios";
import React, { useEffect, useState } from "react";

export default function ListadoConsultorios() {
  const urlBase = "http://localhost:8080/sipress-app/consultorios";
  const [consultorios, setConsultorios] = useState([]);

  useEffect(() => {
    cargarConsultorios();
  }, []);

  const cargarConsultorios = async () => {
    const resultado = await axios.get(urlBase);
    console.log("Resultado de cargar registros");
    console.log(resultado.data);
    setConsultorios(resultado.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-9">
          <div className="card" id="contenedor-lista">
            <div className="card-header">
              <h3 className="text-center">
                <i className="fa-solid fa-clipboard-list"></i> Lista de
                Consultorios
              </h3>
            </div>
            <div className="table-responsive">
              <table className="table table-striped table-hover align-middle">
                <thead className="table-dark">
                  <tr>
                    <th>Número de consultorio</th>
                    <th>Paciente</th>
                    <th>Personal</th>
                    <th>Fecha de admisión</th>
                  </tr>
                </thead>
                <tbody>
                  {
                    // Iterar sobre el arreglo de consultorios
                    consultorios.map((consultorio, indice) => (
                      <tr key={indice}>
                        <th scope="row">{consultorio.numeroConsultorio}</th>
                        <td>
                          {consultorio.paciente && (
                            <div>
                              <div>ID: {consultorio.paciente.idPaciente}</div>
                              <div>
                                Nombre: {consultorio.paciente.nombrePaciente}{" "}
                                {consultorio.paciente.apellidoPaciente}
                              </div>
                              <div>
                                Dirección:{" "}
                                {consultorio.paciente.direccionPaciente}
                              </div>
                              <div>
                                Teléfono:{" "}
                                {consultorio.paciente.telefonoPaciente}
                              </div>
                              <div>
                                Email: {consultorio.paciente.emailPaciente}
                              </div>
                            </div>
                          )}
                        </td>
                        <td>
                          {consultorio.personal && (
                            <div>
                              <div>ID: {consultorio.personal.idPersonal}</div>
                              <div>
                                Nombre: {consultorio.personal.nombrePersonal}{" "}
                                {consultorio.personal.apellidoPersonal}
                              </div>
                              <div>
                                Teléfono:{" "}
                                {consultorio.personal.telefonoPersonal}
                              </div>
                              <div>
                                Email: {consultorio.personal.emailPersonal}
                              </div>
                            </div>
                          )}
                        </td>
                        <td>{consultorio.fechaAdmision}</td>
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
