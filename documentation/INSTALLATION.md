![Logo SIPRESS](../frontend/src/assets/images/sipress-logo.png)

![SIPRESS Web App](https://img.shields.io/badge/SIPRESS-Web%20App-blue)

# Guía de Instalación / Installation Guide

La aplicación web SIPRESS es una herramienta de gestión de pacientes y consultorios médicos. Permite a los usuarios
llevar un registro de los pacientes, asignar consultas y médicos, y visualizar recetas médicas. La aplicación está
desarrollada con React y utiliza una API REST para la gestión de datos. /The SIPRESS web app is a patient and medical
office management tool. It allows users to keep track of patients, assign consultations and doctors, and view medical
prescriptions. The application is developed with React and uses a REST API for data management.

Para un correcto funcionamiento de la aplicación, es necesario seguir los pasos de instalación especificados a
continuación. / For the application to work correctly, it is necessary to follow the installation steps specified below.

---

## Contenido / Table of Contents

- [Prerequisitos / Prerequisites](#prerequisitos--prerequisites)
- [Pasos de Instalación / Installation Steps](#pasos-de-instalación--installation-steps)
- [Funcionalidades Principales / Key Features](#funcionalidades-principales--key-features)
- [Scripts Disponibles / Available Scripts](#scripts-disponibles--available-scripts)
- [Contribuir / Contributing](#contribuir--contributing)
- [Recursos y Documentación / Resources and Documentation](#recursos-y-documentación--resources-and-documentation)

---

## Prerequisitos / Prerequisites

- Node.js
- npm
- Git
- Backend API ( [SIPRESS API](https://github.com/MauricioMonroy/sipress-web-app/tree/main/backend) )

## Pasos de Instalación / Installation Steps

1. Clona el repositorio / Clone the repository:
   ```bash
   git clone https://github.com/MauricioMonroy/sipress-web-app.git
    ```
2. Navega al directorio / Navigate to the directory `frontend`:
   ```bash
    cd sipress-web-app/frontend
    ```
3. Instala las dependencias / Install the dependencies:
    ```bash
    npm install
    ```
4. Inicia la aplicación en modo desarrollo / Start the application in development mode:
    ```bash
    npm start
    ```
5. Abre [http://localhost:3000](http://localhost:3000) en tu navegador para ver la aplicación. / Open
   [http://localhost:3000](http://localhost:3000) in your browser to view the application.

---

## Funcionalidades Principales / Key Features

- Gestión de pacientes: Agregar, editar y eliminar registros de pacientes. / Patient management: `Add`, `edit`, and
  `delete`
  patient records.
- Asignación de consultas y médicos. / Assignment of consultations and doctors.
- Visualización de recetas médicas. / Viewing medical prescriptions.
- Gestión de empleados y consultorios. / Employee and clinic management.

- **Nota:** Para el correcto funcionamiento de la aplicación, es necesario contar con la API REST de SIPRESS. / **Note:
  **
  For the correct operation of the application, it is necessary to have the SIPRESS REST API.

---

## Scripts Disponibles / Available Scripts

En el directorio del proyecto, puedes ejecutar / In the project directory, you can run:

### `npm start`

Ejecuta la aplicación en modo desarrollo. / Runs the app in the development mode.

Abre [http://localhost:3000](http://localhost:3000) en tu navegador para ver la aplicación. /
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

La página se recargará si haces cambios. / The page will reload if you make changes.\
También verás cualquier error de lint en la consola. / You will also see any lint errors in the console.

### `npm test`

Lanza el corredor de pruebas en modo interactivo.\
Consulta la sección sobre [ejecución de pruebas](https://facebook.github.io/create-react-app/docs/running-tests) para
más información. / Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more
information.

### `npm run build`

Construye la aplicación para producción en la carpeta `build`. / Builds the app for production to the `build` folder.\
Empaqueta correctamente React en modo de producción y optimiza la compilación para el mejor rendimiento. / It correctly
bundles React in production mode and optimizes the build for the best performance.

La compilación está minificada y los nombres de archivo incluyen los hashes. /
The build is minified and the filenames include the hashes.\
¡Tu aplicación está lista para ser desplegada! /
Your app is ready to be deployed!

Consulta la sección sobre [despliegue](https://facebook.github.io/create-react-app/docs/deployment) para más
información./
See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

**Nota: esta es una operación unidireccional. Una vez que `eject` se ejecuta, no se puede volver atrás.** /
**Note: this is a one-way operation. Once you `eject`, you can’t go back!**

Si no estás satisfecho con la herramienta de compilación y las opciones de configuración, puedes usar `eject` en
cualquier momento. Este comando eliminará la única dependencia de compilación del proyecto /
If you aren't satisfied with the build tool and configuration choices, you can `eject` at any time. This command will
remove the single build dependency from your project.

En su lugar, copiará todos los archivos de configuración y las dependencias transitivas (webpack, Babel, ESLint, etc.)
directamente en tu proyecto para que tengas control total sobre ellos. Todos los comandos, excepto `eject`, seguirán
funcionando, pero apuntarán a los scripts copiados para que puedas modificarlos. En este punto, estás por tu cuenta.
/ Instead, it will copy all the configuration files and the transitive dependencies (webpack, Babel, ESLint, etc) right
into your project so you have full control over them. All of the commands except `eject` will still work, but they will
point to the copied scripts so you can tweak them. At this point you're on your own.

No tienes que usar `eject`. El conjunto de características seleccionadas está listo para despliegues pequeños y
medianos, y no deberías sentirte obligado a usar esta función. Sin embargo, entendemos que esta herramienta no sería
útil si no pudieras personalizarla cuando estás listo para hacerlo. / You don't have to ever use `eject`. The curated
feature set is suitable for small and middle deployments, and you
shouldn't feel obligated to use this feature. However we understand that this tool wouldn't be useful if you could not
customize it when you are ready for it.

---

## Contribuir / Contributing

Las contribuciones son bienvenidas. Si deseas contribuir, por favor sigue los siguientes pasos / Contributions are
welcome. If you would like to contribute, please follow these steps:

1. Haz un fork del repositorio. / Fork the repository.
2. Crea una nueva rama / Create a new branch (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz un commit / Make your changes and commit them (
   `git commit -am 'Agrega nueva funcionalidad'`).
4. Envía tus cambios / Push your changes (`git push origin feature/nueva-funcionalidad`).
5. Crea un Pull Request. / Create a Pull Request.

---

## Recursos y Documentación / Resources and Documentation

Puedes aprender más sobre Create React App en la / You can learn more in
the [Documentación oficial / Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

Para aprender React, revisa la / To learn React, check out
the [Documentación oficial de React / React documentation](https://reactjs.org/).

---

## Autor / Author

### Mauricio Alberto Monroy Calle

- GitHub: [@MauricioMonroy](https://github.com/MauricioMonroy)

Si tiene alguna pregunta o sugerencia sobre la arquitectura del backend de SIPRESS, no dude en ponerse en contacto
conmigo/ If you have any questions or suggestions about the SIPRESS backend architecture, feel free to contact me:

- Correo electrónico/ Email: `mauricio.monroy0@soy.sena.edu.co` o `mauriciomonroy@live.cl`