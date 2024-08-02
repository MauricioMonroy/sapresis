import axios from "axios";
import React, { useEffect, useState, useCallback } from "react";
import { Link, useParams } from "react-router-dom";
import { NumericFormat } from "react-number-format";

export default function DetalleFactura() {
  const urlBase = "http://localhost:8080/sipress-app/facturas";

  const { id } = useParams();

  const [factura, setFactura] = useState({
    numeroFactura: "",
    descripcionServicio: "",
    valor: "",
    total: "",
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
    numeroFactura,
    descripcionServicio,
    valor,
    total,
    paciente: { idPaciente },
  } = factura;

  const cargarFactura = useCallback(async () => {
    try {
      const resultado = await axios.get(`${urlBase}/${id}`);
      setFactura(resultado.data);
    } catch (error) {
      console.error("Error al cargar la factura:", error);
    }
  }, [id]);

  useEffect(() => {
    cargarFactura();
  }, [cargarFactura]);

  const imprimirFactura = () => {
    const content = document.getElementById("detalle").innerHTML;
    const printWindow = window.open("", "", "height=600,width=800");
    printWindow.document.write(
      "<html><head><title>Detalles de la Factura</title>"
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
                Detalles de la Factura Médica
              </h1>
            </div>
            <div className="card-body">
              <h5 className="card-title">Número de Factura: {numeroFactura}</h5>
              <p className="card-text">
                Descripción del servicio: {descripcionServicio}
              </p>
              <p className="card-text">
                Fecha de facturación:
                <NumericFormat
                  value={valor}
                  displayType={"text"}
                  thousandSeparator={true}
                  prefix={"$"}
                />
              </p>
              <p className="card-text">
                Pago Total:{" "}
                <NumericFormat
                  value={total}
                  displayType={"text"}
                  thousandSeparator={true}
                  prefix={"$"}
                />
              </p>
              <h6>Detalles del Paciente</h6>
              <p className="card-text">ID: {idPaciente}</p>
              <p className="card-text">
                Nombre: {factura.paciente.nombrePaciente}{" "}
                {factura.paciente.apellidoPaciente}
              </p>
              <p className="card-text">
                Dirección: {factura.paciente.direccionPaciente}
              </p>
              <p className="card-text">
                Teléfono: {factura.paciente.telefonoPaciente}
              </p>
              <p className="card-text">
                Email: {factura.paciente.emailPaciente}
              </p>
              <h6>EPS</h6>
              <p className="card-text">
                Nombre EPS: {factura.paciente.eps.nombreEps}
              </p>
              <p className="card-text">
                Teléfono EPS: {factura.paciente.eps.telefonoEps}
              </p>
              <p className="card-text">
                Email EPS: {factura.paciente.eps.emailEps}
              </p>

              <button className="btn btn-success" onClick={imprimirFactura}>
                <i className="fa-solid fa-print"></i> Imprimir
              </button>
              <Link to="/facturas" className="btn btn-info">
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
