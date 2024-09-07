import React, { useState, useRef } from "react";
import axios from "axios";
import { toast } from "react-toastify";

/**
 * Componente funcional que renderiza el modal para agregar una EPS
 * @param {Object} props Las propiedades del componente
 * @param {Function} props.onEpsAdded Función que se ejecuta cuando se agrega una EPS
 * @returns El componente de formulario para agregar una EPS
 * @requires react, axios, react-toastify, useRef, useState
 * @version 1.0
 */

export default function AgregarEps({ onEpsAdded }) {
  const modalRef = useRef(null);

  const [eps, setEps] = useState({
    idEps: "",
    nombreEps: "",
    telefonoEps: "",
    emailEps: "",
  });

  const { idEps, nombreEps, telefonoEps, emailEps } = eps;

  const onInputChange = (e) => {
    setEps({ ...eps, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    const urlBase = "http://localhost:8080/sipress-app/epsS";
    const token = localStorage.getItem("token");
    await axios.post(urlBase, eps, {
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
    onEpsAdded();
    toast.success("Registro agregado correctamente");
  };

  return (
    <div
      className="modal fade"
      id="AgregarEpsModal"
      tabIndex="-1"
      aria-labelledby="AgregarEpsModalLabel"
      aria-hidden="true"
      ref={modalRef}>
      <div className="modal-dialog modal-lg">
        <div className="modal-content bg-light">
          <div className="modal-header">
            <h1 className="modal-title fs-5" id="AgregarEpsModalLabel">
              Registro de EPS
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
                  id="idEps"
                  name="idEps"
                  placeholder="ID EPS"
                  required={true}
                  value={idEps}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="idEps">ID de la EPS</label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="text"
                  className="form-control"
                  id="nombreEps"
                  name="nombreEps"
                  placeholder="Nombre de la EPS"
                  required={true}
                  value={nombreEps}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="nombreEps">Nombre de la EPS</label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="tel"
                  className="form-control"
                  id="telefonoEps"
                  name="telefonoEps"
                  placeholder="Teléfono de la EPS"
                  required={true}
                  value={telefonoEps}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="telefonoEps">Teléfono de la EPS</label>
              </div>

              <div className="form-floating form-group mb-3">
                <input
                  type="email"
                  className="form-control"
                  id="emailEps"
                  name="emailEps"
                  placeholder="Email"
                  required={true}
                  value={emailEps}
                  onChange={(e) => onInputChange(e)}
                />
                <label htmlFor="emailEps">Email</label>
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
