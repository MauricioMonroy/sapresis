import React from "react";

const BotonesNavegacion = () => {
  return (
    <section id="actions">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-md-4 d-flex justify-content-center">
                <a href="ServletUsuario" class="btn btn-info">
                    <i class="fa-solid fa-arrow-left-long"></i> Regresar a la lista
                </a>
            </div>
            <div class="col-12 col-md-4 d-flex justify-content-center">
                <button class="btn btn-success" type="submit">
                    <i class="fa-solid fa-clipboard-check"></i> Guardar Cambios
                </button>
            </div>
            <div class="col-12 col-md-4 d-flex justify-content-center">
                <a href="/"
                   class="btn btn-danger" type="submit">
                    <i class="fa-solid fa-user-slash"></i> Eliminar Registro
                </a>
            </div>
        </div>
    </div>
</section>
  );
};

export default BotonesNavegacion;