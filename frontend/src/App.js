import React from "react";
import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import ListadoPacientes from "./pacientes/ListadoPacientes";
import Navbar from "./comunes/Navbar";
import Footer from "./comunes/Footer";
import "./styles.css";

function App() {
  return (
    <Router>
      <div className="App">
        <Navbar />
        <div className="container">
          <Routes>
            <Route path="/" element={<Inicio />} />
            <Route path="/pacientes" element={<ListadoPacientes />} />            
            {/* Agrega más rutas aquí */}
          </Routes>
          <li>
              <Link to="/pacientes">Lista de Pacientes</Link>
            </li>
        </div>
        <Footer />
      </div>
    </Router>
  );
}

function Inicio() {
  return (
    <div>
      <h2>Página de Inicio</h2>
      <p>Bienvenido a la aplicación SIPRESS.</p>
      {/* Agrega más contenido aquí */}
    </div>
  );
}

export default App;
