import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Navbar from "./comunes/Navbar";
import Footer from "./comunes/Footer";
import "./styles.css";
import ListadoPacientes from "./registros/ListadoPacientes";
import ListadoDoctores from "./registros/ListadoDoctores";
import ListadoPersonal from "./registros/ListadoPersonal";
import ListadoInstituciones from "./registros/ListadoInstituciones";
import ListadoDependencias from "./registros/ListadoDependencias";
import ListadoConsultorios from "./registros/ListadoConsultorios";
import ListadoFormulas from "./registros/ListadoFormulas";
import ListadoFacturas from "./registros/ListadoFacturas";
import ListadoConsultas from "./registros/ListadoConsultas";
import EditarInstitucion from "./formularios/EditarInstitucion";

function App() {
  return (
    <Router>
      <div className="App">
        <Navbar />
        <div className="container">
          <Routes>
            <Route path="/" element={<Inicio />} />
            <Route path="/pacientes" element={<ListadoPacientes />} />
            <Route path="/doctores" element={<ListadoDoctores />} />
            <Route path="/personalS" element={<ListadoPersonal />} />
            <Route path="/instituciones" element={<ListadoInstituciones />} />
            <Route path="/dependencias" element={<ListadoDependencias />} />
            <Route path="/consultorios" element={<ListadoConsultorios />} />
            <Route path="/formulas" element={<ListadoFormulas />} />
            <Route path="/facturas" element={<ListadoFacturas />} />
            <Route path="/consultas" element={<ListadoConsultas />} />
            <Route
              path="/editar/:id"
              element={<EditarInstitucion />}/>
          </Routes>
        </div>
        <Footer />
      </div>
    </Router>
  );
}

function Inicio() {
  return (
    <div>
      {/* Encabezado */}
      <header className="py-3">
        <div className="container">
          <div className="row bg-light rounded-3 text-center">
            <div className="m-2 m-lg-3">
              <h1 className="fs-3 fw-bold">
                Bienvenido a{" "}
                <span className="text-success fw-bold fs-2">SIPRESS</span>
              </h1>
              <p className="fs-6">
                El Sistema Integral para la Prestación de Servicios de Salud
                -SIPRESS es una aplicación diseñada para simplificar la gestión
                de las Instituciones Prestadoras de Salud (IPS), facilitando el
                manejo de la información de los pacientes, personal médico y
                administrativo, así como de los servicios que se prestan en la
                institución. Su objetivo principal es mejorar la atención y la
                eficiencia en la prestación de servicios hospitalarios.
              </p>
            </div>
          </div>
        </div>
      </header>
      {/* Contenido */}
      <section className="index">
        <div className="container">
          <div className="row gx-lg-5">
            <div className="col-md-3 col-sm-6">
              <a href="/instituciones" className="text-decoration-none">
                <div className="card text-center h-100 custom-card">
                  <div className="card-header">
                    <h3 className="card-title">Sedes</h3>
                  </div>
                  <div className="card-body">
                    <img
                      src="images/institucion.png"
                      alt="institucion.png"
                      className="card-img-top"
                    />
                  </div>
                </div>
              </a>
            </div>
            <div className="col-md-3 col-sm-6">
              <a href="/personalS" className="text-decoration-none">
                <div className="card text-center h-100 custom-card">
                  <div className="card-header">
                    <h3 className="card-title">Personal</h3>
                  </div>
                  <div className="card-body">
                    <img
                      src="images/personal.png"
                      alt="personal.png"
                      className="card-img-top"
                    />
                  </div>
                </div>
              </a>
            </div>
            <div className="col-md-3 col-sm-6">
              <a href="/doctores" className="text-decoration-none">
                <div className="card text-center h-100 custom-card">
                  <div className="card-header">
                    <h3 className="card-title">Doctores</h3>
                  </div>
                  <div className="card-body">
                    <img
                      src="images/equipo-medico.png"
                      alt="equipo-medico.png"
                      className="card-img-top"
                    />
                  </div>
                </div>
              </a>
            </div>
            <div className="col-md-3 col-sm-6">
              <a href="/pacientes" className="text-decoration-none">
                <div className="card text-center h-100 custom-card">
                  <div className="card-header">
                    <h3 className="card-title">Pacientes</h3>
                  </div>

                  <div className="card-body">
                    <img
                      src="images/patients.png"
                      alt="patients.png"
                      className="card-img-top"
                    />
                  </div>
                </div>
              </a>
            </div>
          </div>
        </div>
        <br />
      </section>
    </div>
  );
}

export default App;
