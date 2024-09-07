import axios from "axios";
import React, { useState, useEffect, useRef } from "react";
import { toast } from "react-toastify";

export default function AgregarPersonal({ onPersonalAdded }) {
  const modalRef = useRef(null);

  const [personal, setPersonal] = useState({
    idPersonal: "",
    nombrePersonal: "",
    apellidoPersonal: "",
    telefonoPersonal: "",
    emailPersonal: "",
    dependencia: { idDependencia: "" },
  });

  const {
    idPersonal,
    nombrePersonal,
    apellidoPersonal,
    telefonoPersonal,
    emailPersonal,
    dependencia,
  } = personal;

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
      setPersonal((prevPersonal) => ({
        ...prevPersonal,
        dependencia: { idDependencia: value },
      }));
    } else {
      setPersonal({ ...personal, [name]: value });
    }
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    const urlBase = "http://localhost:8080/sipress-app/personalS";
    const token = localStorage.getItem("token");
    await axios.post(urlBase, personal, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    // Cerrar el modal manualmente
    const modalElement = modalRef.current;
    if (modalElement) {
      const modalInstance = new window.bootstrap.Modal(modalElement);
      modalInstance.hide();
    }

    // Llamar a la función de actualización de la lista
    onPersonalAdded();
    toast.success("Registro agregado correctamente");
  };

  return (
    <div
      className="modal fade"
      id="AgregarPersonalModal"
      tabIndex="-1"
      aria-labelledby="AgregarPersonalModalLabel"
      aria-hidden="true"
      ref={modalRef}>
      <div className="modal-dialog modal-lg">
        <div className="modal-content bg-light">
          <div className="modal-header">
            <h1 className="modal-title fs-5" id="AgregarPersonalModalLabel">
              Registro de Personal
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
                  id="idPersonal"
                  name="idPersonal"
                  placeholder="ID Personal"
                  required={true}
                  value={idPersonal}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="idPersonal">ID del Personal</label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="text"
                  className="form-control"
                  id="nombrePersonal"
                  name="nombrePersonal"
                  placeholder="Nombre del Personal"
                  required={true}
                  value={nombrePersonal}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="nombrePersonal">Nombre del Personal</label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="text"
                  className="form-control"
                  id="apellidoPersonal"
                  name="apellidoPersonal"
                  placeholder="Apellido del Personal"
                  required={true}
                  value={apellidoPersonal}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="apellidoPersonal">Apellido del Personal</label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="tel"
                  className="form-control"
                  id="telefonoPersonal"
                  name="telefonoPersonal"
                  placeholder="Teléfono del Personal"
                  required={true}
                  value={telefonoPersonal}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="telefonoPersonal">Teléfono del Personal</label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="email"
                  className="form-control"
                  id="emailPersonal"
                  name="emailPersonal"
                  placeholder="Email"
                  required={true}
                  value={emailPersonal}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="emailPersonal">Email</label>
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
