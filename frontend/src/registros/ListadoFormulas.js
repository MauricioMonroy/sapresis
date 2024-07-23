import axios from "axios";
import React, { useEffect, useState } from "react";
import { NumericFormat } from "react-number-format";

export default function ListadoFormulas() {
  const urlBase = "http://localhost:8080/sipress-app/formulas";
  const [formulas, setFormulas] = useState([]);

  useEffect(() => {
    cargarFormulas();
  }, []);

  const cargarFormulas = async () => {
    const resultado = await axios.get(urlBase);
    console.log("Resultado de cargar registros");
    console.log(resultado.data);
    setFormulas(resultado.data);
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
                    <th>Número de fórmula</th>
                    <th>Paciente</th>
                    <th>Medicación</th>
                    <th>Fecha de formulación</th>
                    <th>Valor de la medicación</th>
                  </tr>
                </thead>
                <tbody>
                  {
                    // Iterar sobre el arreglo de formulas
                    formulas.map((formula, indice) => (
                      <tr key={indice}>
                        <th scope="row">{formula.numeroFormula}</th>
                        <td>
                          {formula.paciente && (
                            <div>
                              <div>ID: {formula.paciente.idPaciente}</div>
                              <div>
                                Nombre: {formula.paciente.nombrePaciente}{" "}
                                {formula.paciente.apellidoPaciente}
                              </div>
                              <div>
                                Dirección: {formula.paciente.direccionPaciente}
                              </div>
                              <div>
                                Teléfono: {formula.paciente.telefonoPaciente}
                              </div>
                              <div>Email: {formula.paciente.emailPaciente}</div>
                            </div>
                          )}
                        </td>
                        <td>{formula.nombreMedicacion}</td>
                        <td>{formula.fechaMedicacion}</td>
                        <td>
                          <NumericFormat
                            value={formula.costoMedicacion}
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
