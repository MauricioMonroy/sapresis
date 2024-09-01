import axios from "axios";
import React, { useEffect, useState, useCallback } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditarDependencia() {
  const urlBase = "http://localhost:8080/sipress-app/dependencias";

  let navigate = useNavigate();
  const { id } = useParams();

  const [dependencia, setDependencia] = useState({
    idDependencia: "",
    nombreDependencia: "",
    institucion: {
      idInstitucion: "",
      nombreInstitucion: "",
      direccionInstitucion: "",
      telefonoInstitucion: "",
      codigoPostal: "",
    },
  });

  const {
    idDependencia,
    nombreDependencia,
    institucion: { idInstitucion },
  } = dependencia;

  const [instituciones, setInstituciones] = useState([]);

  const cargarInstituciones = useCallback(async () => {
    const token = localStorage.getItem("token");
    try {
      const resultado = await axios.get(
        "http://localhost:8080/sipress-app/instituciones",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setInstituciones(resultado.data);
    } catch (error) {
      console.error("Error al cargar las institucion:", error);
    }
  }, []);

  const cargarDependencia = useCallback(async () => {
    const token = localStorage.getItem("token");
    try {
      const resultado = await axios.get(`${urlBase}/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setDependencia(resultado.data);
    } catch (error) {
      console.error("Error al cargar el dependencia:", error);
    }
  }, [id]);

  useEffect(() => {
    cargarInstituciones();
  }, [cargarInstituciones]);

  useEffect(() => {
    cargarDependencia();
  }, [cargarDependencia]);

  const onInputChange = (e) => {
    const { name, value } = e.target;
  
    // Manejo del campo relacionado con Institución
    if (name === "institucion.idInstitucion") {
      setDependencia((prevDependencia) => ({
        ...prevDependencia,
        institucion: {
          ...prevDependencia.institucion,
          idInstitucion: value,
        },
      }));
    } else {
      // Manejo de otros campos de Dependencia
      setDependencia((prevDependencia) => ({
        ...prevDependencia,
        [name]: value,
      }));
    }
  };  

  const onSubmit = async (e) => {
    const token = localStorage.getItem("token");
    e.preventDefault();
    try {
      await axios.put(`${urlBase}/${id}`, dependencia, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      navigate("/dependencias");
    } catch (error) {
      console.error("Error al actualizar el dependencia:", error);
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
                    id="idDependencia"
                    name="idDependencia"
                    placeholder="ID Dependencia"
                    required={true}
                    value={idDependencia}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="idDependencia">ID del Dependencia</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="nombreDependencia"
                    name="nombreDependencia"
                    placeholder="Nombre del Dependencia"
                    required={true}
                    value={nombreDependencia}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="nombreDependencia">
                    Nombre del Dependencia
                  </label>
                </div>

                <div className="form-floating form-group mb-3">
                  <select
                    className="form-control"
                    id="idInstitucion"
                    name="institucion.idInstitucion"
                    required={true}
                    value={idInstitucion}
                    onChange={(e) => onInputChange(e)}>
                    {instituciones.map((institucion) => (
                      <option
                        key={institucion.idInstitucion}
                        value={institucion.idInstitucion}>
                        {institucion.nombreInstitucion}
                      </option>
                    ))}
                  </select>
                  <label htmlFor="idInstitucion">Institucion</label>
                </div>

                <button type="submit" className="btn btn-primary">
                  <i className="fa-regular fa-floppy-disk"></i> Guardar Cambios
                </button>
                <Link to="/dependencias">
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
