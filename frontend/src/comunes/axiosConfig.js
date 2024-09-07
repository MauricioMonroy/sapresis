import axios from "axios";
import { mostrarNotificacion } from "./Notificaciones";

/**
 * Configuración de axios para la intercepción de errores.
 */

axios.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 403) {
      mostrarNotificacion();
    }
    return Promise.reject(error);
  }
);

export default axios;
