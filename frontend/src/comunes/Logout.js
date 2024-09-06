import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const Logout = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const timer = setTimeout(() => {
      navigate("/login");
    }, 3000); // Redirige después de 3 segundos

    return () => clearTimeout(timer);
  }, [navigate]);

  window.history.pushState(null, "", window.location.href);
  window.onpopstate = function () {
    window.history.go(1);
  };

  return (
    // HTML que muestra el mensaje de salida y redirige al login
    <div className="container">
      <div className="row bg-light rounded-3 text-center m-5">
        <div className="m-2">
          <h1 className="fs-5">
            Ha salido correctamente de la aplicación. Redirigiendo al login...
          </h1>
          <p className="fs-6">
            <a href="/login">
              Haga clic aquí si no es redirigido automáticamente.
            </a>
          </p>
        </div>
      </div>
    </div>
  );
};

export default Logout;
