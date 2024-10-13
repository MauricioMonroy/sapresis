describe("Prueba de registro y eliminación de un doctor", () => {
  it("Debería permitir que un usuario con rol ADMIN pueda registrar un doctor y luego ser eliminado por un SUPERADMIN", () => {
    // Visitar la página de inicio de sesión
    cy.visit("http://localhost:3000");

    // Iniciar sesión como usuario con rol ADMIN
    cy.get('input[name="email"]').first().type("usuario@admin.com");
    cy.get('input[name="password"]').first().type("admin");

    // Hacer clic en el botón de inicio de sesión
    cy.get('button[type="submit"]').first().click();

    // Verificar que aparezca una notificación de éxito
    cy.get(".Toastify__toast--success").should(
      "contain",
      "Bienvenido a Sapresis",
      { timeout: 5000 }
    );

    // Ir a la página de doctores
    cy.visit("http://localhost:3000/doctores");

    // Hacer clic en el botón de agregar doctor
    cy.wait(2000).contains("Agregar Registro").click();

    // Asegurarse de que el modal de registro esté visible
    cy.wait(2000).get("#AgregarDoctorModal").should("be.visible");

    // Completar el formulario de registro en el modal
    cy.get('#AgregarDoctorModal input[name="idDoctor"]').first().type("98765");
    cy.get('#AgregarDoctorModal input[name="nombreDoctor"]')
      .first()
      .type("Doctor de prueba");
    cy.get('#AgregarDoctorModal input[name="apellidoDoctor"]')
      .first()
      .type("Apellido de prueba");
    cy.get('#AgregarDoctorModal input[name="telefonoDoctor"]')
      .first()
      .type("1234567890");
    cy.get('#AgregarDoctorModal input[name="emailDoctor"]')
      .first()
      .type("doctor@prueba.com");
    // Seleccionar dependencia
    cy.wait(500).get("#AgregarDoctorModal select").select("Radiología");

    // Hacer clic en el botón de guardar registro
    cy.get('#AgregarDoctorModal button[type="submit"]').click();

    // Verificar que aparezca una notificación de éxito
    cy.get(".Toastify__toast--success").should(
      "contain",
      "Registro agregado correctamente",
      { timeout: 5000 }
    );

    // Cambiar la cantidad de registros visibles por página a 100
    cy.get("select#pageSize").select("100");

    // Verificar que el registro del doctor esté en la tabla
    cy.get("tbody").find("tr").contains("td", "12345").should("be.visible");

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

    // Hacer clic en el botón de menú Doctores
    cy.wait(5000).contains("Doctores").click();

    // Cambiar la cantidad de registros visibles por página a 100
    cy.get("select#pageSize").select("100");

    // Identificar la fila del registro específico usando el correo electrónico
    cy.scrollTo("bottom", { ensureScrollable: false });
    cy.wait(1000)
      .contains("tr", "doctor@prueba.com")
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
    cy.scrollTo("bottom", { ensureScrollable: false });

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
  });
});
