import React from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  useLocation,
} from "react-router-dom";
import Navbar from "./comunes/Navbar";
import Footer from "./comunes/Footer";
import Login from "./comunes/Login";
import ResultadosBusqueda from "./comunes/ResultadosBusqueda";
import "./styles.css";
import "primereact/resources/themes/saga-blue/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import ListadoPacientes from "./registros/ListadoPacientes";
import ListadoDoctores from "./registros/ListadoDoctores";
import ListadoPersonal from "./registros/ListadoPersonal";
import ListadoInstituciones from "./registros/ListadoInstituciones";
import ListadoDependencias from "./registros/ListadoDependencias";
import ListadoEps from "./registros/ListadoEps";
import ListadoConsultorios from "./registros/ListadoConsultorios";
import ListadoFormulas from "./registros/ListadoFormulas";
import ListadoFacturas from "./registros/ListadoFacturas";
import ListadoConsultas from "./registros/ListadoConsultas";
import EditarInstitucion from "./formularios/EditarInstitucion";
import EditarPersonal from "./formularios/EditarPersonal";
import EditarEps from "./formularios/EditarEps";
import EditarDoctor from "./formularios/EditarDoctor";
import EditarPaciente from "./formularios/EditarPaciente";
import EditarDependencia from "./formularios/EditarDependencia";
import EditarFormula from "./formularios/EditarFormula";
import DetalleFormula from "./registros/DetalleFormula";
import EditarFactura from "./formularios/EditarFactura";
import DetalleFactura from "./registros/DetalleFactura";
import EditarConsultorio from "./formularios/EditarConsultorio";
import EditarConsulta from "./formularios/EditarConsulta";

function App() {
  return (
    <Router>
      <Main />
    </Router>
  );
}

function Main() {
  const location = useLocation();
  const noNavAndFooterRoutes = ["/", "/login"];

  return (
    <div className="App">
      {/* Renderiza Navbar solo si la ruta actual no está en noNavAndFooterRoutes */}
      {!noNavAndFooterRoutes.includes(location.pathname) && <Navbar />}

      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/inicio" element={<Inicio />} />
        <Route path="/pacientes" element={<ListadoPacientes />} />
        <Route path="/doctores" element={<ListadoDoctores />} />
        <Route path="/personalS" element={<ListadoPersonal />} />
        <Route path="/instituciones" element={<ListadoInstituciones />} />
        <Route path="/dependencias" element={<ListadoDependencias />} />
        <Route path="/epsS" element={<ListadoEps />} />
        <Route path="/consultorios" element={<ListadoConsultorios />} />
        <Route path="/formulas" element={<ListadoFormulas />} />
        <Route path="/facturas" element={<ListadoFacturas />} />
        <Route path="/consultas" element={<ListadoConsultas />} />
        <Route
          path="/instituciones/editar/:id"
          element={<EditarInstitucion />}
        />
        <Route path="/personalS/editar/:id" element={<EditarPersonal />} />
        <Route path="/epsS/editar/:id" element={<EditarEps />} />
        <Route path="/doctores/editar/:id" element={<EditarDoctor />} />
        <Route path="/pacientes/editar/:id" element={<EditarPaciente />} />
        <Route
          path="/dependencias/editar/:id"
          element={<EditarDependencia />}
        />
        <Route path="/formulas/editar/:id" element={<EditarFormula />} />
        <Route path="/formulas/detalle/:id" element={<DetalleFormula />} />
        <Route path="/facturas/editar/:id" element={<EditarFactura />} />
        <Route path="/facturas/detalle/:id" element={<DetalleFactura />} />
        <Route
          path="/consultorios/editar/:id"
          element={<EditarConsultorio />}
        />
        <Route
          path="/consultas/editar/:pacienteId/:doctorId"
          element={<EditarConsulta />}
        />
        <Route path="/resultados-busqueda" element={<ResultadosBusqueda />} />
      </Routes>

      {/* Renderiza Footer solo si la ruta actual no está en noNavAndFooterRoutes */}
      {!noNavAndFooterRoutes.includes(location.pathname) && <Footer />}
    </div>
  );
}

function Inicio() {
  return (
    <div className="py-2 mb-5 mt-5">
      {/* Encabezado */}
      <header>
        <div className="container">
          <div className="row bg-light rounded-3 text-center m-5">
            <div className="m-2">
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
