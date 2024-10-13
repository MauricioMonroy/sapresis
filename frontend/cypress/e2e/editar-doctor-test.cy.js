describe("Prueba de edición de un registro Doctor", () => {
    it("Debería permitir que un usuario con rol ADMIN pueda editar un registro, en este caso de la entidad Doctor", () => {
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
  
      // Hacer clic en el botón de menú Doctores
      cy.wait(2000).contains("Doctores").click();
  
      // Identificar la fila del doctor específico usando ID del doctor
      cy.wait(500)
        .get("tr[data-id='2002']")
        .within(() => {
          // Dentro de la fila seleccionada, hacer clic en el botón de editar
          cy.get("a.btn-warning").click();
        });
  
      // Modificar el campo de nombre completo
      cy.wait(500)
        .get('input[name="nombreDoctor"]')
        .first()
        .clear()
        .type("Doctor Editado");
  
      // Modificar el campo de apellido
      cy.wait(500)
        .get('input[name="apellidoDoctor"]')
        .first()
        .clear()
        .type("Apellido Editado");  
  
      // Modificar el campo de teléfono
      cy.wait(500)
        .get('input[name="telefonoDoctor"]')
        .first()
        .clear()
        .type("1234567890");
  
      // Modificar el campo de email
      cy.wait(500)
        .get('input[name="emailDoctor"]')
        .first()
        .clear()
        .type("correo@editado.com");
  
      // Seleccionar una Dependencia diferente
      cy.wait(500).get("select").select("Diagnóstico especializado");
  
      // Hacer clic en el botón de guardar registro
      cy.get('button[type="submit"]').click();
  
      // Verificar que aparezca una notificación de éxito
      cy.get(".Toastify__toast--success").should(
        "contain",
        "Registro actualizado con éxito",
        { timeout: 5000 }
      );
  
      // Tiempo de espera para visualizar la lista de doctores actualizada
      cy.wait(4000);
  
      // Restaurar los valores originales
      cy.wait(500)
        .get("tr[data-id='2002']")
        .within(() => {
          // Dentro de la fila seleccionada, hacer clic en el botón de editar
          cy.get("a.btn-warning").click();
        });
  
      // Modificar el campo de nombre completo
      cy.wait(500)
        .get('input[name="nombreDoctor"]')
        .first()
        .clear()
        .type("Gustavo");
  
      // Modificar el campo de apellido
      cy.wait(500)
        .get('input[name="apellidoDoctor"]')
        .first()
        .clear()
        .type("Restrepo");  
 
  
      // Modificar el campo de teléfono
      cy.wait(500)
        .get('input[name="telefonoDoctor"]')
        .first()
        .clear()
        .type("3214471023");
  
      // Modificar el campo de email
      cy.wait(500)
        .get('input[name="emailDoctor"]')
        .first()
        .clear()
        .type("gustavo.restrepo@mail.com");
  
      // Seleccionar la Dependencia original
      cy.wait(500).get("select").select("Radiología");
  
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