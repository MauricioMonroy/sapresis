import axios from "axios";
import React, { useEffect, useState, useCallback } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import Calendario from "../comunes/Calendario";
import BasicTimePicker from "../comunes/BasicTimePicker";
import dayjs from "dayjs";

export default function EditarConsulta() {
  const urlBase = "http://localhost:8080/sipress-app/consultas";

  let navigate = useNavigate();
  const { pacienteId, doctorId } = useParams();

  const [consulta, setConsulta] = useState({
    fechaConsulta: "",
    horaConsulta: "",
    paciente: { idPaciente: "" },
    doctor: { idDoctor: "" },
  });

  const [pacientes, setPacientes] = useState([]);
  const [doctores, setDoctores] = useState([]);

  const cargarPacientes = useCallback(async () => {
    try {
      const resultado = await axios.get(
        "http://localhost:8080/sipress-app/pacientes"
      );
      setPacientes(resultado.data);
    } catch (error) {
      console.error("Error al cargar los pacientes:", error);
    }
  }, []);

  const cargarDoctores = useCallback(async () => {
    try {
      const resultado = await axios.get(
        "http://localhost:8080/sipress-app/doctores"
      );
      setDoctores(resultado.data);
    } catch (error) {
      console.error("Error al cargar los doctores:", error);
    }
  }, []);

  const cargarConsulta = useCallback(async () => {
    try {
      const resultado = await axios.get(`${urlBase}/${pacienteId}/${doctorId}`);
      setConsulta(resultado.data);
    } catch (error) {
      console.error("Error al cargar la consulta:", error);
    }
  }, [pacienteId, doctorId]);

  useEffect(() => {
    cargarPacientes();
    cargarDoctores();
    cargarConsulta();
  }, [cargarPacientes, cargarDoctores, cargarConsulta]);

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
      .toLocaleDateString("es-ES", {
        day: "2-digit",
        month: "2-digit",
        year: "numeric",
      })
      .replace(/\//g, "-");
    setConsulta((prevConsulta) => ({
      ...prevConsulta,
      fechaConsulta: dateStr,
    }));
  };

  const onTimeChange = (newValue) => {
    const timeStr = newValue.format("HH:mm:ss");
    setConsulta((prevConsulta) => ({
      ...prevConsulta,
      horaConsulta: timeStr,
    }));
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`${urlBase}/${pacienteId}/${doctorId}`, consulta);
      navigate("/consultas");
    } catch (error) {
      console.error("Error al actualizar la consulta:", error);
    }
  };

  return (
    <div className="p-4">
      <div className="row justify-content-center">
        <div className="col-lg-9">
          <div className="card" id="details">
            <div className="card-header">
              <h4>Modificar Consulta</h4>
            </div>
            <form onSubmit={onSubmit}>
              <div className="card-body">
                <div className="form-floating form-group mb-3">
                  <select
                    className="form-control"
                    id="idPaciente"
                    name="idPaciente"
                    required
                    value={consulta.paciente.idPaciente}
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
                    value={consulta.doctor.idDoctor}
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
                    value={consulta.fechaConsulta}
                  />
                  <label htmlFor="fechaConsulta">Fecha de Consulta </label>
                </div>
                <div className="form-floating form-group mb-3">
                  <span>
                    <BasicTimePicker
                      value={dayjs(consulta.horaConsulta, "HH:mm:ss")}
                      onChange={onTimeChange}
                    />
                  </span>
                  <label htmlFor="horaConsulta"></label>
                </div>
                <div>
                  <button type="submit" className="btn btn-primary">
                    Guardar Cambios
                  </button>
                  <Link to="/consultas">
                    <i className="fa-solid fa-triangle-exclamation"></i>{" "}
                    Cancelar
                  </Link>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
