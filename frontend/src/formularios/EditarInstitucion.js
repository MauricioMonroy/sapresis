import axios from "axios";
import React, { useEffect, useState, useCallback } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditarInstitucion() {
  const urlBase = "http://localhost:8080/sipress-app/instituciones";

  let navigate = useNavigate();
  const { id } = useParams();

  const [institucion, setInstitucion] = useState({
    idInstitucion: "",
    nombreInstitucion: "",
    direccionInstitucion: "",
    telefonoInstitucion: "",
    codigoPostal: "",
  });

  const {
    idInstitucion,
    nombreInstitucion,
    direccionInstitucion,
    telefonoInstitucion,
    codigoPostal,
  } = institucion;

  const cargarInstitucion = useCallback(async () => {
    const resultado = await axios.get(`${urlBase}/${id}`);
    setInstitucion(resultado.data);
  }, [id]);

  useEffect(() => {
    cargarInstitucion();
  }, [id, cargarInstitucion]); 

  const onInputChange = (e) => {
    setInstitucion({ ...institucion, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.put(`${urlBase}/${id}`, institucion);
    navigate("/instituciones");
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
                    id="idInstitucion"
                    name="idInstitucion"
                    placeholder="ID Institución"
                    required={true}
                    value={idInstitucion}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="idInstitucion">ID de la Institución</label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="nombreInstitucion"
                    name="nombreInstitucion"
                    placeholder="Nombre de la Institución"
                    required={true}
                    value={nombreInstitucion}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="nombreInstitucion">
                    Nombre de la Institución
                  </label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="direccionInstitucion"
                    name="direccionInstitucion"
                    placeholder="Dirección"
                    required={true}
                    value={direccionInstitucion}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="direccionInstitucion">
                    Dirección de la Institución
                  </label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="tel"
                    className="form-control"
                    id="telefonoInstitucion"
                    name="telefonoInstitucion"
                    placeholder="Teléfono de la Institución"
                    required={true}
                    value={telefonoInstitucion}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="telefonoInstitucion">
                    Teléfono de la Institución
                  </label>
                </div>

                <div className="form-floating form-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="codigoPostal"
                    name="codigoPostal"
                    placeholder="Código Postal"
                    required={true}
                    value={codigoPostal}
                    onChange={(e) => onInputChange(e)}
                  />
                  <label htmlFor="codigoPostal">Código Postal</label>
                </div>
                <button type="submit" className="btn btn-primary">
                  <i className="fa-regular fa-floppy-disk"></i> Guardar Cambios
                </button>
                <Link to="/instituciones">
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
