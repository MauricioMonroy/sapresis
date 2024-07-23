import axios from "axios";
import React, { useEffect, useState } from "react";

export default function ListadoConsultas() {
  const urlBase = "http://localhost:8080/sipress-app/consultas";
  const [consultas, setConsultas] = useState([]);

  useEffect(() => {
    cargarConsultas();
  }, []);

  const cargarConsultas = async () => {
    const resultado = await axios.get(urlBase);
    console.log("Resultado de cargar registros");
    console.log(resultado.data);
    setConsultas(resultado.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-9">
          <div className="card" id="contenedor-lista">
            <div className="card-header">
              <h3 className="text-center">
                <i className="fa-solid fa-clipboard-list"></i> Lista de
                Consultas
              </h3>
            </div>
            <div className="table-responsive">
              <table className="table table-striped table-hover align-middle">
                <thead className="table-dark">
                  <tr>
                    <th>Paciente</th>
                    <th>Doctor</th>
                    <th>Fecha de consulta</th>
                    <th>Hora de consulta</th>
                  </tr>
                </thead>
                <tbody>
                  {
                    // Iterar sobre el arreglo de consultas
                    consultas.map((consulta, indice) => (
                      <tr key={indice}>
                        <td>
                          {consulta.paciente && (
                            <div>
                              <div>ID: {consulta.paciente.idPaciente}</div>
                              <div>
                                Nombre: {consulta.paciente.nombrePaciente}{" "}
                                {consulta.paciente.apellidoPaciente}
                              </div>
                              <div>
                                Dirección: {consulta.paciente.direccionPaciente}
                              </div>
                              <div>
                                Teléfono: {consulta.paciente.telefonoPaciente}
                              </div>
                              <div>
                                Email: {consulta.paciente.emailPaciente}
                              </div>
                            </div>
                          )}
                        </td>
                        <td>
                          {consulta.doctor && (
                            <div>
                              <div>ID: {consulta.doctor.idDoctor}</div>
                              <div>
                                Nombre: {consulta.doctor.nombreDoctor}{" "}
                                {consulta.doctor.apellidoDoctor}
                              </div>
                              <div>
                                Teléfono: {consulta.doctor.telefonoDoctor}
                              </div>
                              <div>Email: {consulta.doctor.emailDoctor}</div>
                            </div>
                          )}
                        </td>
                        <td>{consulta.fechaConsulta}</td>
                        <td>{consulta.horaConsulta}</td>
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
