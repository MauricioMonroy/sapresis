describe("Prueba de registro y eliminacíon de un usuario en Sapresis", () => {
  it("Debería permitir el registro y la eliminación de un usuario desde el frontend y verificar la respuesta del backend", () => {
    // Visitar la página de inicio de sesión
    cy.visit("http://localhost:3000/login");

    // Abrir el modal de registro
    cy.contains("¿No tiene una cuenta? Regístrese").click();

    // Asegurarse de que el modal de registro esté visible
    cy.wait(2000).get("#RegistroModal").should("be.visible");

    // Completar el formulario de registro en el modal
    cy.get('#RegistroModal input[name="nombreCompleto"]')
      .first()
      .type("Usuario de prueba");
    cy.get('#RegistroModal input[name="email"]')
      .first()
      .type("usuario.prueba@test.com");
    cy.get('#RegistroModal input[name="password"]').first().type("123456");
    cy.get('#RegistroModal input[name="confirmPassword"]')
      .first()
      .type("123456");

    // Hacer clic en el botón de registro
    cy.get('#RegistroModal button[type="submit"]').click();

    // Verificar que aparezca una notificación de éxito
    cy.get(".Toastify__toast--success").should(
      "contain",
      "Registro exitoso. Ahora puede iniciar sesión."
    );

    // Iniciar sesión con el usuario recién registrado
    cy.get('input[name="email"]').first().type("usuario.prueba@test.com");
    cy.get('input[name="password"]').first().type("123456");

    // Hacer clic en el botón de inicio de sesión
    cy.get('button[type="submit"]').first().click();

    // Verificar que aparezca una notificación de éxito
    cy.get(".Toastify__toast--success").should(
      "contain",
      "Bienvenido a Sapresis",
      { timeout: 5000 }
    );

    // Cerar sesión
    cy.wait(7000).contains("Salir de la aplicación").click();

    // Verificar que aparezca un mensaje de éxito
    cy.contains(
      "Ha salido correctamente de la aplicación. Redirigiendo al login..."
    );

    // Esperar 3 segundos para redirigir al login
    cy.wait(3000);

    // Verificar que se redirige al login
    cy.url().should("include", "/login");

    // Iniciar sesión como usuario con rol SUPERADMIN
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

    // Visitar la página de administración de usuarios
    cy.visit("http://localhost:3000/gestion-usuarios");

    // Cambiar la cantidad de registros visibles por página a 100
    cy.get("select#pageSize").select("100");

    // Identificar la fila del usuario específico usando el correo electrónico
    cy.scrollTo("bottom", {ensureScrollable: false});
    cy.wait(1000).contains("tr", "usuario.prueba@test.com")      
      .within(() => {
        // Dentro de la fila seleccionada, hacer clic en el botón de eliminar
        cy.contains("Eliminar").click({ force: true });
      });

    // Verificar que aparezca una notificación de confirmación
    cy.get(".Toastify__toast-body").should(
      "contain",
      "¿Está seguro de eliminar este registro?"
    );

    // Hacer clic en el botón de confirmación de eliminación
    cy.get(".Toastify__toast-body").contains("Sí").click();

    // Verificar que aparezca una notificación de éxito
    cy.get(".Toastify__toast--success").should(
      "contain",
      "Registro eliminado correctamente",
      { timeout: 5000 }
    );
    cy.scrollTo("bottom", {ensureScrollable: false});

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
