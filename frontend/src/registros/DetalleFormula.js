import axios from "axios";
import React, { useEffect, useState, useCallback } from "react";
import { Link, useParams } from "react-router-dom";
import { NumericFormat } from "react-number-format";

/**
 * Componente funcional que permite ver los detalles de una fórmula médica
 * @param {Function} props.imprimirFormula - Función para imprimir la fórmula
 * @returns El componente de detalles de fórmula médica
 * @requires react, axios, react-router-dom, react-number-format
 * @version 1.0
 * */

export default function DetalleFormula() {
  const urlBase = "https://sipress-backend.onrender.com/sipress-app/formulas";

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
      const token = localStorage.getItem("token");
      const resultado = await axios.get(`${urlBase}/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
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
    printWindow.document.write(`
      <html>
        <head>
         <style>            
            .logo {
              width: 150px;
              height: auto;
              margin-bottom: 20px;
              justify-content: center;
              content-align: center;
              display: block;
            }
          </style>
        <img src="/images/print-logo.png" alt="Logotipo" class="logo" />
        <title clasname="text-center">Detalles de la Factura</title>         
        </head>
        <body>          
          ${content}
        </body>
      </html>
    `);
    printWindow.document.close();
    printWindow.print();
  };

  return (
    <div className="p-4 mb-2 mt-5" id="detalle">
      <div className="row justify-content-center">
        <div className="col-lg-9 col-md-12">
          <div className="card mt-3">
            <div className="card-header">
              <h1 className="modal-title fs-5 text-center">
                Detalles de la Fórmula Médica
              </h1>
            </div>
            <div className="card-body">
              <h5 className="card-title text-wrap">
                Número de Fórmula: {numeroFormula}
              </h5>
              <p className="card-text text-wrap">
                Medicamento: {nombreMedicacion}
              </p>
              <p className="card-text text-wrap">
                Fecha de formulación: {fechaMedicacion}
              </p>
              <p className="card-text text-wrap">
                Costo de la medicación:{" "}
                <NumericFormat
                  value={costoMedicacion}
                  displayType={"text"}
                  thousandSeparator={true}
                  prefix={"$"}
                />
              </p>
              <h6 className="text-wrap">Detalles del Paciente</h6>
              <p className="card-text text-wrap">ID: {idPaciente}</p>
              <p className="card-text text-wrap">
                Nombre: {formula.paciente.nombrePaciente}{" "}
                {formula.paciente.apellidoPaciente}
              </p>
              <p className="card-text text-wrap">
                Dirección: {formula.paciente.direccionPaciente}
              </p>
              <p className="card-text text-wrap">
                Teléfono: {formula.paciente.telefonoPaciente}
              </p>
              <p className="card-text text-wrap">
                Email: {formula.paciente.emailPaciente}
              </p>
              <h6 className="text-wrap">EPS</h6>
              <p className="card-text text-wrap">
                Nombre EPS: {formula.paciente.eps.nombreEps}
              </p>
              <p className="card-text text-wrap">
                Teléfono EPS: {formula.paciente.eps.telefonoEps}
              </p>
              <p className="card-text text-wrap">
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
