import React, { useEffect, useState, useCallback } from "react";
import axios from "axios";
import { Link, useNavigate, useParams } from "react-router-dom";
import { toast } from "react-toastify";

/**
 * Componente funcional que permite editar una factura
 * @returns El componente de formulario para editar una factura
 * @requires react, axios, react-toastify, useNavigate, useParams, Link, useState, useCallback, useEffect
 * @version 1.0
 * */

export default function EditarFactura() {
  const urlBase = "https://sipress-backend.onrender.com/sipress-app/facturas";

  let navigate = useNavigate();
  const { id } = useParams();

  const [factura, setFactura] = useState({
    numeroFactura: "",
    descripcionServicio: "",
    valor: "",
    total: "",
    paciente: {
      idPaciente: "",
      nombrePaciente: "",
      apellidoPaciente: "",
      direccionPaciente: "",
      telefonoPaciente: "",
      emailPaciente: "",
      eps: {
        idEps: "",
        nombreEps: "",
        telefonoEps: "",
        emailEps: "",
      },
    },
  });

  const {
    numeroFactura,
    descripcionServicio,
    valor,
    total,
    paciente: { idPaciente },
  } = factura;

  const [pacientes, setPacientes] = useState([]);

  const cargarPacientes = useCallback(async () => {
    const token = localStorage.getItem("token");
    try {
      const resultado = await axios.get(
        "https://sipress-backend.onrender.com/sipress-app/pacientes",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setPacientes(resultado.data);
    } catch (error) {
      console.error("Error al cargar los registros de Paciente:", error);
      toast.error("Error al cargar los datos del registro solicitado");
    }
  }, []);

  const cargarFactura = useCallback(async () => {
    const token = localStorage.getItem("token");
    try {
      const resultado = await axios.get(`${urlBase}/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setFactura(resultado.data);
    } catch (error) {
      console.error("Error al cargar los registros de Factura:", error);
      toast.error("Error al cargar los datos del registro solicitado");
    }
  }, [id]);

  useEffect(() => {
    cargarPacientes();
  }, [cargarPacientes]);

  useEffect(() => {
    cargarFactura();
  }, [cargarFactura]);

  const onInputChange = (e) => {
    const { name, value } = e.target;
    if (name.startsWith("eps")) {
      setFactura({
        ...factura,
        paciente: {
          ...factura.paciente,
          eps: {
            ...factura.paciente.eps,
            [name.split(".")[1]]: value,
          },
        },
      });
    } else if (name.startsWith("paciente")) {
      setFactura({
        ...factura,
        paciente: {
          ...factura.paciente,
          [name.split(".")[1]]: value,
        },
      });
    } else {
      setFactura({ ...factura, [name]: value });
    }
  };

  const onSubmit = async (e) => {
    const token = localStorage.getItem("token");
    e.preventDefault();
    try {
      await axios.put(`${urlBase}/${id}`, factura, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      toast.success("Registro actualizado con éxito");
      navigate("/facturas");
    } catch (error) {
      console.error("Error al actualizar la factura:", error);
      toast.error("Error al actualizar el registro");
    }
  };

  return (
    <div className="p-4 mb-2 mt-5">
      <div className="row justify-content-center">
        <div className="col-lg-9">
          <div className="card mt-3" id="details">
            <div className="card-header">
              <h4>Modificar Registro</h4>
            </div>
            <form onSubmit={(e) => onSubmit(e)}>
              <div className="card-body">
                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="numeroFactura"
                    name="numeroFactura"
                    placeholder="Número de Factura Médica"
                    required={true}
                    value={numeroFactura}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="numeroFactura">
                    Número de Factura Médica
                  </label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="descripcionServicio"
                    name="descripcionServicio"
                    placeholder="Descripción del servicio"
                    required={true}
                    value={descripcionServicio}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="descripcionServicio">
                    Descripción del servicio
                  </label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="number"
                    step="any"
                    className="form-control"
                    id="valor"
                    name="valor"
                    placeholder="Valor del servicio"
                    required={true}
                    value={valor}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="valor">Valor del servicio</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="number"
                    step="any"
                    className="form-control"
                    id="total"
                    name="total"
                    placeholder="Costo total"
                    required={true}
                    value={total}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="total">Costo total</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <select
                    className="form-control"
                    id="idPaciente"
                    name="paciente.idPaciente"
                    required={true}
                    value={idPaciente}
                    onChange={(e) => onInputChange(e)}>
                    {pacientes.map((paciente) => (
                      <option
                        key={paciente.idPaciente}
                        value={paciente.idPaciente}>
                        {paciente.nombrePaciente} {paciente.apellidoPaciente}
                      </option>
                    ))}
                  </select>
                  <label htmlFor="idPaciente">Elegir paciente</label>
                </div>

                <button type="submit" className="btn btn-primary">
                  <i className="fa-regular fa-floppy-disk"></i> Guardar Cambios
                </button>
                <Link to="../facturas">
                  <i className="fa-solid fa-triangle-exclamation"></i> Cancelar
                </Link>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
