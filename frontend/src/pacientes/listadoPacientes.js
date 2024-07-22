import axios from "axios";
import React, { useEffect, useState } from "react";

export default function ListadoPacientes() {
  const urlBase = "http://localhost:8080/sipress-app/pacientes";
  const [pacientes, setPacientes] = useState([]);

  useEffect(() => {
    cargarPacientes();
  }, []);

  const cargarPacientes = async () => {
    const resultado = await axios.get(urlBase);
    console.log("Resultado de cargar registros");
    console.log(resultado.data);
    setPacientes(resultado.data);
  };

  return (
    <div className="container">
      <div className="container">
        <div className="row">
          <div className="col-md-9">
            <div className="card" id="contenedor-lista">
              <div className="card-header">
                <h3 className="text-center">
                  <i className="fa-solid fa-clipboard-list"></i> Lista de
                  Pacientes
                </h3>
              </div>
              <div className="table-responsive">
                <table className="table table-striped table-hover align-middle">
                  <thead className="table-dark">
                    <tr>
                      <th>ID Paciente</th>
                      <th>Nombre </th>
                      <th>Apellido</th>
                      <th>Dirección</th>
                      <th>Teléfono</th>
                      <th>Email</th>
                      <th>EPS</th>
                      <th>Acciones</th>
                    </tr>
                  </thead>
                  <tbody>
                    {
                      // Iterar sobre el arreglo de pacientes
                      pacientes.map((paciente, indice) => (
                        <tr key={indice}>
                          <th scope="row">{paciente.idPaciente}</th>
                          <td>{paciente.nombrePaciente}</td>
                          <td>{paciente.apellidoPaciente}</td>
                          <td>{paciente.direccionPaciente}</td>
                          <td>{paciente.telefonoPaciente}</td>
                          <td>{paciente.emailPaciente}</td>
                          <td>
                            {paciente.eps && (
                              <div>
                                <div>ID: {paciente.eps.idEps}</div>
                                <div>Nombre: {paciente.eps.nombreEps}</div>
                                <div>Teléfono: {paciente.eps.telefonoEps}</div>
                                <div>Email: {paciente.eps.emailEps}</div>
                              </div>
                            )}
                          </td>
                          <td>
                            <a href="index.html" className="btn btn-danger">
                              <i className="fa-regular fa-trash"></i> Eliminar
                            </a>
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
    </div>
  );
}
