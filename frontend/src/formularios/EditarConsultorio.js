import axios from "axios";
import React, { useEffect, useState, useCallback } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { toast } from "react-toastify";

export default function EditarConsultorio() {
  const urlBase = "http://localhost:8080/sipress-app/consultorios";

  let navigate = useNavigate();
  const { id } = useParams();

  const [consultorio, setConsultorio] = useState({
    numeroConsultorio: "",
    fechaAdmision: "",
    paciente: { idPaciente: "" },
    personal: { idPersonal: "" },
  });

  const [pacientes, setPacientes] = useState([]);
  const [personalS, setPersonalS] = useState([]);

  const cargarPacientes = useCallback(async () => {
    const token = localStorage.getItem("token");
    try {
      const resultado = await axios.get(
        "http://localhost:8080/sipress-app/pacientes",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setPacientes(resultado.data);
    } catch (error) {
      console.error("Error al cargar los registros de Paciente:", error);
      toast.error("Error al cargar los datos del registro solicitado");
    }
  }, []);

  const cargarPersonalS = useCallback(async () => {
    const token = localStorage.getItem("token");
    try {
      const resultado = await axios.get(
        "http://localhost:8080/sipress-app/personalS",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setPersonalS(resultado.data);
    } catch (error) {
      console.error("Error al cargar los registros de Personal:", error);
      toast.error("Error al cargar los datos del registro solicitado");
    }
  }, []);

  const cargarConsultorio = useCallback(async () => {
    const token = localStorage.getItem("token");
    try {
      const resultado = await axios.get(`${urlBase}/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setConsultorio(resultado.data);
    } catch (error) {
      console.error("Error al cargar el regitro:", error);
      toast.error("Error al cargar los datos del registro solicitado");
    }
  }, [id]);

  useEffect(() => {
    cargarPacientes();
  }, [cargarPacientes]);

  useEffect(() => {
    cargarPersonalS();
  }, [cargarPersonalS]);

  useEffect(() => {
    cargarConsultorio();
  }, [cargarConsultorio]);

  const onInputChange = (e) => {
    const { name, value } = e.target;
    setConsultorio((prevConsultorio) => {
      if (name === "idPaciente") {
        return { ...prevConsultorio, paciente: { idPaciente: value } };
      } else if (name === "idPersonal") {
        return { ...prevConsultorio, personal: { idPersonal: value } };
      } else if (name === "fechaAdmision") {
        const adjustedDate = new Date(value);
        adjustedDate.setMinutes(
          adjustedDate.getMinutes() + adjustedDate.getTimezoneOffset()
        );
        return {
          ...prevConsultorio,
          [name]: adjustedDate.toISOString().split("T")[0],
        };
      } else {
        return { ...prevConsultorio, [name]: value };
      }
    });
  };

  const onSubmit = async (e) => {
    const token = localStorage.getItem("token");
    e.preventDefault();
    try {
      await axios.put(`${urlBase}/${id}`, consultorio, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      toast.success("Registro actualizado con éxito");
      navigate("/consultorios");
    } catch (error) {
      console.error("Error al actualizar el registro:", error);
      toast.error("Error al actualizar el registro");
    }
  };

  return (
    <div className="p-4 mb-2 mt-5">
      <div className="row justify-content-center">
        <div className="col-lg-9">
          <div className="card mt-3" id="details">
            <div className="card-header">
              <h4>Modificar Consultorio</h4>
            </div>
            <form onSubmit={(e) => onSubmit(e)}>
              <div className="card-body">
                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="numeroConsultorio"
                    name="numeroConsultorio"
                    placeholder="Número de Consultorio"
                    required={true}
                    value={consultorio.numeroConsultorio}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="numeroConsultorio">
                    Número de Consultorio
                  </label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="date"
                    className="form-control"
                    id="fechaAdmision"
                    name="fechaAdmision"
                    placeholder="Fecha de Admisión"
                    required={true}
                    value={consultorio.fechaAdmision}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="fechaAdmision">Fecha de Admisión</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <select
                    className="form-select"
                    id="idPaciente"
                    name="idPaciente"
                    required={true}
                    value={consultorio.paciente.idPaciente}
                    onChange={(e) => onInputChange(e)}>
                    <option value="">Seleccionar Paciente</option>
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
                    className="form-select"
                    id="idPersonal"
                    name="idPersonal"
                    required={true}
                    value={consultorio.personal.idPersonal}
                    onChange={(e) => onInputChange(e)}>
                    <option value="">Seleccionar Personal</option>
                    {personalS.map((personal) => (
                      <option
                        key={personal.idPersonal}
                        value={personal.idPersonal}>
                        {personal.nombrePersonal} {personal.apellidoPersonal}
                      </option>
                    ))}
                  </select>
                  <label htmlFor="idPersonal">Personal</label>
                </div>

                <button type="submit" className="btn btn-primary">
                  Guardar Cambios
                </button>
                <Link to="../consultorios">
                  <i className="fa-solid fa-triangle-exclamation"></i> Cancelar
                </Link>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
