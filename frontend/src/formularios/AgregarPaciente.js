import axios from "axios";
import React, { useState, useEffect, useRef } from "react";

export default function AgregarPaciente({ onPacienteAdded }) {
  const modalRef = useRef(null);

  const [paciente, setPaciente] = useState({
    idPaciente: "",
    nombrePaciente: "",
    apellidoPaciente: "",
    direccionPaciente: "",
    telefonoPaciente: "",
    emailPaciente: "",
    eps: { idEps: "" }, // Cambiar a objeto Eps
  });

  const {
    idPaciente,
    nombrePaciente,
    apellidoPaciente,
    direccionPaciente,
    telefonoPaciente,
    emailPaciente,
    eps,
  } = paciente;

  const [epsS, setEpsS] = useState([]);

  useEffect(() => {
    // Cargar las epsS al montar el componente
    const cargarEpsS = async () => {
      const token = localStorage.getItem("token");
      const resultado = await axios.get(
        "http://localhost:8080/sipress-app/epsS",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setEpsS(resultado.data);
    };

    cargarEpsS();
  }, []);

  const onInputChange = (e) => {
    const { name, value } = e.target;
    if (name === "idEps") {
      setPaciente((prevPaciente) => ({
        ...prevPaciente,
        eps: { idEps: value }, // Actualizar el objeto Eps
      }));
    } else {
      setPaciente({ ...paciente, [name]: value });
    }
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    const urlBase = "http://localhost:8080/sipress-app/pacientes";
    const token = localStorage.getItem("token");
    await axios.post(
      urlBase,
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      },
      paciente
    );

    // Cerrar el modal manualmente
    const modalElement = modalRef.current;
    if (modalElement) {
      const modalInstance = new window.bootstrap.Modal(modalElement);
      modalInstance.hide();
    }

    // Llamar a la función de actualización de la lista
    onPacienteAdded();
  };

  return (
    <div
      className="modal fade"
      id="AgregarPacienteModal"
      tabIndex="-1"
      aria-labelledby="AgregarPacienteModalLabel"
      aria-hidden="true"
      ref={modalRef}>
      <div className="modal-dialog modal-lg">
        <div className="modal-content bg-light">
          <div className="modal-header">
            <h1 className="modal-title fs-5" id="AgregarPacienteModalLabel">
              Registro de Pacientes
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
                <label htmlFor="apellidoPaciente">Apellido del Paciente</label>
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
                <label htmlFor="telefonoPaciente">Teléfono del Paciente</label>
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
                  name="idEps"
                  required={true}
                  value={eps.idEps} // Usar el ID de la eps
                  onChange={(e) => onInputChange(e)}>
                  <option value="">Seleccione una Eps</option>
                  {epsS.map((eps) => (
                    <option key={eps.idEps} value={eps.idEps}>
                      {eps.nombreEps}
                    </option>
                  ))}
                </select>
                <label htmlFor="idEps">Eps</label>
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
