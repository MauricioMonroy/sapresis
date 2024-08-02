import axios from "axios";
import React, { useEffect, useState, useCallback } from "react";
import { Link, useParams } from "react-router-dom";
import { NumericFormat } from "react-number-format";

export default function DetalleFormula() {
  const urlBase = "http://localhost:8080/sipress-app/formulas";

  const { id } = useParams();

  const [formula, setFormula] = useState({
    numeroFormula: "",
    nombreMedicacion: "",
    fechaMedicacion: "",
    costoMedicacion: "",
    paciente: {
      idPaciente: "",
      nombrePaciente: "",
      apellidoPaciente: "",
      direccionPaciente: "",
      telefonoPaciente: "",
      emailPaciente: "",
      eps: {
        idEps: "",
        nombreEps: "",
        telefonoEps: "",
        emailEps: "",
      },
    },
  });

  const {
    numeroFormula,
    nombreMedicacion,
    fechaMedicacion,
    costoMedicacion,
    paciente: { idPaciente },
  } = formula;

  const cargarFormula = useCallback(async () => {
    try {
      const resultado = await axios.get(`${urlBase}/${id}`);
      setFormula(resultado.data);
    } catch (error) {
      console.error("Error al cargar la fórmula:", error);
    }
  }, [id]);

  useEffect(() => {
    cargarFormula();
  }, [cargarFormula]);

  const imprimirFormula = () => {
    const content = document.getElementById("detalle").innerHTML;
    const printWindow = window.open("", "", "height=600,width=800");
    printWindow.document.write(
      "<html><head><title>Detalles de la Fórmula</title>"
    );
    printWindow.document.write("</head><body>");
    printWindow.document.write(content);
    printWindow.document.write("</body></html>");
    printWindow.document.close();
    printWindow.print();
  };

  return (
    <div className="p-4" id="detalle">
      <div className="row justify-content-center">
        <div className="col-lg-9">
          <div className="card">
            <div className="card-header">
              <h1 className="modal-title fs-5">
                Detalles de la Fórmula Médica
              </h1>
            </div>
            <div className="card-body">
              <h5 className="card-title">Número de Fórmula: {numeroFormula}</h5>
              <p className="card-text">Medicamento: {nombreMedicacion}</p>
              <p className="card-text">
                Fecha de formulación: {fechaMedicacion}
              </p>
              <p className="card-text">
                Costo de la medicación:{" "}
                <NumericFormat
                  value={costoMedicacion}
                  displayType={"text"}
                  thousandSeparator={true}
                  prefix={"$"}
                />
              </p>
              <h6>Detalles del Paciente</h6>
              <p className="card-text">ID: {idPaciente}</p>
              <p className="card-text">
                Nombre: {formula.paciente.nombrePaciente}{" "}
                {formula.paciente.apellidoPaciente}
              </p>
              <p className="card-text">
                Dirección: {formula.paciente.direccionPaciente}
              </p>
              <p className="card-text">
                Teléfono: {formula.paciente.telefonoPaciente}
              </p>
              <p className="card-text">
                Email: {formula.paciente.emailPaciente}
              </p>
              <h6>EPS</h6>
              <p className="card-text">
                Nombre EPS: {formula.paciente.eps.nombreEps}
              </p>
              <p className="card-text">
                Teléfono EPS: {formula.paciente.eps.telefonoEps}
              </p>
              <p className="card-text">
                Email EPS: {formula.paciente.eps.emailEps}
              </p>

              <button className="btn btn-success" onClick={imprimirFormula}>
                <i className="fa-solid fa-print"></i> Imprimir
              </button>
              <Link to="/formulas" className="btn btn-info">
                <i className="fa-solid fa-arrow-left-long"></i> Volver a la
                lista
              </Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
