import React, { useState, useEffect, useRef } from "react";
import PropTypes from "prop-types";
import axios from "axios";
import { toast } from "react-toastify";

/**
 * Componente funcional que renderiza el modal para agregar una fórmula médica
 * @param {Object} props Las propiedades del componente
 * @param {Function} props.onFormulaAdded Función que se ejecuta cuando se agrega una fórmula médica
 * @returns El componente de formulario para agregar una fórmula médica
 * @requires react, axios, react-toastify, useRef, useState, useEffect
 * @version 1.0
 * */

export default function AgregarFormula({ onFormulaAdded }) {
  const modalRef = useRef(null);
  const [error, setError] = useState(null);

  const [formula, setFormula] = useState({
    numeroFormula: "",
    nombreMedicacion: "",
    fechaMedicacion: "",
    costoMedicacion: "",
    paciente: { idPaciente: "" }, // Cambiar a objeto Paciente
  });

  const {
    numeroFormula,
    nombreMedicacion,
    fechaMedicacion,
    costoMedicacion,
    paciente,
  } = formula;

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
      setFormula((prevFormula) => ({
        ...prevFormula,
        paciente: { idPaciente: value }, // Actualizar el objeto Paciente
      }));
    } else {
      setFormula({ ...formula, [name]: value });
    }
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    const urlBase = process.env.REACT_APP_API_URL + "/sapresis/formulas";
    const token = localStorage.getItem("token");

    try {
      const response = await axios.post(urlBase, formula, {
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
        onFormulaAdded();
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
      id="AgregarFormulaModal"
      tabIndex="-1"
      aria-labelledby="AgregarFormulaModalLabel"
      aria-hidden="true"
      ref={modalRef}>
      <div className="modal-dialog modal-lg">
        <div className="modal-content bg-light">
          <div className="modal-header">
            <h1 className="modal-title fs-5" id="AgregarFormulaModalLabel">
              Registro de Fórmulas Médicas
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
                  id="numeroFormula"
                  name="numeroFormula"
                  placeholder="Número de Fórmula Médica"
                  required={true}
                  value={numeroFormula}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="numeroFormula">Número de Fórmula Médica</label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="text"
                  className="form-control"
                  id="nombreMedicacion"
                  name="nombreMedicacion"
                  placeholder="Nombre de la Medicación"
                  required={true}
                  value={nombreMedicacion}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="nombreMedicacion">
                  Nombre de la Medicación
                </label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="date"
                  className="form-control"
                  id="fechaMedicacion"
                  name="fechaMedicacion"
                  placeholder="Fecha de la Medicación"
                  required={true}
                  value={fechaMedicacion}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="fechaMedicacion">Fecha de la Medicación</label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="number"
                  step="any"
                  className="form-control"
                  id="costoMedicacion"
                  name="costoMedicacion"
                  placeholder="Costo de la Medicación"
                  required={true}
                  value={costoMedicacion}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="costoMedicacion">Costo de la Medicación</label>
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
AgregarFormula.propTypes = {
  onFormulaAdded: PropTypes.func.isRequired,
};
