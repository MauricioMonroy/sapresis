describe("PR-003: Prueba de limitación de credenciales de usuario con rol USER en Sapresis", () => {
  it("Debería limitar las credenciales de un usuario con rol USER en el frontend y verificar la respuesta del backend", () => {
    // Visitar la página de inicio de sesión
    cy.visit("http://localhost:3000");

    // Completar el formulario de inicio de sesión
    cy.get('input[name="email"]').first().type("rol.user@test.com");
    cy.get('input[name="password"]').first().type("user");

    // Hacer clic en el botón de inicio de sesión
    cy.get('button[type="submit"]').first().click();

    // Verificar que aparezca una notificación de éxito
    cy.get(".Toastify__toast--success").should(
      "contain",
      "Bienvenido a Sapresis",
      { timeout: 5000 }
    );

    // Verificar que no aparezca el menu Usuarios
    cy.get(".navbar-nav").should("not.contain", "Usuarios");

    // Ingresa a la sección Sedes
    cy.wait(2000).visit("http://localhost:3000/instituciones");

    // Verificar que no aparezca el botón Editar en la tabla
    cy.get("table").should("not.contain", "Editar");

    // Verificar que aparezca la notificación de error cuando intenta agregar un registro
    cy.wait(500).contains("Agregar Registro").click();
    cy.wait(5000)
      .get(".Toastify__toast--error")
      .should(
        "contain",
        "No tiene los permisos necesarios para agregar un registro.",
        { timeout: 5000 }
      );

    // Salir de la aplicación
    cy.wait(4000).contains("Salir de la aplicación").click();

    // Verificar que aparezca un mensaje de éxito
    cy.contains(
      "Ha salido correctamente de la aplicación. Redirigiendo al login..."
    );

    // Esperar 3 segundos para redirigir al login
    cy.wait(3000);

    // Verificar que se redirige al login
    cy.url().should("include", "/login");
  });
});
