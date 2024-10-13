import * as React from "react";
import PropTypes from "prop-types";
import { TimePicker } from "@mui/x-date-pickers/TimePicker";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import dayjs from "dayjs";
import utc from "dayjs/plugin/utc";

dayjs.extend(utc);

/**
 * Componente para seleccionar la hora de una consulta.
 * @param {Object} props Las propiedades del componente.
 * @param {string} props.value La hora de la consulta.
 * @param {Function} props.onChange Función que se ejecuta cuando cambia la hora.
 * @returns El componente de selección de hora.
 */

export default function BasicTimePicker({ value, onChange }) {
  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <TimePicker
        label="Hora de Consulta"
        value={dayjs.utc(value)}
        onChange={(newValue) => onChange(dayjs.utc(newValue).format())}
        renderInput={(params) => <input {...params} className="form-control" />}
      />
    </LocalizationProvider>
  );
}

BasicTimePicker.propTypes = {
  value: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
};
