import React, { useState, useEffect, useRef } from "react";
import PropTypes from "prop-types";
import axios from "axios";
import { toast } from "react-toastify";

/**
 * Componente funcional que renderiza el modal para agregar una dependencia
 * @param {Object} props Las propiedades del componente
 * @param {Function} props.onDependenciaAdded Función que se ejecuta cuando se agrega una dependencia
 * @returns El componente de formulario para agregar una dependencia
 * @requires react, axios, react-toastify, useRef, useState, useEffect
 * @version 1.0
 * */

export default function AgregarDependencia({ onDependenciaAdded }) {
  const modalRef = useRef(null);
  const [error, setError] = useState(null);

  const [dependencia, setDependencia] = useState({
    idDependencia: "",
    nombreDependencia: "",
    institucion: { idInstitucion: "" },
  });

  const { idDependencia, nombreDependencia, institucion } = dependencia;

  const [instituciones, setInstituciones] = useState([]);

  useEffect(() => {
    const cargarInstituciones = async () => {
      try {
        const token = localStorage.getItem("token");
        const resultado = await axios.get(
          process.env.REACT_APP_API_URL + "/sapresis/instituciones",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        setInstituciones(resultado.data);
        setError(null);
      } catch (error) {
        setError("Error al cargar los registros de Institucion");
        console.error("Error al cargar registros", error);
      }
    };

    cargarInstituciones();
  }, []);

  const onInputChange = (e) => {
    const { name, value } = e.target;
    if (name === "idInstitucion") {
      setDependencia((prevDependencia) => ({
        ...prevDependencia,
        institucion: { idInstitucion: value },
      }));
    } else {
      setDependencia({ ...dependencia, [name]: value });
    }
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    const urlBase = process.env.REACT_APP_API_URL + "/sapresis/dependencias";
    const token = localStorage.getItem("token");
    try {
      const response = await axios.post(urlBase, dependencia, {
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
        onDependenciaAdded();
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
              {error && <p className="fs-5">{error}</p>}
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
AgregarDependencia.propTypes = {
  onDependenciaAdded: PropTypes.func,
};
