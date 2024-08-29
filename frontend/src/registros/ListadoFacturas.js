import axios from "axios";
import React, { useEffect, useState } from "react";
import AgregarFactura from "../formularios/AgregarFactura";
import { Link, useNavigate } from "react-router-dom";

export default function ListadoFacturas() {
  const urlBase = "http://localhost:8080/sipress-app/facturas";
  const [facturas, setFacturas] = useState([]);
  const [error, setError] = useState(null);
  let navigate = useNavigate();

  const cargarFacturas = async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await axios.get(urlBase, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setFacturas(response.data);
      setError(null);
    } catch (error) {
      setError("Error al cargar los registros");
      console.error("Error al cargar registros:", error);
    }
  };

  useEffect(() => {
    cargarFacturas();
  }, []);

  const eliminarFactura = async (id) => {
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
        cargarFacturas();
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
        <AgregarFactura onFacturaAdded={cargarFacturas} />
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
                data-bs-target="#AgregarFacturaModal">
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
                <i className="fa-solid fa-file-invoice-dollar"></i> Lista de
                Facturas
              </h3>
            </div>
            <div className="table-responsive">
              <table className="table table-striped table-hover align-middle">
                <thead className="table-dark">
                  <tr>
                    <th>Número de factura</th>
                    <th>Paciente</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  {
                    // Iterar sobre el arreglo de facturas
                    facturas.map((factura, indice) => (
                      <tr key={indice}>
                        <th scope="row">{factura.numeroFactura}</th>
                        <td>
                          {factura.paciente && (
                            <div>
                              <div>
                                Nombre: {factura.paciente.nombrePaciente}{" "}
                                {factura.paciente.apellidoPaciente}
                              </div>
                              <div>ID: {factura.paciente.idPaciente}</div>
                            </div>
                          )}
                        </td>
                        <td>
                          <div className="textCenter">
                            <Link
                              to={`/facturas/detalle/${factura.numeroFactura}`}
                              className="btn btn-info btn-sm me-2">
                              <i className="fa-regular fa-eye"></i> Detalle
                            </Link>
                            <Link
                              to={`/facturas/editar/${factura.numeroFactura}`}
                              className="btn btn-warning btn-sm me-2">
                              <i className="fa-regular fa-pen-to-square"></i>{" "}
                              Editar
                            </Link>
                            <button
                              onClick={() =>
                                eliminarFactura(factura.numeroFactura)
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
