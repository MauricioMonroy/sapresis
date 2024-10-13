describe("Prueba de inicio de sesión de usuario en Sapresis", () => {
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
  });
});
