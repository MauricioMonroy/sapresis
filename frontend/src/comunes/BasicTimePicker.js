import * as React from "react";
import { TimePicker } from "@mui/x-date-pickers/TimePicker";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";

export default function BasicTimePicker({ value, onChange }) {
  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <TimePicker
        label="Hora de Consulta"
        value={value}
        onChange={onChange}
        renderInput={(params) => <input {...params} className="form-control" />}
      />
    </LocalizationProvider>
  );
}
