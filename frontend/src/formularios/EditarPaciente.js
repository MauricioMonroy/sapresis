import axios from "axios";
import React, { useEffect, useState, useCallback } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditarPaciente() {
  const urlBase = "http://localhost:8080/sipress-app/pacientes";

  let navigate = useNavigate();
  const { id } = useParams();

  const [paciente, setPaciente] = useState({
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
  });

  const {
    idPaciente,
    nombrePaciente,
    apellidoPaciente,
    direccionPaciente,
    telefonoPaciente,
    emailPaciente,
    eps: { idEps },
  } = paciente;

  const [epsS, setEpsS] = useState([]);

  const cargarEpsS = useCallback(async () => {
    try {
      const resultado = await axios.get(
        "http://localhost:8080/sipress-app/epsS"
      );
      setEpsS(resultado.data);
    } catch (error) {
      console.error("Error al cargar las eps:", error);
    }
  }, []);

  const cargarPaciente = useCallback(async () => {
    try {
      const resultado = await axios.get(`${urlBase}/${id}`);
      setPaciente(resultado.data);
    } catch (error) {
      console.error("Error al cargar el paciente:", error);
    }
  }, [id]);

  useEffect(() => {
    cargarEpsS();
  }, [cargarEpsS]);

  useEffect(() => {
    cargarPaciente();
  }, [cargarPaciente]);

  const onInputChange = (e) => {
    const { name, value } = e.target;
    if (name.startsWith("eps")) {
      setPaciente({
        ...paciente,
        eps: {
          ...paciente.eps,
        },
      });
    } else if (name.startsWith("eps")) {
      setPaciente({
        ...paciente,
        eps: {
          ...paciente.eps,
          [name.split(".")[1]]: value,
        },
      });
    } else {
      setPaciente({ ...paciente, [name]: value });
    }
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`${urlBase}/${id}`, paciente);
      navigate("/pacientes");
    } catch (error) {
      console.error("Error al actualizar el paciente:", error);
    }
  };

  return (
    <div className="p-4" id="details">
      <div className="row justify-content-center">
        <div className="col-lg-9">
          <div className="card">
            <div className="card-header">
              <h4>Modificar Registro</h4>
            </div>
            <form onSubmit={(e) => onSubmit(e)}>
              <div className="card-body">
                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="idPaciente"
                    name="idPaciente"
                    placeholder="ID Paciente"
                    required={true}
                    value={idPaciente}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="idPaciente">ID del Paciente</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="nombrePaciente"
                    name="nombrePaciente"
                    placeholder="Nombre del Paciente"
                    required={true}
                    value={nombrePaciente}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="nombrePaciente">Nombre del Paciente</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="apellidoPaciente"
                    name="apellidoPaciente"
                    placeholder="Apellido del Paciente"
                    required={true}
                    value={apellidoPaciente}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="apellidoPaciente">
                    Apellido del Paciente
                  </label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="direccionPaciente"
                    name="direccionPaciente"
                    placeholder="Dirección del Paciente"
                    required={true}
                    value={direccionPaciente}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="direccionPaciente">
                    Dirección del Paciente
                  </label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="tel"
                    className="form-control"
                    id="telefonoPaciente"
                    name="telefonoPaciente"
                    placeholder="Teléfono del Paciente"
                    required={true}
                    value={telefonoPaciente}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="telefonoPaciente">
                    Teléfono del Paciente
                  </label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="email"
                    className="form-control"
                    id="emailPaciente"
                    name="emailPaciente"
                    placeholder="Email"
                    required={true}
                    value={emailPaciente}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="emailPaciente">Email</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <select
                    className="form-control"
                    id="idEps"
                    name="eps.idEps"
                    required={true}
                    value={idEps}
                    onChange={(e) => onInputChange(e)}>
                    {epsS.map((eps) => (
                      <option key={eps.idEps} value={eps.idEps}>
                        {eps.nombreEps}
                      </option>
                    ))}
                  </select>
                  <label htmlFor="idEps">Eps</label>
                </div>

                <button type="submit" className="btn btn-primary">
                  <i className="fa-regular fa-floppy-disk"></i> Guardar Cambios
                </button>
                <Link to="/pacientes">
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
