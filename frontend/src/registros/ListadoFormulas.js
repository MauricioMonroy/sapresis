import axios from "axios";
import React, { useEffect, useState } from "react";
import AgregarFormula from "../formularios/AgregarFormula";
import { Link, useNavigate } from "react-router-dom";

export default function ListadoFormulas() {
  const urlBase = "http://localhost:8080/sipress-app/formulas";
  const [formulas, setFormulas] = useState([]);
  const [error, setError] = useState(null);
  let navigate = useNavigate();

  const cargarFormulas = async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await axios.get(urlBase, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setFormulas(response.data);
      setError(null);
    } catch (error) {
      setError("Error al cargar los registros");
      console.error("Error al cargar registros:", error);
    }
  };

  useEffect(() => {
    cargarFormulas();
  }, []);

  const eliminarFormula = async (id) => {
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
        cargarFormulas();
      } catch (error) {
        console.error("Error al eliminar el registro", error);
        if (error.response && error.response.status === 401) {
          navigate("/login");
        }
      }
    }
  };

  return (
    <div className="p-3 mb-2 mt-5">
      <section>
        <AgregarFormula onFormulaAdded={cargarFormulas} />
        {error && <p>Error al cargar los registros: {error.message}</p>}
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
                data-bs-target="#AgregarFormulaModal">
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
                <i className="fa-solid fa-pills"></i> Lista de Fórmulas Médicas
              </h3>
            </div>
            <div className="table-responsive">
              <table className="table table-striped table-hover align-middle">
                <thead className="table-dark">
                  <tr>
                    <th>Número de fórmula</th>
                    <th>Paciente</th>
                    <th>Fecha de formulación</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  {formulas.map((formula, indice) => (
                    <tr key={indice}>
                      <th scope="row">{formula.numeroFormula}</th>
                      <td>
                        {formula.paciente && (
                          <div>
                            <div>
                              {formula.paciente.nombrePaciente}{" "}
                              {formula.paciente.apellidoPaciente}
                            </div>
                            <div>ID: {formula.paciente.idPaciente}</div>
                          </div>
                        )}
                      </td>
                      <td>{formula.fechaMedicacion}</td>
                      <td>
                        <div className="textCenter">
                          <Link
                            to={`/formulas/detalle/${formula.numeroFormula}`}
                            className="btn btn-info btn-sm me-2">
                            <i className="fa-regular fa-eye"></i> Detalle
                          </Link>
                          <Link
                            to={`/formulas/editar/${formula.numeroFormula}`}
                            className="btn btn-warning btn-sm me-2">
                            <i className="fa-regular fa-pen-to-square"></i>{" "}
                            Editar
                          </Link>
                          <button
                            onClick={() =>
                              eliminarFormula(formula.numeroFormula)
                            }
                            className="btn btn-danger btn-sm">
                            <i className="fa-regular fa-trash-can"></i> Eliminar
                          </button>
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
