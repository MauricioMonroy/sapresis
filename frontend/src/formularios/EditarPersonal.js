import React, { useEffect, useState, useCallback } from "react";
import axios from "axios";
import { Link, useNavigate, useParams } from "react-router-dom";
import { toast } from "react-toastify";

/**
 * Componente funcional que permite editar un personal
 * @returns El componente de formulario para editar un personal
 * @requires react, axios, react-toastify, useNavigate, useParams, Link, useState, useCallback, useEffect
 * @version 1.0
 * */

export default function EditarPersonal() {
  const urlBase = "https://sapresis-backend.onrender.com/sapresis/personalS";

  let navigate = useNavigate();
  const { id } = useParams();

  const [personal, setPersonal] = useState({
    idPersonal: "",
    nombrePersonal: "",
    apellidoPersonal: "",
    telefonoPersonal: "",
    emailPersonal: "",
    dependencia: {
      idDependencia: "",
      nombreDependencia: "",
      institucion: {
        idInstitucion: "",
        nombreInstitucion: "",
        direccionInstitucion: "",
        telefonoInstitucion: "",
        codigoPostal: "",
      },
    },
  });

  const {
    idPersonal,
    nombrePersonal,
    apellidoPersonal,
    telefonoPersonal,
    emailPersonal,
    dependencia: { idDependencia },
  } = personal;

  const [dependencias, setDependencias] = useState([]);

  const cargarDependencias = useCallback(async () => {
    const token = localStorage.getItem("token");
    try {
      const resultado = await axios.get(
        "https://sapresis-backend.onrender.com/sapresis/dependencias",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setDependencias(resultado.data);
    } catch (error) {
      console.error("Error al cargar los registros de Dependencia:", error);
      toast.error("Error al cargar los datos del registro solicitado");
    }
  }, []);

  const cargarPersonal = useCallback(async () => {
    const token = localStorage.getItem("token");
    try {
      const resultado = await axios.get(`${urlBase}/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setPersonal(resultado.data);
    } catch (error) {
      console.error("Error al cargar los registros de Personal:", error);
      toast.error("Error al cargar los datos del registro solicitado");
    }
  }, [id]);

  useEffect(() => {
    cargarDependencias();
  }, [cargarDependencias]);

  useEffect(() => {
    cargarPersonal();
  }, [cargarPersonal]);

  const onInputChange = (e) => {
    const { name, value } = e.target;

    if (name === "dependencia.idDependencia") {
      // Actualiza solo la dependencia
      setPersonal((prevPersonal) => ({
        ...prevPersonal,
        dependencia: {
          ...prevPersonal.dependencia,
          idDependencia: value,
        },
      }));
    } else if (name === "institucion.idInstitucion") {
      // Actualiza la institución dentro de la dependencia
      setPersonal((prevPersonal) => ({
        ...prevPersonal,
        dependencia: {
          ...prevPersonal.dependencia,
          institucion: {
            ...prevPersonal.dependencia.institucion,
            idInstitucion: value,
          },
        },
      }));
    } else {
      // Actualiza los demás campos directamente en el personal
      setPersonal((prevPersonal) => ({
        ...prevPersonal,
        [name]: value,
      }));
    }
  };

  const onSubmit = async (e) => {
    const token = localStorage.getItem("token");
    e.preventDefault();
    try {
      await axios.put(`${urlBase}/${id}`, personal, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      toast.success("Registro actualizado con éxito");
      navigate("/personalS");
    } catch (error) {
      console.error("Error al actualizar el registro:", error);
      toast.error("Error al actualizar el registro");
    }
  };

  return (
    <div className="p-3 mt-5 mb-2" id="details">
      <div className="row justify-content-center mt-5">
        <div className="col-lg-9">
          <div className="card">
            <div className="card-header">
              <h4>Modificar Registro</h4>
            </div>
            <form onSubmit={(e) => onSubmit(e)}>
              <div className="card-body">
                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="idPersonal"
                    name="idPersonal"
                    placeholder="ID Personal"
                    required={true}
                    value={idPersonal}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="idPersonal">ID del Personal</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="nombrePersonal"
                    name="nombrePersonal"
                    placeholder="Nombre del Personal"
                    required={true}
                    value={nombrePersonal}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="nombrePersonal">Nombre del Personal</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="apellidoPersonal"
                    name="apellidoPersonal"
                    placeholder="Apellido del Personal"
                    required={true}
                    value={apellidoPersonal}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="apellidoPersonal">
                    Apellido del Personal
                  </label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="tel"
                    className="form-control"
                    id="telefonoPersonal"
                    name="telefonoPersonal"
                    placeholder="Teléfono del Personal"
                    required={true}
                    value={telefonoPersonal}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="telefonoPersonal">
                    Teléfono del Personal
                  </label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="email"
                    className="form-control"
                    id="emailPersonal"
                    name="emailPersonal"
                    placeholder="Email"
                    required={true}
                    value={emailPersonal}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="emailPersonal">Email</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <select
                    className="form-control"
                    id="idDependencia"
                    name="dependencia.idDependencia"
                    required={true}
                    value={idDependencia}
                    onChange={(e) => onInputChange(e)}>
                    {dependencias.map((dependencia) => (
                      <option
                        key={dependencia.idDependencia}
                        value={dependencia.idDependencia}>
                        {dependencia.nombreDependencia}
                      </option>
                    ))}
                  </select>
                  <label htmlFor="idDependencia">Dependencia</label>
                </div>

                <button type="submit" className="btn btn-primary">
                  <i className="fa-regular fa-floppy-disk"></i> Guardar Cambios
                </button>
                <Link to="../personalS">
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
