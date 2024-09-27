import React, { useState, useRef } from "react";
import axios from "axios";
import { toast } from "react-toastify";

/**
 * Componente funcional que renderiza el modal para agregar una institución
 * @param {Object} props Las propiedades del componente
 * @param {Function} props.onInstitucionAdded Función que se ejecuta cuando se agrega una institución
 * @returns El componente de formulario para agregar una institución
 * @requires react, axios, react-toastify, useRef, useState
 * @version 1.0
 * */

export default function AgregarInstitucion({ onInstitucionAdded }) {
  const modalRef = useRef(null);

  const [institucion, setInstitucion] = useState({
    idInstitucion: "",
    nombreInstitucion: "",
    direccionInstitucion: "",
    telefonoInstitucion: "",
    codigoPostal: "",
  });

  const {
    idInstitucion,
    nombreInstitucion,
    direccionInstitucion,
    telefonoInstitucion,
    codigoPostal,
  } = institucion;

  const onInputChange = (e) => {
    setInstitucion({ ...institucion, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    const urlBase = "https://sapresis-backend.onrender.com/sapresis/instituciones";
    const token = localStorage.getItem("token");
    await axios.post(urlBase, institucion, {
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
    onInstitucionAdded();
    toast.success("Registro agregado correctamente");
  };

  return (
    <div
      className="modal fade"
      id="AgregarInstitucionModal"
      tabIndex="-1"
      aria-labelledby="AgregarInstitucionModalLabel"
      aria-hidden="true"
      ref={modalRef}>
      <div className="modal-dialog modal-lg">
        <div className="modal-content bg-light">
          <div className="modal-header">
            <h1 className="modal-title fs-5" id="AgregarInstitucionModalLabel">
              Registro de Instituciones
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
                  id="idInstitucion"
                  name="idInstitucion"
                  placeholder="ID Institución"
                  required={true}
                  value={idInstitucion}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="idInstitucion">ID de la Institución</label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="text"
                  className="form-control"
                  id="nombreInstitucion"
                  name="nombreInstitucion"
                  placeholder="Nombre de la Institución"
                  required={true}
                  value={nombreInstitucion}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="nombreInstitucion">
                  Nombre de la Institución
                </label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="text"
                  className="form-control"
                  id="direccionInstitucion"
                  name="direccionInstitucion"
                  placeholder="Dirección"
                  required={true}
                  value={direccionInstitucion}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="direccionInstitucion">
                  Dirección de la Institución
                </label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="tel"
                  className="form-control"
                  id="telefonoInstitucion"
                  name="telefonoInstitucion"
                  placeholder="Teléfono de la Institución"
                  required={true}
                  value={telefonoInstitucion}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="telefonoInstitucion">
                  Teléfono de la Institución
                </label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="text"
                  className="form-control"
                  id="codigoPostal"
                  name="codigoPostal"
                  placeholder="Código Postal"
                  required={true}
                  value={codigoPostal}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="codigoPostal">Código Postal</label>
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
