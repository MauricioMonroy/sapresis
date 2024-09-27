import React, { useEffect, useState, useCallback } from "react";
import axios from "axios";
import { Link, useNavigate, useParams } from "react-router-dom";
import { toast } from "react-toastify";

/**
 * Componente funcional que permite editar un doctor
 * @returns El componente de formulario para editar un doctor
 * @requires react, axios, react-toastify, useNavigate, useParams, Link, useState, useCallback, useEffect
 * @version 1.0
 * */

export default function EditarDoctor() {
  const urlBase = process.env.REACT_APP_API_URL + "/sapresis/doctores";

  let navigate = useNavigate();
  const { id } = useParams();

  const [doctor, setDoctor] = useState({
    idDoctor: "",
    nombreDoctor: "",
    apellidoDoctor: "",
    telefonoDoctor: "",
    emailDoctor: "",
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
    idDoctor,
    nombreDoctor,
    apellidoDoctor,
    telefonoDoctor,
    emailDoctor,
    dependencia: { idDependencia },
  } = doctor;

  const [dependencias, setDependencias] = useState([]);

  const cargarDependencias = useCallback(async () => {
    const token = localStorage.getItem("token");
    try {
      const resultado = await axios.get(
        process.env.REACT_APP_API_URL + "/sapresis/dependencias",
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

  const cargarDoctor = useCallback(async () => {
    const token = localStorage.getItem("token");
    try {
      const resultado = await axios.get(`${urlBase}/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setDoctor(resultado.data);
    } catch (error) {
      console.error("Error al cargar los registros de Doctor:", error);
      toast.error("Error al cargar los datos del registro solicitado");
    }
  }, [id]);

  useEffect(() => {
    cargarDependencias();
  }, [cargarDependencias]);

  useEffect(() => {
    cargarDoctor();
  }, [cargarDoctor]);

  const onInputChange = (e) => {
    const { name, value } = e.target;

    if (name === "dependencia.idDependencia") {
      // Actualiza solo la dependencia
      setDoctor((prevDoctor) => ({
        ...prevDoctor,
        dependencia: {
          ...prevDoctor.dependencia,
          idDependencia: value,
        },
      }));
    } else if (name === "institucion.idInstitucion") {
      // Actualiza la institución dentro de la dependencia
      setDoctor((prevDoctor) => ({
        ...prevDoctor,
        dependencia: {
          ...prevDoctor.dependencia,
          institucion: {
            ...prevDoctor.dependencia.institucion,
            idInstitucion: value,
          },
        },
      }));
    } else {
      // Actualiza los demás campos directamente en el doctor
      setDoctor((prevDoctor) => ({
        ...prevDoctor,
        [name]: value,
      }));
    }
  };

  const onSubmit = async (e) => {
    const token = localStorage.getItem("token");
    e.preventDefault();
    try {
      await axios.put(`${urlBase}/${id}`, doctor, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      toast.success("Registro actualizado con éxito");
      navigate("/doctores");
    } catch (error) {
      console.error("Error al actualizar el doctor:", error);
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
                    id="idDoctor"
                    name="idDoctor"
                    placeholder="ID Doctor"
                    required={true}
                    value={idDoctor}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="idDoctor">ID del Doctor</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="nombreDoctor"
                    name="nombreDoctor"
                    placeholder="Nombre del Doctor"
                    required={true}
                    value={nombreDoctor}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="nombreDoctor">Nombre del Doctor</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="apellidoDoctor"
                    name="apellidoDoctor"
                    placeholder="Apellido del Doctor"
                    required={true}
                    value={apellidoDoctor}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="apellidoDoctor">Apellido del Doctor</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="tel"
                    className="form-control"
                    id="telefonoDoctor"
                    name="telefonoDoctor"
                    placeholder="Teléfono del Doctor"
                    required={true}
                    value={telefonoDoctor}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="telefonoDoctor">Teléfono del Doctor</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="email"
                    className="form-control"
                    id="emailDoctor"
                    name="emailDoctor"
                    placeholder="Email"
                    required={true}
                    value={emailDoctor}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="emailDoctor">Email</label>
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
                <Link to="../doctores">
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
