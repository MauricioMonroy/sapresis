import axios from "axios";
import React, { useEffect, useState, useCallback } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { toast } from "react-toastify";

export default function EditarEps() {
  const urlBase = "http://localhost:8080/sipress-app/epsS";

  let navigate = useNavigate();
  const { id } = useParams();

  const [eps, setEps] = useState({
    idEps: "",
    nombreEps: "",
    telefonoEps: "",
    emailEps: "",
  });

  const { idEps, nombreEps, telefonoEps, emailEps } = eps;

  const cargarEps = useCallback(async () => {
    const token = localStorage.getItem("token");
    try {
      const resultado = await axios.get(`${urlBase}/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setEps(resultado.data);
    } catch (error) {
      console.error("Error al cargar los registros de Eps:", error);
      toast.error("Error al cargar los datos del registro solicitado");
    }
  }, [id]);

  useEffect(() => {
    cargarEps();
  }, [id, cargarEps]);

  const onInputChange = (e) => {
    const { name, value } = e.target;
    setEps((prevEps) => ({
      ...prevEps,
      [name]: value,
    }));
  };

  const onSubmit = async (e) => {
    const token = localStorage.getItem("token");
    e.preventDefault();
    try {
      await axios.put(`${urlBase}/${id}`, eps, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      toast.success("Registro actualizado con éxito");
      navigate("/epsS");
    } catch (error) {
      console.error("Error al actualizar el registro:", error);
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
                    id="idEps"
                    name="idEps"
                    placeholder="ID Institución"
                    required={true}
                    value={idEps}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="idEps">ID de la EPS</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="nombreEps"
                    name="nombreEps"
                    placeholder="Nombre de la EPS"
                    required={true}
                    value={nombreEps}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="nombreEps">Nombre de la EPS</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="tel"
                    className="form-control"
                    id="telefonoEps"
                    name="telefonoEps"
                    placeholder="Teléfono de la EPS"
                    required={true}
                    value={telefonoEps}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="telefonoEps">Teléfono de la EPS</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="email"
                    className="form-control"
                    id="emailEps"
                    name="emailEps"
                    placeholder="Email"
                    required={true}
                    value={emailEps}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="emailEps">Email</label>
                </div>
                <button type="submit" className="btn btn-primary">
                  <i className="fa-regular fa-floppy-disk"></i> Guardar Cambios
                </button>
                <Link to="/epsS">
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
