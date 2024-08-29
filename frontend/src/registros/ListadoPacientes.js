import axios from "axios";
import React, { useEffect, useState } from "react";
import AgregarPaciente from "../formularios/AgregarPaciente";
import { Link, useNavigate } from "react-router-dom";

export default function ListadoPacientes() {
  const urlBase = "http://localhost:8080/sipress-app/pacientes";
  const [pacientes, setPacientes] = useState([]);
  const [error, setError] = useState(null);
  let navigate = useNavigate();

  const cargarPacientes = async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await axios.get(urlBase, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setPacientes(response.data);
      setError(null);
    } catch (error) {
      setError("Error al cargar los pacientes");
      console.error("Error al cargar pacientes:", error);
    }
  };

  useEffect(() => {
    cargarPacientes();
  }, []);

  const eliminarPaciente = async (id) => {
    const confirmacion = window.confirm(
      "¿Está seguro de que desea eliminar este registro?"
    );
    if (confirmacion) {
      const token = localStorage.getItem("token");
      try {
        await axios.delete(`${urlBase}/${id}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        cargarPacientes();
      } catch (error) {
        console.error("Error al eliminar el paciente", error);
        if (error.response && error.response.status === 401) {
          navigate("/login");
        }
      }
    }
  };

  return (
    <div className="p-3 mb-2 mt-5">
      <section>
        <AgregarPaciente onPacienteAdded={cargarPacientes} />
        {error && <p>Error al cargar registros: {error.message}</p>}
        <div id="actions" className="mt-3">
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
                data-bs-target="#AgregarPacienteModal">
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
                <i className="fa-solid fa-hospital-user"></i> Lista de Pacientes
              </h3>
            </div>
            <div className="table-responsive">
              <table className="table table-striped table-hover align-middle">
                <thead className="table-dark">
                  <tr>
                    <th>ID Paciente</th>
                    <th>Nombre completo</th>
                    <th>Dirección</th>
                    <th>Teléfono</th>
                    <th>Email</th>
                    <th>EPS</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  {
                    // Iterar sobre el arreglo de pacientes
                    pacientes.map((paciente, indice) => (
                      <tr key={indice}>
                        <th scope="row">{paciente.idPaciente}</th>
                        <td>
                          {paciente.nombrePaciente} {paciente.apellidoPaciente}
                        </td>
                        <td>{paciente.direccionPaciente}</td>
                        <td>{paciente.telefonoPaciente}</td>
                        <td>{paciente.emailPaciente}</td>
                        <td>
                          {paciente.eps && <div>{paciente.eps.nombreEps}</div>}
                        </td>
                        <td>
                          <div className="textCenter">
                            <Link
                              to={`/pacientes/editar/${paciente.idPaciente}`}
                              className="btn btn-warning btn-sm me-2">
                              <i className="fa-regular fa-pen-to-square"></i>{" "}
                              Editar
                            </Link>
                            <button
                              onClick={() =>
                                eliminarPaciente(paciente.idPaciente)
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
