import axios from "axios";
import React, { useEffect, useState, useCallback } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditarDoctor() {
  const urlBase = "http://localhost:8080/sipress-app/doctores";

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
    try {
      const resultado = await axios.get(
        "http://localhost:8080/sipress-app/dependencias"
      );
      setDependencias(resultado.data);
    } catch (error) {
      console.error("Error al cargar las dependencias:", error);
    }
  }, []);

  const cargarDoctor = useCallback(async () => {
    try {
      const resultado = await axios.get(`${urlBase}/${id}`);
      setDoctor(resultado.data);
    } catch (error) {
      console.error("Error al cargar el doctor:", error);
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
    if (name.startsWith("institucion")) {
      setDoctor({
        ...doctor,
        dependencia: {
          ...doctor.dependencia,
          institucion: {
            ...doctor.dependencia.institucion,
            [name.split(".")[1]]: value,
          },
        },
      });
    } else if (name.startsWith("dependencia")) {
      setDoctor({
        ...doctor,
        dependencia: {
          ...doctor.dependencia,
          [name.split(".")[1]]: value,
        },
      });
    } else {
      setDoctor({ ...doctor, [name]: value });
    }
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`${urlBase}/${id}`, doctor);
      navigate("/doctores");
    } catch (error) {
      console.error("Error al actualizar el doctor:", error);
    }
  };

  return (
    <div className="p-4" id="details">
      <div className="row justify-content-center">
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
                <Link to="/doctores">
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
