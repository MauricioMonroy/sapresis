import axios from "axios";
import React, { useState, useEffect, useRef } from "react";

export default function AgregarDependencia({ onDependenciaAdded }) {
  const modalRef = useRef(null);

  const [dependencia, setDependencia] = useState({
    idDependencia: "",
    nombreDependencia: "",
    institucion: { idInstitucion: "" }, // Cambiar a objeto Institucion
  });

  const { idDependencia, nombreDependencia, institucion } = dependencia;

  const [instituciones, setInstituciones] = useState([]);

  useEffect(() => {
    // Cargar las instituciones al montar el componente
    const cargarInstituciones = async () => {
      const token = localStorage.getItem("token");
      const resultado = await axios.get(
        "http://localhost:8080/sipress-app/instituciones",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setInstituciones(resultado.data);
    };

    cargarInstituciones();
  }, []);

  const onInputChange = (e) => {
    const { name, value } = e.target;
    if (name === "idInstitucion") {
      setDependencia((prevDependencia) => ({
        ...prevDependencia,
        institucion: { idInstitucion: value }, // Actualizar el objeto Institucion
      }));
    } else {
      setDependencia({ ...dependencia, [name]: value });
    }
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    const urlBase = "http://localhost:8080/sipress-app/dependencias";
    const token = localStorage.getItem("token");
    await axios.post(urlBase, dependencia, {
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
    onDependenciaAdded();
  };

  return (
    <div
      className="modal fade"
      id="AgregarDependenciaModal"
      tabIndex="-1"
      aria-labelledby="AgregarDependenciaModalLabel"
      aria-hidden="true"
      ref={modalRef}>
      <div className="modal-dialog modal-lg">
        <div className="modal-content bg-light">
          <div className="modal-header">
            <h1 className="modal-title fs-5" id="AgregarDependenciaModalLabel">
              Registro de Dependencias
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
                  id="idDependencia"
                  name="idDependencia"
                  placeholder="ID Dependencia"
                  required={true}
                  value={idDependencia}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="idDependencia">ID del Dependencia</label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="text"
                  className="form-control"
                  id="nombreDependencia"
                  name="nombreDependencia"
                  placeholder="Nombre del Dependencia"
                  required={true}
                  value={nombreDependencia}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="nombreDependencia">
                  Nombre del Dependencia
                </label>
              </div>

              <div className="form-floating form-group mb-3">
                <select
                  className="form-control"
                  id="idInstitucion"
                  name="idInstitucion"
                  required={true}
                  value={institucion.idInstitucion} // Usar el ID de la institucion
                  onChange={(e) => onInputChange(e)}>
                  <option value="">Seleccione una Institucion</option>
                  {instituciones.map((institucion) => (
                    <option
                      key={institucion.idInstitucion}
                      value={institucion.idInstitucion}>
                      {institucion.nombreInstitucion}
                    </option>
                  ))}
                </select>
                <label htmlFor="idInstitucion">Institucion</label>
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
