describe("PR-001: Prueba de inicio de sesión de usuario en Sapresis", () => {
  it("Debería permitir el inicio de sesión de un usuario desde el frontend y verificar la respuesta del backend", () => {
    // Visitar la página de inicio de sesión
    cy.visit("http://localhost:3000");

    // Completar el formulario de inicio de sesión
    cy.get('input[name="email"]').first().type("super.admin@correo.com");
    cy.get('input[name="password"]').first().type("G)T,T_Yr8]c6:YM");

    // Hacer clic en el botón de inicio de sesión
    cy.get('button[type="submit"]').first().click();

    // Verificar que aparezca una notificación de éxito
    cy.get(".Toastify__toast--success").should(
      "contain",
      "Bienvenido a Sapresis",
      { timeout: 5000 }
    );

    // Cerrar sesión
    cy.wait(7000).contains("Salir de la aplicación").click();

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
