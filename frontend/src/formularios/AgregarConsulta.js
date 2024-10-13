import React, { useState, useEffect, useRef } from "react";
import PropTypes from "prop-types";
import axios from "axios";
import Calendario from "../comunes/Calendario";
import BasicTimePicker from "../comunes/BasicTimePicker";
import dayjs from "dayjs";
import { toast } from "react-toastify";

/**
 * Componente funcional que renderiza el modal para agregar una consulta
 * @param {Object} props Las propiedades del componente
 * @param {Function} props.onConsultaAdded Función que se ejecuta cuando se agrega una consulta
 * @returns El componente de formulario para agregar una consulta
 * @requires react, axios, react-toastify, Calendario, BasicTimePicker, dayjs
 * @version 1.0
 */

export default function AgregarConsulta({ onConsultaAdded }) {
  const modalRef = useRef(null);
  const [error, setError] = useState(null);

  const [consulta, setConsulta] = useState({
    fechaConsulta: "",
    horaConsulta: "",
    paciente: { idPaciente: "" },
    doctor: { idDoctor: "" },
  });

  const { fechaConsulta, horaConsulta, paciente, doctor } = consulta;

  const [pacientes, setPacientes] = useState([]);
  const [doctores, setDoctores] = useState([]);

  useEffect(() => {
    const cargarPacientes = async () => {
      const token = localStorage.getItem("token");
      try {
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
        console.error("Error al cargar los registros de Paciente", error);
      }
    };
    cargarPacientes();
  }, []);

  useEffect(() => {
    const cargarDoctores = async () => {
      const token = localStorage.getItem("token");
      try {
        const resultado = await axios.get(
          process.env.REACT_APP_API_URL + "/sapresis/doctores",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        setDoctores(resultado.data);
        setError(null);
      } catch (error) {
        setError("Error al cargar los registros de Doctor");
        console.error("Error al cargar los registros de Doctor", error);
      }
    };
    cargarDoctores();
  }, []);

  const onInputChange = (e) => {
    const { name, value } = e.target;
    setConsulta((prevConsulta) => {
      if (name === "idPaciente") {
        return { ...prevConsulta, paciente: { idPaciente: value } };
      } else if (name === "idDoctor") {
        return { ...prevConsulta, doctor: { idDoctor: value } };
      } else {
        return { ...prevConsulta, [name]: value };
      }
    });
  };

  const onDateClick = (date) => {
    const dateStr = date
      .toLocaleDateString("es-CO", {
        year: "numeric",
        month: "2-digit",
        day: "2-digit",
      })
      .replace(/\//g, "-");
    setConsulta((prevConsulta) => ({
      ...prevConsulta,
      fechaConsulta: dateStr,
    }));
  };

  const onTimeChange = (newValue) => {
    const date = new Date(newValue);    
    const timeStr = date.toLocaleTimeString({
      hour: "2-digit",
      minute: "2-digit",
      second: "2-digit",
      hour12: false,
    });
    setConsulta((prevConsulta) => ({
      ...prevConsulta,
      horaConsulta: timeStr,
    }));
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    console.log(consulta);
    const urlBase = process.env.REACT_APP_API_URL + "/sapresis/consultas";
    const token = localStorage.getItem("token");
    try {
      const response = await axios.post(urlBase, consulta, {
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
        onConsultaAdded();
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
      id="AgregarConsultaModal"
      tabIndex="-1"
      aria-labelledby="AgregarConsultaModalLabel"
      aria-hidden="true"
      ref={modalRef}>
      <div className="modal-dialog modal-lg">
        <div className="modal-content bg-light">
          <div className="modal-header">
            <h1 className="modal-title fs-5" id="AgregarConsultaModalLabel">
              Registro de Consultas
            </h1>
            <button
              type="button"
              className="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close">
              <span></span>
            </button>
          </div>
          <form onSubmit={onSubmit}>
            <div className="modal-body">
              {error && <p className="fs-5">{error}</p>}
              <div className="form-floating form-group mb-3">
                <select
                  className="form-control"
                  id="idPaciente"
                  name="idPaciente"
                  required
                  value={paciente.idPaciente}
                  onChange={onInputChange}>
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

              <div className="form-floating form-group mb-3">
                <select
                  className="form-control"
                  id="idDoctor"
                  name="idDoctor"
                  required
                  value={doctor.idDoctor}
                  onChange={onInputChange}>
                  <option value="">Seleccione un Doctor</option>
                  {doctores.map((doctor) => (
                    <option key={doctor.idDoctor} value={doctor.idDoctor}>
                      {doctor.nombreDoctor} {doctor.apellidoDoctor}
                    </option>
                  ))}
                </select>
                <label htmlFor="idDoctor">Doctor</label>
              </div>

              <div className="mb-3">
                <Calendario onDateClick={onDateClick} />
              </div>
              <div className="form-floating form-group mb-3">
                <input
                  type="text"
                  className="form-control"
                  id="fechaConsulta"
                  name="fechaConsulta"
                  placeholder="Fecha de Consulta"
                  readOnly
                  value={fechaConsulta}
                />
                <label htmlFor="fechaConsulta">Fecha de Consulta </label>
              </div>
              <div className="form-floating form-group mb-3">
                <span>
                  <BasicTimePicker
                    value={dayjs(horaConsulta, "HH:mm:ss")}
                    onChange={onTimeChange}
                  />
                </span>
                <label htmlFor="horaConsulta"></label>
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
AgregarConsulta.propTypes = {
  onConsultaAdded: PropTypes.func.isRequired,
};
