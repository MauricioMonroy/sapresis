import React from "react";
import { Link } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

/**
 * Funciones para mostrar notificaciones en la aplicación.
 * @module Notificaciones
 * @requires react-toastify
 * @version 1.0
 */

/**
 * Función para mostrar una notificación de sesión expirada.
 * Se muestra una sola vez.
 * @returns {void}
 */

let notificacionMostrada = false;
export const mostrarNotificacion = () => {
  if (!notificacionMostrada) {
    notificacionMostrada = true;
    toast.warn(
      ({ closeToast }) => (
        <div>
          <p className="text-danger h3">Su sesión ha expirado.</p>
          <div className="mt-4">
            <Link
              to="/login"
              className="btn btn-success fs-5"
              onClick={() => {
                closeToast();
                notificacionMostrada = false;
              }}>
              Ingresar nuevamente
            </Link>
          </div>
        </div>
      ),
      {
        position: "top-center",
        autoClose: false,
        closeOnClick: false,
      }
    );
  }
};

/**
 * Función para mostrar una notificación de confirmación de eliminación
 * de un registro.
 * @param {number} id Identificador del registro a eliminar
 */
export const confirmarEliminacion = (id, eliminarPersonal) => {
  toast.warn(
    ({ closeToast }) => (
      <div className="container">
        <h5>¿Está seguro de eliminar este registro?</h5>
        <div>
          <button
            className="btn btn-outline-danger me-4"
            onClick={() => {
              eliminarPersonal(id);
              closeToast();
            }}>
            Sí
          </button>
          <button className="btn btn-outline-success" onClick={closeToast}>
            No
          </button>
        </div>
      </div>
    ),
    {
      position: "top-center",
      autoClose: false,
      closeOnClick: false,
    }
  );
};

// Componente de contenedor para las notificaciones
export function Notificaciones() {
  return <ToastContainer />;
}
