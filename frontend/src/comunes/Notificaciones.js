import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

// Función genérica para mostrar notificaciones
export const mostrarNotificacion = (mensaje, tipo = "info") => {
  toast(mensaje, {
    type: tipo, 
    position: "top-center",
    autoClose: 3000, 
    hideProgressBar: false,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true,
    progress: undefined,
  });
};

// Función para confirmar la eliminación con una notificación
export const confirmarEliminacion = (id, eliminarPersonal) => {
  toast.warn(
    ({ closeToast }) => (
      <div className="container">
        <h5>¿Está seguro de que desea eliminar este registro?</h5>
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
