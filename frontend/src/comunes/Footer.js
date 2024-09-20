import React from "react";

/**
 * Componente para mostrar el pie de página en todas las páginas.
 * @returns El componente Footer.
 * @version 1.0
 */

const Footer = () => {
  return (
    <footer className="py-5" id="iconos">
      <div className="py-2 text-center">
        <span> Copyright &copy; 2024, Codelicht Software Solutions.</span>
        <p>Todos los derechos reservados.</p>
        <div className="text-center">
          <a
            href="https://github.com/MauricioMonroy/sipress-web-app.git"
            aria-label="Repositorio GitHub de Mauricio Monroy"
            rel="noopener noreferrer"
            target="_blank">
            <i className="fa-brands fa-github"></i>
          </a>
          <a
            href="https://call.whatsapp.com/voice/JqIwkDwqS2qpUUqbFjMwwd"
            aria-label="Contacto Whatsapp"
            rel="noopener noreferrer"
            target="_blank">
            <i className="fa-brands fa-whatsapp"></i>
          </a>
          <a
            href="https://t.me/+GVvMDQ4p8p84ZWJh"
            aria-label="Contacto Telegram"
            rel="noopener noreferrer"
            target="_blank">
            <i className="fa-brands fa-telegram"></i>
          </a>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
