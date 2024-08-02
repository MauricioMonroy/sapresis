import axios from "axios";
import React, { useEffect, useState } from "react";
import AgregarFactura from "../formularios/AgregarFactura";
import { Link } from "react-router-dom";

export default function ListadoFacturas() {
  const urlBase = "http://localhost:8080/sipress-app/facturas";
  const [facturas, setFacturas] = useState([]);

  useEffect(() => {
    cargarFacturas();
  }, []);

  const cargarFacturas = async () => {
    const resultado = await axios.get(urlBase);
    console.log("Resultado de cargar registros");
    console.log(resultado.data);
    setFacturas(resultado.data);
  };

  const eliminarFactura = async (id) => {
    const confirmacion = window.confirm(
      "¿Está seguro que desea eliminar la factura?"
    );
    if (confirmacion) {
      await axios.delete(`${urlBase}/${id}`);
      cargarFacturas();
    }
  };

  return (
    <div className="p-3">
      <section>
        <AgregarFactura onFacturaAdded={cargarFacturas} />
        <div id="actions">
          <div className="row justify-content-center">
            <div className="col-12 col-md-4 d-flex justify-content-center">
              <a href="/" className="btn btn-info">
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
              <i class="fa-solid fa-file-invoice-dollar"></i> Lista de Fórmulas
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
                        <td className="textCenter">
                          <div>
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
