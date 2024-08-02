import axios from "axios";
import React, { useState, useEffect, useRef } from "react";

export default function AgregarFactura({ onFacturaAdded }) {
  const modalRef = useRef(null);

  const [factura, setFactura] = useState({
    numeroFactura: "",
    descripcionServicio: "",
    valor: "",
    total: "",
    paciente: { idPaciente: "" }, // Cambiar a objeto Paciente
  });

  const { numeroFactura, descripcionServicio, valor, total, paciente } =
    factura;

  const [pacientes, setPacientes] = useState([]);

  useEffect(() => {
    // Cargar los pacientes al montar el componente
    const cargarPacientes = async () => {
      const resultado = await axios.get(
        "http://localhost:8080/sipress-app/pacientes"
      );
      setPacientes(resultado.data);
    };

    cargarPacientes();
  }, []);

  const onInputChange = (e) => {
    const { name, value } = e.target;
    if (name === "idPaciente") {
      setFactura((prevFactura) => ({
        ...prevFactura,
        paciente: { idPaciente: value }, // Actualizar el objeto Paciente
      }));
    } else {
      setFactura({ ...factura, [name]: value });
    }
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    const urlBase = "http://localhost:8080/sipress-app/facturas";
    await axios.post(urlBase, factura);

    // Cerrar el modal manualmente
    const modalElement = modalRef.current;
    if (modalElement) {
      const modalInstance = new window.bootstrap.Modal(modalElement);
      modalInstance.hide();
    }

    // Llamar a la función de actualización de la lista
    onFacturaAdded();
  };

  return (
    <div
      className="modal fade"
      id="AgregarFacturaModal"
      tabIndex="-1"
      aria-labelledby="AgregarFacturaModalLabel"
      aria-hidden="true"
      ref={modalRef}>
      <div className="modal-dialog modal-lg">
        <div className="modal-content bg-light">
          <div className="modal-header">
            <h1 className="modal-title fs-5" id="AgregarFacturaModalLabel">
              Registro de Facturas Médicas
            </h1>
            <button
              type="button"
              className="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close">
              <span></span>
            </button>
          </div>
          <form onSubmit={(e) => onSubmit(e)}>
            <div className="modal-body">
              <div className="form-floating form-group mb-3">
                <input
                  type="text"
                  className="form-control"
                  id="numeroFactura"
                  name="numeroFactura"
                  placeholder="Número de Factura Médica"
                  required={true}
                  value={numeroFactura}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="numeroFactura">Número de Factura Médica</label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="text"
                  className="form-control"
                  id="descripcionServicio"
                  name="descripcionServicio"
                  placeholder="Descripción del servicio"
                  required={true}
                  value={descripcionServicio}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="descripcionServicio">
                  Descripción del servicio
                </label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="number"
                  step="any"
                  className="form-control"
                  id="valor"
                  name="valor"
                  placeholder="Valor del Servicio"
                  required={true}
                  value={valor}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="valor">Valor del Servicio</label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="number"
                  step="any"
                  className="form-control"
                  id="total"
                  name="total"
                  placeholder="Costo Total"
                  required={true}
                  value={total}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="total">Costo Total</label>
              </div>

              <div className="form-floating form-group mb-3">
                <select
                  className="form-control"
                  id="idPaciente"
                  name="idPaciente"
                  required={true}
                  value={paciente.idPaciente} // Usar el ID de la paciente
                  onChange={(e) => onInputChange(e)}>
                  <option value="">Seleccione un Paciente</option>
                  {pacientes.map((paciente) => (
                    <option
                      key={paciente.idPaciente}
                      value={paciente.idPaciente}>
                      {paciente.nombrePaciente} {paciente.apellidoPaciente}
                    </option>
                  ))}
                </select>
                <label htmlFor="idPaciente">Paciente</label>
              </div>
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary btn-sm me-3"
                data-bs-dismiss="modal">
                <i className="fa-regular fa-rectangle-xmark"></i> Cerrar
              </button>
              <button
                type="submit"
                className="btn btn-primary"
                data-bs-dismiss="modal">
                <i className="fa-solid fa-folder-plus"></i> Guardar registro
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
