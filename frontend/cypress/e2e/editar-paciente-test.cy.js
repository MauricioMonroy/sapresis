describe("PR-006: Prueba de edición de un registro Paciente", () => {
  it("Debería permitir que un usuario con rol ADMIN pueda editar un registro, en este caso de la entidad Paciente", () => {
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

    // Hacer clic en el botón de menú Pacientes
    cy.wait(2000).contains("Pacientes").click();

    // Identificar la fila del paciente específico usando ID del paciente
    cy.wait(500)
      .get("tr[data-id='3']")
      .within(() => {
        // Dentro de la fila seleccionada, hacer clic en el botón de editar
        cy.get("a.btn-warning").click();
      });

    // Modificar el campo de nombre completo
    cy.wait(500)
      .get('input[name="nombrePaciente"]')
      .first()
      .clear()
      .type("Paciente Editado");

    // Modificar el campo de apellido
    cy.wait(500)
      .get('input[name="apellidoPaciente"]')
      .first()
      .clear()
      .type("Apellido Editado");

    // Modificar el campo de dirección
    cy.wait(500)
      .get('input[name="direccionPaciente"]')
      .first()
      .clear()
      .type("Dirección Editada");

    // Modificar el campo de teléfono
    cy.wait(500)
      .get('input[name="telefonoPaciente"]')
      .first()
      .clear()
      .type("1234567890");

    // Modificar el campo de email
    cy.wait(500)
      .get('input[name="emailPaciente"]')
      .first()
      .clear()
      .type("correo@editado.com");

    // Seleccionar una EPS diferente
    cy.wait(500).get("select").select("PrevenSalud");

    // Hacer clic en el botón de guardar registro
    cy.get('button[type="submit"]').click();

    // Verificar que aparezca una notificación de éxito
    cy.get(".Toastify__toast--success").should(
      "contain",
      "Registro actualizado con éxito",
      { timeout: 5000 }
    );

    // Tiempo de espera para visualizar la lista de pacientes actualizada
    cy.wait(4000);

    // Restaurar los valores originales
    cy.wait(500)
      .get("tr[data-id='3']")
      .within(() => {
        // Dentro de la fila seleccionada, hacer clic en el botón de editar
        cy.get("a.btn-warning").click();
      });

    // Modificar el campo de nombre completo
    cy.wait(500)
      .get('input[name="nombrePaciente"]')
      .first()
      .clear()
      .type("Hugo");

    // Modificar el campo de apellido
    cy.wait(500)
      .get('input[name="apellidoPaciente"]')
      .first()
      .clear()
      .type("Trejos");

    // Modificar el campo de dirección
    cy.wait(500)
      .get('input[name="direccionPaciente"]')
      .first()
      .clear()
      .type("Calle 18b 96-38");

    // Modificar el campo de teléfono
    cy.wait(500)
      .get('input[name="telefonoPaciente"]')
      .first()
      .clear()
      .type("3114771125");

    // Modificar el campo de email
    cy.wait(500)
      .get('input[name="emailPaciente"]')
      .first()
      .clear()
      .type("hugo.trejos@correo.com");

    // Seleccionar la EPS original
    cy.wait(500).get("select").select("Traumasalud");

    // Hacer clic en el botón de guardar registro
    cy.get('button[type="submit"]').click();

    // Verificar que aparezca una notificación de éxito
    cy.get(".Toastify__toast--success").should(
      "contain",
      "Registro actualizado con éxito",
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
