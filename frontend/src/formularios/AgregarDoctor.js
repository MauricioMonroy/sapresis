import axios from "axios";
import React, { useState, useEffect, useRef } from "react";

export default function AgregarDoctor({ onDoctorAdded }) {
  const modalRef = useRef(null);

  const [doctor, setDoctor] = useState({
    idDoctor: "",
    nombreDoctor: "",
    apellidoDoctor: "",
    telefonoDoctor: "",
    emailDoctor: "",
    dependencia: { idDependencia: "" }, // Cambiar a objeto Dependencia
  });

  const {
    idDoctor,
    nombreDoctor,
    apellidoDoctor,
    telefonoDoctor,
    emailDoctor,
    dependencia,
  } = doctor;

  const [dependencias, setDependencias] = useState([]);

  useEffect(() => {
    // Cargar las dependencias al montar el componente
    const cargarDependencias = async () => {
      const token = localStorage.getItem("token");
      const resultado = await axios.get(
        "http://localhost:8080/sipress-app/dependencias",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setDependencias(resultado.data);
    };

    cargarDependencias();
  }, []);

  const onInputChange = (e) => {
    const { name, value } = e.target;
    if (name === "idDependencia") {
      setDoctor((prevDoctor) => ({
        ...prevDoctor,
        dependencia: { idDependencia: value }, // Actualizar el objeto Dependencia
      }));
    } else {
      setDoctor({ ...doctor, [name]: value });
    }
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    const urlBase = "http://localhost:8080/sipress-app/doctores";
    const token = localStorage.getItem("token");
    await axios.post(
      urlBase,
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      },
      doctor
    );

    // Cerrar el modal manualmente
    const modalElement = modalRef.current;
    if (modalElement) {
      const modalInstance = new window.bootstrap.Modal(modalElement);
      modalInstance.hide();
    }

    // Llamar a la función de actualización de la lista
    onDoctorAdded();
  };

  return (
    <div
      className="modal fade"
      id="AgregarDoctorModal"
      tabIndex="-1"
      aria-labelledby="AgregarDoctorModalLabel"
      aria-hidden="true"
      ref={modalRef}>
      <div className="modal-dialog modal-lg">
        <div className="modal-content bg-light">
          <div className="modal-header">
            <h1 className="modal-title fs-5" id="AgregarDoctorModalLabel">
              Registro de Doctores
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
                  id="idDoctor"
                  name="idDoctor"
                  placeholder="ID Doctor"
                  required={true}
                  value={idDoctor}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="idDoctor">ID del Doctor</label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="text"
                  className="form-control"
                  id="nombreDoctor"
                  name="nombreDoctor"
                  placeholder="Nombre del Doctor"
                  required={true}
                  value={nombreDoctor}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="nombreDoctor">Nombre del Doctor</label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="text"
                  className="form-control"
                  id="apellidoDoctor"
                  name="apellidoDoctor"
                  placeholder="Apellido del Doctor"
                  required={true}
                  value={apellidoDoctor}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="apellidoDoctor">Apellido del Doctor</label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="tel"
                  className="form-control"
                  id="telefonoDoctor"
                  name="telefonoDoctor"
                  placeholder="Teléfono del Doctor"
                  required={true}
                  value={telefonoDoctor}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="telefonoDoctor">Teléfono del Doctor</label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="email"
                  className="form-control"
                  id="emailDoctor"
                  name="emailDoctor"
                  placeholder="Email"
                  required={true}
                  value={emailDoctor}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="emailDoctor">Email</label>
              </div>

              <div className="form-floating form-group mb-3">
                <select
                  className="form-control"
                  id="idDependencia"
                  name="idDependencia"
                  required={true}
                  value={dependencia.idDependencia} // Usar el ID de la dependencia
                  onChange={(e) => onInputChange(e)}>
                  <option value="">Seleccione una Dependencia</option>
                  {dependencias.map((dependencia) => (
                    <option
                      key={dependencia.idDependencia}
                      value={dependencia.idDependencia}>
                      {dependencia.nombreDependencia}
                    </option>
                  ))}
                </select>
                <label htmlFor="idDependencia">Dependencia</label>
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
