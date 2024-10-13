import React, { useState, useEffect, useRef } from "react";
import PropTypes from "prop-types";
import axios from "axios";
import { toast } from "react-toastify";

/**
 * Componente funcional que renderiza el modal para agregar una factura médica
 * @param {Object} props Las propiedades del componente
 * @param {Function} props.onFacturaAdded Función que se ejecuta cuando se agrega una factura médica
 * @returns El componente de formulario para agregar una factura médica
 * @requires react, axios, react-toastify, useRef, useState, useEffect
 * @version 1.0
 * */

export default function AgregarFactura({ onFacturaAdded }) {
  const modalRef = useRef(null);
  const [error, setError] = useState(null);

  const [factura, setFactura] = useState({
    numeroFactura: "",
    descripcionServicio: "",
    valor: "",
    total: "",
    paciente: { idPaciente: "" },
  });

  const { numeroFactura, descripcionServicio, valor, total, paciente } =
    factura;

  const [pacientes, setPacientes] = useState([]);

  useEffect(() => {
    // Cargar los pacientes al montar el componente
    const cargarPacientes = async () => {
      try {
        const token = localStorage.getItem("token");
        const resultado = await axios.get(
          process.env.REACT_APP_API_URL + "/sapresis/pacientes",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        setPacientes(resultado.data);
        setError(null);
      } catch (error) {
        setError("Error al cargar los registros de Paciente");
        console.error("Error al cargar registros", error);
      }
    };

    cargarPacientes();
  }, []);

  const onInputChange = (e) => {
    const { name, value } = e.target;
    if (name === "idPaciente") {
      setFactura((prevFactura) => ({
        ...prevFactura,
        paciente: { idPaciente: value },
      }));
    } else {
      setFactura({ ...factura, [name]: value });
    }
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    const urlBase = process.env.REACT_APP_API_URL + "/sapresis/facturas";
    const token = localStorage.getItem("token");

    try {
      const response = await axios.post(urlBase, factura, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      // Verificar si el código de estado es 201 Created
      if (response.status === 201) {
        // Cerrar el modal manualmente
        const modalElement = modalRef.current;
        if (modalElement) {
          const modalInstance = new window.bootstrap.Modal(modalElement);
          modalInstance.hide();
        }

        // Llamar a la función de actualización de la lista
        onFacturaAdded();
        toast.success("Registro agregado correctamente");
      } else {
        toast.error("Error: no se pudo agregar el registro.");
      }
    } catch (error) {
      // Manejar cualquier error de la petición
      toast.error(
        "Error: " +
          (error.response?.data?.message || "Error al agregar el registro")
      );
    }
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
              {error && <p className="fs-5">{error}</p>}
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
AgregarFactura.propTypes = {
  onFacturaAdded: PropTypes.func.isRequired,
};
