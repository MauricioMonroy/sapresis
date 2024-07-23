import axios from "axios";
import React, { useEffect, useState } from "react";
import { NumericFormat } from "react-number-format";

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

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-9">
          <div className="card" id="contenedor-lista">
            <div className="card-header">
              <h3 className="text-center">
                <i className="fa-solid fa-clipboard-list"></i> Lista de Fórmulas
              </h3>
            </div>
            <div className="table-responsive">
              <table className="table table-striped table-hover align-middle">
                <thead className="table-dark">
                  <tr>
                    <th>Número de factura</th>
                    <th>Paciente</th>
                    <th>Descripción</th>
                    <th>Valor servicio</th>
                    <th>Total a pagar</th>
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
                              <div>ID: {factura.paciente.idPaciente}</div>
                              <div>
                                Nombre: {factura.paciente.nombrePaciente}{" "}
                                {factura.paciente.apellidoPaciente}
                              </div>
                              <div>
                                Dirección: {factura.paciente.direccionPaciente}
                              </div>
                              <div>
                                Teléfono: {factura.paciente.telefonoPaciente}
                              </div>
                              <div>Email: {factura.paciente.emailPaciente}</div>
                            </div>
                          )}
                        </td>
                        <td>{factura.descripcionServicio}</td>
                        <td>
                          <NumericFormat
                            value={factura.valor}
                            displayType={"text"}
                            thousandSeparator=","
                            prefix={"$"}
                            decimalScale={2}
                            fixedDecimalScale
                          />
                        </td>
                        <td>
                          <NumericFormat
                            value={factura.total}
                            displayType={"text"}
                            thousandSeparator=","
                            prefix={"$"}
                            decimalScale={2}
                            fixedDecimalScale
                          />
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
