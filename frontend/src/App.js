/**
 * @file App.js
 * @brief Archivo de configuración de rutas de la aplicación.
 * @requires react
 * @requires react-router-dom, useLocation
 * */

import React from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  useLocation,
} from "react-router-dom";
import Inicio from "./comunes/Inicio";
import Navbar from "./comunes/Navbar";
import Footer from "./comunes/Footer";
import Login from "./comunes/Login";
import Logout from "./comunes/Logout";
import RegistroUsuario from "./formularios/RegistroUsuario";
import GestionUsuarios from "./registros/GestionUsuarios";
import { Notificaciones } from "./comunes/Notificaciones";
import "./comunes/axiosConfig";
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
import EditarUsuario from "./formularios/EditarUsuario";

function App() {
  return (
    <Router>
      <Main />
    </Router>
  );
}

function Main() {
  const location = useLocation();
  const noNavAndFooterRoutes = [
    "/",
    "/login",
    "/logout",
    "/logout-success",
    "/registro",
  ];

  return (
    <div className="App">
      {/* Renderiza Navbar solo si la ruta actual no está en noNavAndFooterRoutes */}
      {!noNavAndFooterRoutes.includes(location.pathname) && <Navbar />}

      {/* Renderiza notificaciones en todas las rutas */}
      <Notificaciones />

      {/* Rutas de la aplicación */}
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/registro" element={<RegistroUsuario />} />
        <Route path="/gestion-usuarios" element={<GestionUsuarios />} />
        <Route path="/logout-success" element={<Logout />} />
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
        <Route path="/usuarios/editar/:id" element={<EditarUsuario />} />
      </Routes>

      {/* Renderiza Footer solo si la ruta actual no está en noNavAndFooterRoutes */}
      {!noNavAndFooterRoutes.includes(location.pathname) && <Footer />}
    </div>
  );
}

export default App;
