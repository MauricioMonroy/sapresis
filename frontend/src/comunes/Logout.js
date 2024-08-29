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

  return (
    <div className="container-fluid mt-5 mb-2 p-4" id="details">
      <p>Ha salido correctamente de la aplicación. Redirigiendo al login...</p>
      <a href="/login">Haga clic aquí si no es redirigido automáticamente.</a>
    </div>
  );
};

export default Logout;
