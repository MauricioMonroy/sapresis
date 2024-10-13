import React, { useState } from "react";
import PropTypes from "prop-types";
import { Calendar } from "primereact/calendar";
import { addLocale } from "primereact/api";

/**
 * Componente Calendario
 * @param {Object} props Las propiedades del componente.
 * @param {Function} props.onDateClick Función que se ejecuta cuando se selecciona una fecha.
 * @param {Function} props.addLocale Función que agrega un idioma al calendario.
 * @param {Function} props.handleDateChange Función que se ejecuta cuando cambia la fecha.
 * @returns {JSX.Element} El componente Calendario.
 * @requires react primereact/api primereact/calendar
 * @version 1.0
 */

const Calendario = ({ onDateClick }) => {
  const [date, setDate] = useState(null);

  addLocale("es", {
    firstDayOfWeek: 1,
    dayNames: [
      "domingo",
      "lunes",
      "martes",
      "miércoles",
      "jueves",
      "viernes",
      "sábado",
    ],
    dayNamesShort: ["dom", "lun", "mar", "mié", "jue", "vie", "sáb"],
    dayNamesMin: ["D", "L", "M", "X", "J", "V", "S"],
    monthNames: [
      "enero",
      "febrero",
      "marzo",
      "abril",
      "mayo",
      "junio",
      "julio",
      "agosto",
      "septiembre",
      "octubre",
      "noviembre",
      "diciembre",
    ],
    monthNamesShort: [
      "ene",
      "feb",
      "mar",
      "abr",
      "may",
      "jun",
      "jul",
      "ago",
      "sep",
      "oct",
      "nov",
      "dic",
    ],
    today: "Hoy",
    clear: "Limpiar",
  });

  const handleDateChange = (e) => {
    setDate(e.value);
    onDateClick(e.value);
  };

  return (
    <Calendar inline value={date} onChange={handleDateChange} locale="es" />
  );
};
Calendario.propTypes = {
  onDateClick: PropTypes.func.isRequired,
};

export default Calendario;
