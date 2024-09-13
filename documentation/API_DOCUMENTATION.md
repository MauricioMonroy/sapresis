# 📚 Documentación de SIPRESS API

---

Este documento ofrece una guía completa sobre cómo utilizar la API de SIPRESS, un sistema desarrollado para la gestión
de información en instituciones prestadoras de salud.

SIPRESS es un proyecto académico desarrollado para la Tecnología de Análisis y Desarrollo de Software (2721455) del
SENA. Este sistema está diseñado para ser utilizado por el personal de instituciones prestadoras de salud y permite la
consulta y gestión de información crítica relacionada con la administración de personal, la asignación de consultas y el
manejo de pacientes.

La API REST de SIPRESS ofrece un conjunto de servicios que permiten realizar operaciones como la gestión de empleados,
pacientes, consultorios, fórmulas médicas, y más. La API también incluye autenticación y autorización para garantizar
que solo los usuarios autorizados puedan acceder a los recursos del sistema.

## **Guía de inicio**

Para comenzar a utilizar la API de SIPRESS, se debe tener en cuenta lo siguiente:

- Es necesario utilizar una clave API válida para enviar solicitudes a los endpoints de la API.

- Para obtener la clave API es necesario registrarse en el sistema.

- La API devuelve respuestas en formato JSON.

- Cuando una solicitud API genera un error, este se incluye en la respuesta JSON bajo la clave de error.

## Request

La clave de la API de SIPRESS utiliza autenticación basada en tokens JWT (JSON Web Token) para proteger los endpoints.
Todos los usuarios deben autenticarse proporcionando sus credenciales para obtener un token de acceso. Solo los usuarios
con credenciales de `ADMIN` o `SUPERADMIN` pueden gestionar solicitudes.

### Pasos para la autenticación:

1. Se debe enviar una solicitud POST al endpoint `/sipress-app/auth/login` con las credenciales del usuario (`email` y
   `password`).

2. Si las credenciales son válidas, se recibirá un token JWT en la respuesta.

3. Este token se debe incluir en el encabezado de autorización de cada solicitud subsiguiente, usando el formato:
   Authorization: `bearer token`.

### Respuesta de error en la autenticación

Si el token está ausente, está malformado o es inválido, se mostrará un código de respuesta HTTP `401 Unauthorized`.

### **¿Necesita ayuda?**

Si tiene preguntas, puede revisar el repositorio de
GitHub [login-registration-service](https://github.com/MauricioMonroy/login-registration-service.git) en donde se
explica con más detalle el funcionamiento del registro y la autenticación. También se puede revisar el repositorio de
GitHub [SIPRESS](https://github.com/MauricioMonroy/sipress-web-app.git), en donde se detalla la estructura, las
características y el funcionamiento de la aplicación.

___

# Contenido

- [Servicio de login y registro](#servicio-de-login-y-registro)
- [Servicios de sipress-web-app](#servicios-de-sipress-web-app)
  - [Servicio de institución](#servicio-de-institución)
  - [Servicio de personal](#servicio-de-personal)
  - [Servicio de paciente](#servicio-de-paciente)
  - [Servicio de fórmula](#servicio-de-fórmula)
  - [Servicio de factura](#servicio-de-factura)
  - [Servicio de EPS](#servicio-de-eps)
  - [Servicio de doctor](#servicio-de-doctor)
  - [Servicio de dependencia](#servicio-de-dependencia)
  - [Servicio de consultorio](#servicio-de-consultorio)
  - [Servicio de consulta](#servicio-de-consulta)
- [Referencias](#referencias)

___

# Servicio de login y registro

Los endpoints `/sipress-app/auth/login` y `/sipress-app/auth/registro` permiten la gestión de la autorización y la
autenticación en la API, esto es, el ingreso y el registro de usuarios, generando un token que es necesario para el
acceso a los distintos endpoints de la aplicación.

El endpoint `/sipress-app/usuarios/perfil` permite visualizar la información relacionada con un registro específico,
mientras que el endpoint `/sipress-app/usuarios/` permite ver todos los registros.

## <p style="color:khaki">POST</p> Iniciar sesión

### Endpoint

```http request
/sipress-app/auth/login
```

Este endpoint permite el inicio de sesión en la aplicación, siempre y cuando se usen las credenciales correctas. La
respuesta arroja un token que autoriza el acceso a diferentes rutas protegidas.

### Request body

```json
{
  "email": "string",
  "password": "string"
}

 ```

### Response

```json
{
  "token": "",
  "expiresIn": ""
}

 ```

## Autorización *`No se requiere autorización`*

### Body Parameters

```json
{
  "email": "super.admin@correo.com",
  "password": "superadmin"
}
```

___

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXBlci5hZG1pbkBjb3JyZW8uY29tIiwiaWF0IjoxNzI0MDE0OTcwLCJleHAiOjE3MjQwMTg1NzB9.QYjgQo0mCBeFVYPwbKYrZ4Cu-Y6utaTf6mjIjL-1LzM",
  "expiresIn": 3600000
}
```

### Respuesta con credenciales incorrectas `http status 401 Unauthorized`

```json
{
  "type": "about:blank",
  "title": "Unauthorized",
  "status": 401,
  "detail": "Bad credentials",
  "instance": "/sipress-app/auth/login",
  "description": "El usuario o la contraseña son incorrectos"
}
```

___

## <p style="color:khaki">POST</p> Registro de usuario

### Endpoint

```http request
/sipress-app/auth/registro
```

Este endpoint permite el registro de un nuevo usuario en la aplicación. La respuesta arroja un token que autoriza el
acceso a diferentes rutas protegidas.

### Request Body

- `email`: (text) Correo electrónico de un nuevo usuario,
  ejemplo: [nuevo.usuario@correo.com](https://mailto:nuevo.usuario@correo.com)

- `password`: (text) Contraseña para el nuevo usuario, ejemplo: *contraseña*

- `nombreCompleto`: (text) Nombre y apellido del nuevo usuario, ejemplo: *Nuevo Usuario*

```json
{
  "email": "string",
  "password": "string",
  "nombreCompleto": "string"
}
```

### Response

La respuesta para la petición puede ser visualizada en formato JSON y contiene la información del usuario registrado.

```json
{
  "id": 0,
  "nombreCompleto": "",
  "email": "",
  "password": "",
  "createdAt": "",
  "updatedAt": "",
  "role": {
    "id": 0,
    "nombre": "",
    "descripcion": "",
    "createdAt": "",
    "updatedAt": ""
  },
  "enabled": true,
  "accountNonExpired": true,
  "credentialsNonExpired": true,
  "username": "",
  "authorities": [
    {
      "authority": ""
    }
  ],
  "accountNonLocked": true
}

```

## Autorización `No se requiere autorización`

___

## Ejemplos

### Body Parameters

```json
{
  "email": "nuevo.usuario@correo.com",
  "password": "contraseña",
  "nombreCompleto": "Nuevo Usuario"
}
```

### Registro de usuario exitoso `http status 201 Created`

```json
{
  "id": 2,
  "nombreCompleto": "Nuevo Usuario",
  "email": "nuevo.usuario@correo.com",
  "password": "$2a$10$74J3ZR8DRUAykzjwC.QEDu8w5X4pH6jlxHpAR8SsJmvTiDMpoUA2G",
  "createdAt": "2024-08-19T11:15:19.798+00:00",
  "updatedAt": "2024-08-19T11:15:19.798+00:00",
  "role": {
    "id": 1,
    "nombre": "USER",
    "descripcion": "Rol de usuario",
    "createdAt": "2024-08-15T20:56:50.692+00:00",
    "updatedAt": "2024-08-15T20:56:50.692+00:00"
  },
  "enabled": true,
  "accountNonExpired": true,
  "credentialsNonExpired": true,
  "username": "nuevo.usuario@correo.com",
  "authorities": [
    {
      "authority": "ROLE_USER"
    }
  ],
  "accountNonLocked": true
}
```

### Registro de usuario con correo ya registrado `http status 400 Bad Request`

```json
{
  "type": "about:blank",
  "title": "Bad Request",
  "status": 400,
  "detail": "Email ya registrado",
  "instance": "/sipress-app/auth/registro",
  "description": "El correo electrónico ya se encuentra registrado en el sistema"
}
```

---

## <p style="color:palegreen">GET</p> Obtener perfil de usuario

### Endpoint

```http request
/sipress-app/usuarios/perfil
```

Este endpoint permite visualizar la información relacionada con un registro específico. La respuesta arroja un objeto
JSON con la información del usuario.

### Request

Para acceder a este endpoint es necesario incluir el token de autorización en el encabezado de la solicitud.

### Response

* Status: 200 OK
* Content-Type: application/json

### Estructura de la respuesta:

- **`id`** `(integer)`: El ID del usuario.

- **`nombreCompleto`** `(string)`: El nombre y el apellido del usuario.

- **`email`** `(string)`: El correo electrónico del usuario.

- **`password`** `(string)`: La contraseña del usuario.

- **`createdAt`** `(string)`: La fecha y hora de creación de la cuenta.

- **`updatedAt`** `(string)`: La fecha y hora de la última actualización.

- **`role`** `(object)`: El rol del usuario, que contiene `id`, `nombre`, `descripcion`, `createdAt`, y`updatedAt`.

- **`enabled`** `(boolean)`: Indica si la cuenta del usuario está habilitada.

- **`accountNonExpired`** `(boolean)`: Indica si la cuenta de usuario tiene un tiempo de caducidad.

- **`credentialsNonExpired`** `(boolean)`: Indica si las credenciales tienen un tiempo de caducidad.

- **`username`** `(string)`: El nombre de usuario, que en este caso corresponde al correo electrónico.

- **`authorities`** `(array)`: Un arreglo que muestra el tipo de rol y autorización de la cuenta del usuario.

- **`accountNonLocked`** `(boolean)`: Indica si la cuenta está bloqueada o no.

### Ejemplos de la estructura:

``` json
{
    "id": 0,
    "nombreCompleto": "",
    "email": "",
    "password": "",
    "createdAt": "",
    "updatedAt": "",
    "role": {
        "id": 0,
        "nombre": "",
        "descripcion": "",
        "createdAt": "",
        "updatedAt": ""
    },
    "enabled": true,
    "accountNonExpired": true,
    "credentialsNonExpired": true,
    "username": "",
    "authorities": [
        {
            "authority": ""
        }
    ],
    "accountNonLocked": true
}
```

## Autorización `Bearer token`

___

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "id": 1,
  "nombreCompleto": "Super Admin",
  "email": "super.admin@correo.com",
  "password": "$2a$10$C2gRCLxaKvp1C8c6talJiuqdXfOrI61UkOlZfRK8AcNzYZVpC303y",
  "createdAt": "2024-08-15T20:59:01.567+00:00",
  "updatedAt": "2024-08-15T20:59:01.567+00:00",
  "role": {
    "id": 3,
    "nombre": "SUPERADMIN",
    "descripcion": "Rol de super administrador",
    "createdAt": "2024-08-15T20:56:50.731+00:00",
    "updatedAt": "2024-08-15T20:56:50.731+00:00"
  },
  "enabled": true,
  "accountNonExpired": true,
  "credentialsNonExpired": true,
  "username": "super.admin@correo.com",
  "authorities": [
    {
      "authority": "ROLE_SUPERADMIN"
    }
  ],
  "accountNonLocked": true
}
```

### Respuesta con token inválido `http status 401 Unauthorized`

```json
{
  "type": "about:blank",
  "title": "Unauthorized",
  "status": 401,
  "detail": "Full authentication is required to access this resource",
  "instance": "/sipress-app/usuarios/perfil",
  "description": "No se ha proporcionado un token de autenticación válido"
}
```

---

## <p style="color:palegreen">GET</p> Obtener todos los usuarios

### Endpoint

```http request
/sipress-app/usuarios/todos
```

Este endpoint permite visualizar la información de todos los registros de usuarios en la aplicación.

### Request

Para acceder a este endpoint es necesario incluir el token de autorización en el encabezado de la solicitud.

### Response

La respuesta tiene una estructura tipo JSON que contiene los objetos tipo usuario con las siguientes propiedades:

- `id` (number): El identificado único para cada usuario.

- `nombreCompleto` (string): El nombre y el apellido del usuario.

- `email` (string): El correo electrónico del usuario.

- `password` (string): La contraseña de acceso.

- `createdAt` (string): La fecha y la hora en la que fue creada la cuenta.

- `updatedAt` (string): La fecha y la hora en la que se realizó la última actualización.

- `role` (object): Un objeto de tipo rol que contiene las siguientes propiedades:

    - `id` (number): El identificador único del rol.

    - `nombre` (string): El nombre del rol.

    - `descripcion` (string): La descripción del rol.

    - `createdAt` (string): La fecha y la hora en la que fue creado el rol.

    - `updatedAt` (string): La fecha y la hora en la que se actualizó por última vez el rol.

- `enabled` (boolean): Indica si la cuenta está habilitada.

- `accountNonExpired` (boolean): Indica si la cuenta de usuario tiene caducidad.

- `credentialsNonExpired` (boolean): Indica si las credenciales de autenticación no son caducas.

- `username` (string): El nombre de usuario, que en este caso correspondería al email.

- `authorities` (array): Un arreglo que contiene el tipo de autorización:

    - `authority` (string): Tipo de autorización del usuario.

- `accountNonLocked` (boolean): Indica si la cuenta está bloqueada o no.

### Ejemplo de respuesta:

``` json
[
  {
    "id": 0,
    "nombreCompleto": "",
    "email": "",
    "password": "",
    "createdAt": "",
    "updatedAt": "",
    "role": {
      "id": 0,
      "nombre": "",
      "descripcion": "",
      "createdAt": "",
      "updatedAt": ""
    },
    "enabled": true,
    "accountNonExpired": true,
    "credentialsNonExpired": true,
    "username": "",
    "authorities": [
      {
        "authority": ""
      }
    ],
    "accountNonLocked": true
  }
]
```

## Autorización *`Bearer token`

___

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
[
  {
    "id": 1,
    "nombreCompleto": "Super Admin",
    "email": "super.admin@correo.com",
    "password": "$2a$10$C2gRCLxaKvp1C8c6talJiuqdXfOrI61UkOlZfRK8AcNzYZVpC303y",
    "createdAt": "2024-08-15T20:59:01.567+00:00",
    "updatedAt": "2024-08-15T20:59:01.567+00:00",
    "role": {
      "id": 3,
      "nombre": "SUPERADMIN",
      "descripcion": "Rol de super administrador",
      "createdAt": "2024-08-15T20:56:50.731+00:00",
      "updatedAt": "2024-08-15T20:56:50.731+00:00"
    },
    "enabled": true,
    "accountNonExpired": true,
    "credentialsNonExpired": true,
    "username": "super.admin@correo.com",
    "authorities": [
      {
        "authority": "ROLE_SUPERADMIN"
      }
    ],
    "accountNonLocked": true
  },
  {
    "id": 2,
    "nombreCompleto": "Nuevo Usuario",
    "email": "nuevo.usuario@correo.com",
    "password": "$2a$10$74J3ZR8DRUAykzjwC.QEDu8w5X4pH6jlxHpAR8SsJmvTiDMpoUA2G",
    "createdAt": "2024-08-19T11:15:19.798+00:00",
    "updatedAt": "2024-08-19T11:15:19.798+00:00",
    "role": {
      "id": 1,
      "nombre": "USER",
      "descripcion": "Rol de usuario",
      "createdAt": "2024-08-15T20:56:50.692+00:00",
      "updatedAt": "2024-08-15T20:56:50.692+00:00"
    },
    "enabled": true,
    "accountNonExpired": true,
    "credentialsNonExpired": true,
    "username": "nuevo.usuario@correo.com",
    "authorities": [
      {
        "authority": "ROLE_USER"
      }
    ],
    "accountNonLocked": true
  }
]
```

### Respuesta con token inválido `http status 401 Unauthorized`

```json
{
  "type": "about:blank",
  "title": "Unauthorized",
  "status": 401,
  "detail": "Full authentication is required to access this resource",
  "instance": "/sipress-app/usuarios/todos",
  "description": "No se ha proporcionado un token de autenticación válido"
}
```

___

# Servicios de sipress-web-app

Los servicios de SIPRESS permiten la gestión de información relacionada con el personal, los pacientes, los
consultorios, las fórmulas médicas y otros aspectos clave de la atención sanitaria. A continuación, se describen los
servicios disponibles en la API de SIPRESS.

Las entidades que hacen parte de este sistema son:

- ### **`Institución`**

- ### **`Personal`**

- ### **`Paciente`**

- ### **`Fórmula`**

- ### **`Factura`**

- ### **`Eps`**

- ### **`Doctor`**

- ### **`Dependencia`**

- ### **`Consultorio`**

- ### **`Consulta`**

___

# Servicio de institución

Los endpoints `/instituciones` y `/instituciones/{id}` permiten la gestión de información relacionada con las
instituciones de salud. Estos servicios permiten la creación, actualización, eliminación y consulta de instituciones.

## Autorización *`Bearer token`

___

## <p style="color:lightgreen">GET</p> Obtener todos los registros

### Endpoint

```http request
/sipress-app/instituciones
```

Este endpoint permite visualizar la información de todos los registros de instituciones en la aplicación y que se
encuentran en la base de datos.

### Request

Esta petición requiere un token de tipo bearer. Solo los usuarios con privilegios de administrador pueden acceder al
recurso.

### Response

- Status: 200

- Content-Type: application/json

### Body Parameters

``` json
[
    {
        "idInstitucion": 0,
        "nombreInstitucion": "",
        "direccionInstitucion": "",
        "telefonoInstitucion": "",
        "codigoPostal": ""
    }
]
```

## Autorización *`Bearer token`

___

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
[
  {
    "idInstitucion": 101,
    "nombreInstitucion": "IPS Fundación Hospitalaria Alexander Fleming",
    "direccionInstitucion": "Calle Falsa 123",
    "telefonoInstitucion": "6045033892",
    "codigoPostal": "050040"
  },
  {
    "idInstitucion": 102,
    "nombreInstitucion": "Centro Médico Quahog",
    "direccionInstitucion": "Calle Evergreen 742",
    "telefonoInstitucion": "6063218277",
    "codigoPostal": "047025"
  },
  {
    "idInstitucion": 103,
    "nombreInstitucion": "Clínica Salutia IPS",
    "direccionInstitucion": "Avenida Laputa 4152",
    "telefonoInstitucion": "6012541133",
    "codigoPostal": "023658"
  },
  {
    "idInstitucion": 105,
    "nombreInstitucion": "Clínica Las Carmelitas",
    "direccionInstitucion": "Calle Diantres 369",
    "telefonoInstitucion": "6052478523",
    "codigoPostal": "0412369"
  }
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/instituciones",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/instituciones",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:lightgreen">GET</p> Obtener un registro

### Endpoint

```http request
/sipress-app/instituciones/{id}
```

Este endpoint permite recuperar la información de un solo registro de la entidad institución.

### Request

Para el acceso es necesario el inicio de sesión el cual otorga el token de tipo bearer.

### Response

La respuesta generada tiene una estructura tipo JSON con el siguiente formato:

``` json
{
    "idInstitucion": "number",
    "nombreInstitucion": "string",
    "direccionInstitucion": "string",
    "telefonoInstitucion": "string",
    "codigoPostal": "string"
}
```

## Autorización *`Bearer token`

___

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "idInstitucion": 101,
  "nombreInstitucion": "IPS Fundación Hospitalaria Alexander Fleming",
  "direccionInstitucion": "Calle Falsa 123",
  "telefonoInstitucion": "6045033892",
  "codigoPostal": "050040"
}
```

### Respuesta con ID no encontrado `http status 404 Not Found`

```json
{
  "type": "about:blank",
  "title": "Not Found",
  "status": 404,
  "detail": "Institución no encontrada",
  "instance": "/sipress-app/instituciones/104",
  "description": "No se encontró la institución con el ID 104"
}
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/instituciones/101",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/instituciones/101",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:khaki">POST</p> Agregar un nuevo registro

### Endpoint

```http request
/sipress-app/instituciones
```

Este endpoint permite añadir un nuevo registro de institución en la aplicación SIPRESS.

### Request Body

La petición debe estar en formato raw de tipo JSON, y debe incluir las siguientes propiedades:

- `idInstitucion` — Una cadena que almacena el número de identificación de la institución.

- `nombreInstitucion` — Una cadena que almacena el nombre de la institución.

- `direccionInstitucion` — Una cadena que almacena la dirección de la institución.

- `telefonoInstitucion` — Una cadena que almacena el número de teléfono de la institución.

- `codigoPostal` — Una cadena que almacena el código postal de la institución.

### Response

La respuesta se visualizará en un formato JSON de la siguiente manera:

``` json
{
  "idInstitucion": 0,
  "nombreInstitucion": "",
  "direccionInstitucion": "",
  "telefonoInstitucion": "",
  "codigoPostal": ""
}
```

## Autorización *`Bearer token`

___

## Ejemplos

### Body Parameters

```json
{
  "idInstitucion": "104",
  "nombreInstitucion": "Hospital El Refugio",
  "direccionInstitucion": "Avenida Kilombo 458",
  "telefonoInstitucion": "6047893696",
  "codigoPostal": "478521"
}
```

### Respuesta exitosa `http status 201 Created`

```json
{
  "idInstitucion": 104,
  "nombreInstitucion": "Hospital El Refugio",
  "direccionInstitucion": "Avenida Kilombo 458",
  "telefonoInstitucion": "6047893696",
  "codigoPostal": "478521"
}
```

### Respuesta con campos vacíos `http status 400 Bad Request`

```json
[
  "El nombre de la institución no puede estar vacío",
  "La dirección de la institución no puede estar vacía",
  "El teléfono de la institución no puede estar vacío",
  "El código postal de la institución no puede estar vacío"
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/instituciones",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/instituciones",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:cornflowerblue">PUT</p> Actualizar un registro

### Endpoint

```http request
/sipress-app/instituciones/{id}
```

Este endpoint actualiza un registro de la entidad Institución con el ID especificado.

### Request Body

La solicitud deberá especificar los datos existentes que se quieran actualizar usando un formato JSON con las siguientes
propiedades:

- `idInstitucion` — El número de identificación de la institución.

- `nombreInstitucion` — El nombre de la institución.

- `direccionInstitucion` — La dirección de la institución.

- `telefonoInstitucion` — El teléfono de la institución.

- `codigoPostal` — El código postal de la institución.

### Response

La respuesta de la petición mostrará la siguiente estructura:

``` json
{
  "idInstitucion": 0,
  "nombreInstitucion": "",
  "direccionInstitucion": "",
  "telefonoInstitucion": "",
  "codigoPostal": ""
}
```

### Importante

Hay que ser precavidos al utilizar este punto final, ya que sustituirá el registro existente por los valores
introducidos en el cuerpo de la solicitud.

## Autorización *`Bearer token`

___

## Ejemplos

### Body Parameters

```json
{
  "idInstitucion": "104",
  "nombreInstitucion": "Clínica El Refugio (dato actualizado)",
  "direccionInstitucion": "Calle Kilombo 954 (dato actualizado)",
  "telefonoInstitucion": "6014789563 (dato actualizado)",
  "codigoPostal": "478521"
}
```

### Respuesta exitosa `http status 200 OK`

```json
{
  "idInstitucion": 104,
  "nombreInstitucion": "Clínica El Refugio (dato actualizado)",
  "direccionInstitucion": "Calle Kilombo 954 (dato actualizado)",
  "telefonoInstitucion": "6014789563 (dato actualizado)",
  "codigoPostal": "478521"
}
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/instituciones/104",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/instituciones/104",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:lightsalmon">DELETE</p> Eliminar un registro

### Endpoint

```http request
/sipress-app/instituciones/{id}
```

Este endpoint borra un registro de la base de datos.

### Request Body

Esta solicitud no requiere un cuerpo, solo necesita especificar el ID del registro en la petición HTTP

### Response Body

La respuesta generada tendrá la siguiente estructura:

- `Eliminado`: Un valor booleano si la eliminación fue exitosa (`true`) o no (`false`).

### Response HTTP

- Status: 200

- Content-Type: application/json

### Ejemplo de respuesta:

``` json
{
    "Eliminado": boolean
}
```

## Autorización *`Bearer token`

___

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "Eliminado": true
}
```

### Respuesta con ID no encontrado `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "Institucion no encontrada con el id: 108",
  "instance": "/sipress-app/instituciones/108",
  "description": "Error interno del servidor"
}
```

___

# Servicio de personal

Los endpoints `/personalS` y `/personalS/{id}` permiten la gestión de información relacionada con el personal de salud.
Estos servicios permiten la creación, actualización, eliminación y consulta de personal.

## Autorización *`Bearer token`

___

## <p style="color:lightgreen">GET</p> Obtener todos los registros

### Endpoint

```http request
/sipress-app/personalS
```

Este endpoint permite recuperar la lista de todos los miembros del personal que pertenecen a las instituciones
registradas en la base de datos.

### Request

La solicitud requiere un token de tipo bearer. Solo los usuarios con privilegios de administrador pueden acceder al
recurso.

### Response

- Status: 200

- Content-Type: application/json

Ejemplos of response body:

``` json
[
    {
        "idPersonal": 0,
        "nombrePersonal": "",
        "apellidoPersonal": "",
        "telefonoPersonal": "",
        "emailPersonal": "",
        "dependencia": {
            "idDependencia": 0,
            "nombreDependencia": "",
            "institucion": {
                "idInstitucion": 0,
                "nombreInstitucion": "",
                "direccionInstitucion": "",
                "telefonoInstitucion": "",
                "codigoPostal": ""
            }
        }
    }
]
```

## Autorización *`Bearer token`

___

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
[
  {
    "idPersonal": 4101,
    "nombrePersonal": "Carla",
    "apellidoPersonal": "Posada",
    "telefonoPersonal": "3206639874",
    "emailPersonal": "carla.franco@mail.com",
    "dependencia": {
      "idDependencia": 1401,
      "nombreDependencia": "Servicios Generales",
      "institucion": {
        "idInstitucion": 101,
        "nombreInstitucion": "IPS Fundación Hospitalaria Alexander Fleming",
        "direccionInstitucion": "Calle Falsa 123",
        "telefonoInstitucion": "6045033892",
        "codigoPostal": "050040"
      }
    }
  },
  {
    "idPersonal": 4102,
    "nombrePersonal": "Pedro",
    "apellidoPersonal": "Gado",
    "telefonoPersonal": "3102015253",
    "emailPersonal": "pedro.gado@mail.com",
    "dependencia": {
      "idDependencia": 1402,
      "nombreDependencia": "Laboratorio Clínico",
      "institucion": {
        "idInstitucion": 102,
        "nombreInstitucion": "Centro Médico Quahog",
        "direccionInstitucion": "Calle Evergreen 742",
        "telefonoInstitucion": "6063218277",
        "codigoPostal": "047025"
      }
    }
  },
  {
    "idPersonal": 4103,
    "nombrePersonal": "Ana",
    "apellidoPersonal": "Arango",
    "telefonoPersonal": "3114716985",
    "emailPersonal": "ana.arango@mail.com",
    "dependencia": {
      "idDependencia": 1402,
      "nombreDependencia": "Laboratorio Clínico",
      "institucion": {
        "idInstitucion": 102,
        "nombreInstitucion": "Centro Médico Quahog",
        "direccionInstitucion": "Calle Evergreen 742",
        "telefonoInstitucion": "6063218277",
        "codigoPostal": "047025"
      }
    }
  },
  {
    "idPersonal": 4104,
    "nombrePersonal": "Juana",
    "apellidoPersonal": "Marín",
    "telefonoPersonal": "314258963",
    "emailPersonal": "juana@mail.com",
    "dependencia": {
      "idDependencia": 1401,
      "nombreDependencia": "Servicios Generales",
      "institucion": {
        "idInstitucion": 101,
        "nombreInstitucion": "IPS Fundación Hospitalaria Alexander Fleming",
        "direccionInstitucion": "Calle Falsa 123",
        "telefonoInstitucion": "6045033892",
        "codigoPostal": "050040"
      }
    }
  },
  {
    "idPersonal": 4105,
    "nombrePersonal": "Carlos",
    "apellidoPersonal": "López",
    "telefonoPersonal": "6014713696",
    "emailPersonal": "carlos@mail.com",
    "dependencia": {
      "idDependencia": 1402,
      "nombreDependencia": "Laboratorio Clínico",
      "institucion": {
        "idInstitucion": 102,
        "nombreInstitucion": "Centro Médico Quahog",
        "direccionInstitucion": "Calle Evergreen 742",
        "telefonoInstitucion": "6063218277",
        "codigoPostal": "047025"
      }
    }
  }
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/personalS",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/personalS",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:lightgreen">GET</p> Obtener un registro

### Endpoint

```http request
/sipress-app/personalS/{id}
```

Este endpoint permite recuperar la información de un solo registro de la entidad Personal, así como la información
relacionada con la entidad Institución.

### Request

Para acceder a este endpoint es necesario iniciar sesión para obtener el token de tipo bearer para la autorización.

### Response

La respuesta obtenida es de tipo JSON y tiene la siguiente estructura:

``` json
{
    "idPersonal": "number",
    "nombrePersonal": "string",
    "apellidoPersonal": "string",
    "telefonoPersonal": "string",
    "emailPersonal": "string",
    "dependencia": {
        "idDependencia": "number",
        "nombreDependencia": "string",
        "institucion": {
            "idInstitucion": "number",
            "nombreInstitucion": "string",
            "direccionInstitucion": "string",
            "telefonoInstitucion": "string",
            "codigoPostal": "string"
        }
    }
}
```

## Autorización *`Bearer token`

___

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "idPersonal": 4102,
  "nombrePersonal": "Pedro",
  "apellidoPersonal": "Gado",
  "telefonoPersonal": "3102015253",
  "emailPersonal": "pedro.gado@mail.com",
  "dependencia": {
    "idDependencia": 1402,
    "nombreDependencia": "Laboratorio Clínico",
    "institucion": {
      "idInstitucion": 102,
      "nombreInstitucion": "Centro Médico Quahog",
      "direccionInstitucion": "Calle Evergreen 742",
      "telefonoInstitucion": "6063218277",
      "codigoPostal": "047025"
    }
  }
}
```

### Respuesta con ID no encontrado `http status 404 Not Found`

```json
{
  "type": "about:blank",
  "title": "Not Found",
  "status": 404,
  "detail": "Personal no encontrado",
  "instance": "/sipress-app/personalS/4106",
  "description": "No se encontró el personal con el ID 4106"
}
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/personalS/4102",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/personalS/4102",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:khaki">POST</p> Agregar un nuevo registro

### Endpoint

```http request
/sipress-app/personalS
```

Este endpoint es usado para agregar un nuevo objeto de la entidad Personal.

### Request Body

Para hacer efectiva la solicitud, es necesario usar las siguientes propiedades en una estructura de tipo JSON:

- `idPersonal` (number): El identificador único del Personal.

- `nombrePersonal` (string): El nombre del individuo.

- `apellidoPersonal` (string): El apellido del individuo.

- `telefonoPersonal` (string): El número telefónico.

- `emailPersonal` (string): El correo electrónico.

- `dependencia` (object): Un objeto de tipo Dependencia a la que pertenece el individuo:

    - `idDependencia` (number): El identificador único de la dependencia.

    - `institucion` (object): Un objeto de tipo Institución a la que pertenece la Dependencia:

        - `idInstitucion` (number): El identificado único de la institución.

        - `nombreInstitucion` (string): El nombre de la institución.

        - `direccionInstitucion` (string): La dirección de la institución.

        - `telefonoInstitucion` (string): El número telefónico de la institución.

        - `codigoPostal` (string): El código postal de la institución.

### Response

La respuesta tendrá las mismas propiedades mencionadas, siguiendo una estructura tipo JSON:

### Ejemplo de respuesta:

``` json
{
  "idPersonal": 0,
  "nombrePersonal": "",
  "apellidoPersonal": "",
  "telefonoPersonal": "",
  "emailPersonal": "",
  "dependencia": {
    "idDependencia": 0,
    "nombreDependencia": "",
    "institucion": {
      "idInstitucion": 0,
      "nombreInstitucion": "",
      "direccionInstitucion": "",
      "telefonoInstitucion": "",
      "codigoPostal": ""
    }
  }
}
```

## Autorización *`Bearer token`

___

## Ejemplos

### Body Parameters

```json
{
  "idPersonal": 4106,
  "nombrePersonal": "Juan",
  "apellidoPersonal": "Tello",
  "telefonoPersonal": "3254789632",
  "emailPersonal": "juan.tello@correo.com",
  "dependencia": {
    "idDependencia": 1402,
    "institucion": {
      "idInstitucion": 102
    }
  }
}
```

### Respuesta exitosa `http status 201 Created`

```json
{
  "idPersonal": 4106,
  "nombrePersonal": "Juan",
  "apellidoPersonal": "Tello",
  "telefonoPersonal": "3254789632",
  "emailPersonal": "juan.tello@correo.com",
  "dependencia": {
    "idDependencia": 1402,
    "nombreDependencia": "Laboratorio Clínico",
    "institucion": {
      "idInstitucion": 102,
      "nombreInstitucion": "Centro Médico Quahog",
      "direccionInstitucion": "Calle Evergreen 742",
      "telefonoInstitucion": "6063218277",
      "codigoPostal": "047025"
    }
  }
}
```

### Respuesta con campos vacíos `http status 400 Bad Request`

```json
[
  "El ID del personal no puede estar vacío",
  "El nombre del personal no puede estar vacío",
  "El apellido del personal no puede estar vacío",
  "El teléfono del personal no puede estar vacío",
  "El correo electrónico del personal no puede estar vacío"
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/personalS",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/personalS",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:cornflowerblue">PUT</p> Actualizar un registro

### Endpoint

```http request
/sipress-app/personalS/{id}
```

Este endpoint permite la actualización de un registro de la entidad Personal.

### Request Body

Para hacer efectiva la solicitud, es necesario usar las siguientes propiedades en una estructura de tipo JSON:

- `idPersonal` (number): El identificador único del Personal.

- `nombrePersonal` (string): El nombre del individuo.

- `apellidoPersonal` (string): El apellido del individuo.

- `telefonoPersonal` (string): El número telefónico.

- `emailPersonal` (string): El correo electrónico.

- `dependencia` (object): Un objeto de tipo Dependencia a la que pertenece el individuo:

    - `idDependencia` (number): El identificador único de la dependencia.

    - `institucion` (object): Un objeto de tipo Institución a la que pertenece la Dependencia:

        - `idInstitucion` (number): El identificado único de la institución.

### Response

La respuesta tendrá las siguientes propiedades mencionadas, siguiendo una estructura tipo JSON:

``` json
{
  "idPersonal": 0,
  "nombrePersonal": "",
  "apellidoPersonal": "",
  "telefonoPersonal": "",
  "emailPersonal": "",
  "dependencia": {
    "idDependencia": 0,
    "nombreDependencia": "",
    "institucion": {
      "idInstitucion": 0,
      "nombreInstitucion": "",
      "direccionInstitucion": "",
      "telefonoInstitucion": "",
      "codigoPostal": ""
    }
  }
}

 ```

### Importante

Se debe ser precavido con el uso de este endpoint, ya que se reemplazarán los valores de un registro existente.

## Autorización *`Bearer token`

___

## Ejemplos

### Body Parameters

```json
    {
  "idPersonal": 4104,
  "nombrePersonal": "Juana Mercedes (dato actualizado)",
  "apellidoPersonal": "Díaz (dato actualizado)",
  "telefonoPersonal": "314258963",
  "emailPersonal": "correo@actualizado.com",
  "dependencia": {
    "idDependencia": 1401,
    "nombreDependencia": "Servicios Generales",
    "institucion": {
      "idInstitucion": 101,
      "nombreInstitucion": "IPS Fundación Hospitalaria Alexander Fleming",
      "direccionInstitucion": "Calle Falsa 123",
      "telefonoInstitucion": "6045033892",
      "codigoPostal": "050040"
    }
  }
}
```

### Respuesta exitosa `http status 200 OK`

```json
{
  "idPersonal": 4104,
  "nombrePersonal": "Juana Mercedes (dato actualizado)",
  "apellidoPersonal": "Díaz (dato actualizado)",
  "telefonoPersonal": "314258963",
  "emailPersonal": "correo@actualizado.com",
  "dependencia": {
    "idDependencia": 1401,
    "nombreDependencia": "Servicios Generales",
    "institucion": {
      "idInstitucion": 101,
      "nombreInstitucion": "IPS Fundación Hospitalaria Alexander Fleming",
      "direccionInstitucion": "Calle Falsa 123",
      "telefonoInstitucion": "6045033892",
      "codigoPostal": "050040"
    }
  }
}
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/personalS/4104",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/personalS/4104",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:lightsalmon">DELETE</p> Eliminar un registro

### Endpoint

```http request
/sipress-app/personalS/{id}
```

Este endpoint permite la eliminación de un registro de la base de datos. El ID del registro debe ser especificado en la
petición.

### Request

Esta solicitud no requiere un cuerpo, solo necesita especificar el ID del registro en la petición HTTP

### Response Body

La respuesta generada tendrá la siguiente estructura:

- `Eliminado`: Un valor booleano si la eliminación fue exitosa (`true`) o no (`false`).

### Response HTTP

- Status: 200

- Content-Type: application/json

### Ejemplo de respuesta:

``` json
{
    "Eliminado": boolean
}
```

## Autorización *`Bearer token`

___

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "Eliminado": true
}
```

### Respuesta con ID no encontrado `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "Personal no encontrado con el id: 4107",
  "instance": "/sipress-app/personalS/4107",
  "description": "Error interno del servidor"
}
```

___

## Servicio de paciente

Los endpoints `/pacientes` y `/pacientes/{id}` permiten la gestión de información relacionada con los pacientes. Estos
servicios permiten la creación, actualización, eliminación y consulta de pacientes.

## Autorización *`Bearer token`

___

## <p style="color:lightgreen">GET</p> Obtener todos los registros

### Endpoint

```http request
/sipress-app/pacientes
```

Este endpoint permite recuperar la lista de todos los pacientes registrados en la base de datos.

### Request

La solicitud requiere un token de tipo bearer. Solo los usuarios con privilegios de administrador pueden acceder al
recurso.

### Response

- Status: 200

- Content-Type: application/json

### Ejemplo *response body*:

``` json
[
    {
        "idPaciente": 0,
        "nombrePaciente": "",
        "apellidoPaciente": "",
        "direccionPaciente": "",
        "telefonoPaciente": "",
        "emailPaciente": "",
        "eps": {
            "idEps": 0,
            "nombreEps": "",
            "telefonoEps": "",
            "emailEps": ""
        }
    }
]
```

## Autorización *`Bearer token`

___

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
[
  {
    "idPaciente": 1,
    "nombrePaciente": "Sara",
    "apellidoPaciente": "Tabares",
    "direccionPaciente": "Avenida Las Flores 4514",
    "telefonoPaciente": "3144421001",
    "emailPaciente": "sara.tabares@mail.com",
    "eps": {
      "idEps": 241,
      "nombreEps": "Cleptosalud",
      "telefonoEps": "3194713365",
      "emailEps": "cleptosalud.eps@mail.com"
    }
  },
  {
    "idPaciente": 2,
    "nombrePaciente": "Diego",
    "apellidoPaciente": "Norrea",
    "direccionPaciente": "Barrio Nueva Vista 97-458",
    "telefonoPaciente": "3295478522",
    "emailPaciente": "diego.norrea@mail.com",
    "eps": {
      "idEps": 242,
      "nombreEps": "Farsitas",
      "telefonoEps": "3271475896",
      "emailEps": "farsitas.eps@mail.com"
    }
  },
  {
    "idPaciente": 3,
    "nombrePaciente": "Camila",
    "apellidoPaciente": "Vidal",
    "direccionPaciente": "Urbanización Los Bloques 582",
    "telefonoPaciente": "3148569632",
    "emailPaciente": "camila.vidal@mail.com",
    "eps": {
      "idEps": 242,
      "nombreEps": "Farsitas",
      "telefonoEps": "3271475896",
      "emailEps": "farsitas.eps@mail.com"
    }
  }
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/pacientes",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/pacientes",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:lightgreen">GET</p> Obtener un registro

### Endpoint

```http request
/sipress-app/pacientes/{id}
```

Este endpoint permite recuperar un registro de la entidad Paciente, así como el objeto de la entidad Eps relacionado.

### Request

Para acceder a este endpoint es necesario iniciar sesión para obtener el token de tipo bearer para la autorización.
Además, se debe especificar el ID del registro que se quiere recuperar en la petición HTTP.

### Response

La respuesta obtenida es de tipo JSON y tiene la siguiente estructura:

``` json
{
    "idPaciente": "number",
    "nombrePaciente": "string",
    "apellidoPaciente": "string",
    "direccionPaciente": "string",
    "telefonoPaciente": "string",
    "emailPaciente": "string",
    "eps": {
        "idEps": "number",
        "nombreEps": "string",
        "telefonoEps": "string",
        "emailEps": "string"
    }
}
```

## Autorización *`Bearer token`

___

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "idPaciente": 3,
  "nombrePaciente": "Camila",
  "apellidoPaciente": "Vidal",
  "direccionPaciente": "Urbanización Los Bloques 582",
  "telefonoPaciente": "3148569632",
  "emailPaciente": "camila.vidal@mail.com",
  "eps": {
    "idEps": 242,
    "nombreEps": "Farsitas",
    "telefonoEps": "3271475896",
    "emailEps": "farsitas.eps@mail.com"
  }
}
```

### Respuesta con ID no encontrado `http status 404 Not Found`

```json
{
  "type": "about:blank",
  "title": "Not Found",
  "status": 404,
  "detail": "Paciente no encontrado",
  "instance": "/sipress-app/pacientes/4",
  "description": "No se encontró el paciente con el ID 4"
}
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/pacientes/3",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/pacientes/3",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:khaki">POST</p> Agregar un nuevo registro

### Endpoint

```http request
/sipress-app/pacientes
```

Este endpoint permite añadir un nuevo registro de la entidad Paciente.

### Autorización

Para acceder a este endpoint es necesario iniciar sesión para obtener el token de tipo bearer para la autorización.

### Request Body

La solicitud debe incluir las siguientes propiedades en una estructura en formato JSON:

- `idPaciente` — El identificador del registro a actualizar.

- `nombrePaciente` — El nombre del paciente.

- `apellidoPaciente` — El apellido del paciente.

- `direccionPaciente` — La dirección del paciente.

- `telefonoPaciente` — El número telefónico del paciente

- `emailPaciente` — El correo electrónico del paciente.

- `eps` — El objeto de la entidad Eps relacionado con el registro Paciente:

    - `idEps` — El identificador único de la EPS.

    - `nombreEps` — The name of the EPS.

    - `telefonoEps` — The phone number of the EPS.

    - `emailEps` — The email address of the EPS.

### Response

La respuesta mostrará las propiedades mencionadas en una estructura de tipo JSON:

### Ejemplo *response body*:

``` json
{
  "idPaciente": 0,
  "nombrePaciente": "",
  "apellidoPaciente": "",
  "direccionPaciente": "",
  "telefonoPaciente": "",
  "emailPaciente": "",
  "eps": {
    "idEps": 0,
    "nombreEps": "",
    "telefonoEps": "",
    "emailEps": ""
  }
}
```

## Autorización *`Bearer token`

___

## Ejemplos

### Body Parameters

```json
{
  "idPaciente": 4,
  "nombrePaciente": "Luis",
  "apellidoPaciente": "Gómez",
  "direccionPaciente": "Carrera 45 # 23-14",
  "telefonoPaciente": "3142589632",
  "emailPaciente": "luis.gomez@correo.com",
  "eps": {
    "idEps": 242
  }
}
```

### Respuesta exitosa `http status 200 OK`

```json
{
  "idPaciente": 4,
  "nombrePaciente": "Luis",
  "apellidoPaciente": "Gómez",
  "direccionPaciente": "Carrera 45 # 23-14",
  "telefonoPaciente": "3142589632",
  "emailPaciente": "luis.gomez@correo.com",
  "eps": {
    "idEps": 242,
    "nombreEps": "Farsitas",
    "telefonoEps": "3271475896",
    "emailEps": "farsitas.eps@correo.com"
  }
}
```

### Respuesta con campos vacíos `http status 400 Bad Request`

```json
[
  "El ID del paciente no puede estar vacío",
  "El nombre del paciente no puede estar vacío",
  "El apellido del paciente no puede estar vacío",
  "La dirección del paciente no puede estar vacía",
  "El teléfono del paciente no puede estar vacío",
  "El correo electrónico del paciente no puede estar vacío"
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/pacientes",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/pacientes",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:cornflowerblue">PUT</p> Actualizar un registro

### Endpoint

```http request
/sipress-app/pacientes/{id}
```

Este endpoint permite la actualización de un registro de la entidad Paciente con el ID especificado en la petición HTTP.

### Request Body

La solicitud debe incluir la información existente del registro especificado en una estructura en formato JSON con las
siguientes propiedades:

- `idPaciente` — El identificador del registro a actualizar.

- `nombrePaciente` — El nombre del paciente.

- `apellidoPaciente` — El apellido del paciente.

- `direccionPaciente` — La dirección del paciente.

- `telefonoPaciente` — El número telefónico del paciente

- `emailPaciente` — El correo electrónico del paciente.

- `eps` — El objeto de la entidad Eps relacionado con el registro Paciente:

    - `idEps` — El identificador único de la EPS.

    - `nombreEps` — The name of the EPS.

    - `telefonoEps` — The phone number of the EPS.

    - `emailEps` — The email address of the EPS.

### Response

La respuesta de la petición mostrará la siguiente estructura como un formato de tipo JSON:

``` json
{
  "idPaciente": 0,
  "nombrePaciente": "",
  "apellidoPaciente": "",
  "direccionPaciente": "",
  "telefonoPaciente": "",
  "emailPaciente": "",
  "eps": {
    "idEps": 0,
    "nombreEps": null,
    "telefonoEps": null,
    "emailEps": null
  }
}

 ```

### Importante

Se debe tener en cuenta que el suo de este endpoint reemplazara la información existente con la que se especifique en la
nueva solicitud.

## Autorización *`Bearer token`

___

## Ejemplos

### Body Parameters

```json
{
  "idPaciente": 1,
  "nombrePaciente": "Sara Fernanda (dato actualizado)",
  "apellidoPaciente": "Tabares",
  "direccionPaciente": "Dirección Actualizada 4514",
  "telefonoPaciente": "3144421001",
  "emailPaciente": "correo.actualizado@mail.com",
  "eps": {
    "idEps": 243
  }
}
```

### Respuesta exitosa `http status 200 OK`

```json
{
  "idPaciente": 1,
  "nombrePaciente": "Sara Fernanda (dato actualizado)",
  "apellidoPaciente": "Tabares",
  "direccionPaciente": "Dirección Actualizada 4514",
  "telefonoPaciente": "3144421001",
  "emailPaciente": "correo.actualizado@mail.com",
  "eps": {
    "idEps": 243,
    "nombreEps": null,
    "telefonoEps": null,
    "emailEps": null
  }
}
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/pacientes/1",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/pacientes/1",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:lightsalmon">DELETE</p> Eliminar un registro

### Endpoint

```http request
/sipress-app/pacientes/{id}
```

Este endpoint permite la eliminación de un registro de la base de datos. El ID del registro debe ser especificado en la
petición.

### Request

Esta solicitud no requiere un cuerpo, solo necesita especificar el ID del registro en la petición HTTP

### Response Body

La respuesta generada tendrá la siguiente estructura:

- `Eliminado`: Un valor booleano si la eliminación fue exitosa (`true`) o no (`false`).

### Response HTTP

- Status: 200
- Content-Type: application/json

### Ejemplo de respuesta:

``` json
{
    "Eliminado": boolean
}
```

### Importante

Hay que tener en cuenta que la eliminación de un registro de la entidad Paciente, eliminará automáticamente los
registros relacionados con otras entidades tales como Factura, Consultorio, Consulta y Fórmula.

## Autorización *`Bearer token`

___

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "Eliminado": true
}
```

### Respuesta con ID no encontrado `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "Paciente no encontrado con el id: 5",
  "instance": "/sipress-app/pacientes/5",
  "description": "Error interno del servidor"
}
```

---

# Servicio de fórmula

Los endpoints `/formulas` y `/formulas/{id}` permiten la gestión de información relacionada con las fórmulas médicas.
Estos servicios permiten la creación, actualización, eliminación y consulta de fórmulas, así como los datos específicos
relacionados con un paciente.

## Autorización *`Bearer token`

---

## <p style="color:lightgreen">GET</p> Obtener todos los registros

### Endpoint

```http request
/sipress-app/formulas
```

Este endpoint permite recuperar todos los registros de la entidad Fórmula.

### Request

La petición requiere el inicio de sesión, para obtener un token de tipo bearer que permite la autorización de
recuperación.

### Response

- Status: 200

- Content-Type: application/json

### Ejemplo *response body*:

``` json
{
    "numeroFormula": integer,
    "nombreMedicacion": "string",
    "fechaMedicacion": "date",
    "costoMedicacion": Double,
    "paciente": {
        "idPaciente": integer,
        "nombrePaciente": "string",
        "apellidoPaciente": "string",
        "direccionPaciente": "string",
        "telefonoPaciente": "string",
        "emailPaciente": "string",
        "eps": {
            "idEps": integer,
            "nombreEps": "string",
            "telefonoEps": "string",
            "emailEps": "string"
        }
    }
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
[
  {
    "numeroFormula": 80001,
    "nombreMedicacion": "Metronidazol",
    "fechaMedicacion": "2024-07-05",
    "costoMedicacion": 20000,
    "paciente": {
      "idPaciente": 1,
      "nombrePaciente": "Sara Fernanda (dato actualizado)",
      "apellidoPaciente": "Tabares",
      "direccionPaciente": "Dirección Actualizada 4514",
      "telefonoPaciente": "3144421001",
      "emailPaciente": "correo.actualizado@mail.com",
      "eps": {
        "idEps": 243,
        "nombreEps": "Medicalsalud",
        "telefonoEps": "3226987543",
        "emailEps": "medisalud.eps@mail.com"
      }
    }
  },
  {
    "numeroFormula": 80002,
    "nombreMedicacion": "Ibuprofeno",
    "fechaMedicacion": "2024-07-12",
    "costoMedicacion": 15000,
    "paciente": {
      "idPaciente": 2,
      "nombrePaciente": "Diego",
      "apellidoPaciente": "Norrea",
      "direccionPaciente": "Barrio Nueva Vista 97-458",
      "telefonoPaciente": "3295478522",
      "emailPaciente": "diego.norrea@mail.com",
      "eps": {
        "idEps": 242,
        "nombreEps": "Farsitas",
        "telefonoEps": "3271475896",
        "emailEps": "farsitas.eps@mail.com"
      }
    }
  }
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/formulas",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/formulas",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:lightgreen">GET</p> Obtener un registro

### Endpoint

```http request
/sipress-app/formulas/{id}
```

Este endpoint permite la recuperación de un solo registro de la entidad Fórmula de la base de datos.

#### Request

Para el acceso a este endpoint es necesario iniciar sesión y obtener el token de tipo bearer. También es necesario
especificar el ID del registro en la petición HTTP.

#### Response

La respuesta mostrará la información con la siguiente estructura de tipo JSON:

``` json
{
    "numeroFormula": 0,
    "nombreMedicacion": "",
    "fechaMedicacion": "",
    "costoMedicacion": 0,
    "paciente": {
        "idPaciente": 0,
        "nombrePaciente": "",
        "apellidoPaciente": "",
        "direccionPaciente": "",
        "telefonoPaciente": "",
        "emailPaciente": "",
        "eps": {
            "idEps": 0,
            "nombreEps": "",
            "telefonoEps": "",
            "emailEps": ""
        }
    }
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "numeroFormula": 80002,
  "nombreMedicacion": "Ibuprofeno",
  "fechaMedicacion": "2024-07-12",
  "costoMedicacion": 15000,
  "paciente": {
    "idPaciente": 2,
    "nombrePaciente": "Diego",
    "apellidoPaciente": "Norrea",
    "direccionPaciente": "Barrio Nueva Vista 97-458",
    "telefonoPaciente": "3295478522",
    "emailPaciente": "diego.norrea@mail.com",
    "eps": {
      "idEps": 242,
      "nombreEps": "Farsitas",
      "telefonoEps": "3271475896",
      "emailEps": "farsitas.eps@mail.com"
    }
  }
}
```

### Respuesta con ID no encontrado `http status 404 Not Found`

```json
{
  "type": "about:blank",
  "title": "Not Found",
  "status": 404,
  "detail": "Fórmula no encontrada",
  "instance": "/sipress-app/formulas/80003",
  "description": "No se encontró la fórmula con el ID 80003"
}
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/formulas/80002",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/formulas/80002",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:khaki">POST</p> Agregar un nuevo registro

### Endpoint

```http request
/sipress-app/formulas
```

Este endpoint permite agregar un nuevo registro de la entidad Fórmula, y relacionarla con un objeto específico de la
entidad Paciente.

### Request

Para ejecutar la solicitud es necesario el inicio de sesión para obtener el token de tipo bearer, e incluir una
solicitud que contenga las siguientes propiedades:

### Request Body

- `numeroFormula` (number): El número de identificación de la fórmula médica.

- `nombreMedicacion` (string): El nombre de la medicación.

- `fechaMedicacion` (string): La fecha en la que se expide la fórmula.

- `costoMedicacion` (number): El costo de la medicación.

- `paciente` (object): Se asocia un objeto Paciente con las siguientes propiedades:

    - `idPaciente` (number): El ID del paciente que se quiera relacionar.

    - `eps` (object): El objeto de tipo Eps relacionado con el Paciente especificado antes:

        - `idEps` (number): El ID de la Eps.

### Response

La respuesta se mostrará en un formato de tipo JSON con la siguiente estructura:

``` json
{
  "numeroFormula": 0,
  "nombreMedicacion": "",
  "fechaMedicacion": "",
  "costoMedicacion": 0,
  "paciente": {
    "idPaciente": 0,
    "eps": {
      "idEps": 0
    }
  }
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Body Parameters

```json
{
  "numeroFormula": 80003,
  "nombreMedicacion": "Paracetamol",
  "fechaMedicacion": "2024-07-15",
  "costoMedicacion": 10000,
  "paciente": {
    "idPaciente": 3,
    "eps": {
      "idEps": 242
    }
  }
}
```

### Respuesta exitosa `http status 200 OK`

```json
{
  "numeroFormula": 80003,
  "nombreMedicacion": "Medicamento agregado",
  "fechaMedicacion": "2024-08-19",
  "costoMedicacion": 55000,
  "paciente": {
    "idPaciente": 4,
    "nombrePaciente": "Nuevo",
    "apellidoPaciente": "Paciente",
    "direccionPaciente": "Dirección nuevo paciente",
    "telefonoPaciente": "123456789",
    "emailPaciente": "nuevo.paciente@correo.com",
    "eps": {
      "idEps": 241,
      "nombreEps": "Cleptosalud",
      "telefonoEps": "3194713365",
      "emailEps": "cleptosalud.eps@mail.com"
    }
  }
}
```

### Respuesta con campos vacíos `http status 400 Bad Request`

```json
[
  "El número de fórmula no puede estar vacío",
  "El nombre de la medicación no puede estar vacío",
  "La fecha de la medicación no puede estar vacía",
  "El costo de la medicación no puede estar vacío",
  "El ID del paciente no puede estar vacío"
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/formulas",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/formulas",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:cornflowerblue">PUT</p> Actualizar un registro

Este endpoint permite la actualización de un registro específico de la entidad Fórmula.

### Request Body

Para ejecutar la solicitud es necesario el inicio de sesión para obtener el token de tipo bearer y especificar en la
petición HTTP el ID del registro que se quiere actualizar, e incluir una solicitud que contenga las siguientes
propiedades:

- `numeroFormula` (number): El número de identificación de la fórmula médica.

- `nombreMedicacion` (string): El nombre de la medicación.

- `fechaMedicacion` (string): La fecha en la que se expide la fórmula.

- `costoMedicacion` (number): El costo de la medicación.

- `paciente` (object): Se asocia un objeto Paciente con las siguientes propiedades:

    - `idPaciente` (number): El ID del paciente que se quiera relacionar.

    - `eps` (object): El objeto de tipo Eps relacionado con el Paciente especificado antes:

        - `idEps` (number): El ID de la Eps.

### Response

La respuesta se mostrará en un formato de tipo JSON con la siguiente estructura:

``` json
{
  "numeroFormula": 0,
  "nombreMedicacion": "",
  "fechaMedicacion": "",
  "costoMedicacion": 0,
  "paciente": {
    "idPaciente": 0,
    "nombrePaciente": null,
    "apellidoPaciente": null,
    "direccionPaciente": null,
    "telefonoPaciente": null,
    "emailPaciente": null,
    "eps": {
      "idEps": 0,
      "nombreEps": null,
      "telefonoEps": null,
      "emailEps": null
    }
  }
}
```

### Importante

Se debe tener en cuenta que el uso de este endpoint reemplazará la información existente con la que se especifique en la
nueva solicitud.

## Autorización *`Bearer token`

---

## Ejemplos

### Body Parameters

```json
{
  "numeroFormula": 80003,
  "nombreMedicacion": "Paracetamol",
  "fechaMedicacion": "2024-07-15",
  "costoMedicacion": 10000,
  "paciente": {
    "idPaciente": 3,
    "eps": {
      "idEps": 242
    }
  }
}
```

### Respuesta exitosa `http status 200 OK`

```json
{
  "numeroFormula": 80003,
  "nombreMedicacion": "Paracetamol",
  "fechaMedicacion": "2024-07-15T00:00:00.000+00:00",
  "costoMedicacion": 10000,
  "paciente": {
    "idPaciente": 3,
    "nombrePaciente": "Camila",
    "apellidoPaciente": "Vidal",
    "direccionPaciente": "Urbanización Los Bloques 582",
    "telefonoPaciente": "3148569632",
    "emailPaciente": "camila.vidal@mail.com",
    "eps": {
      "idEps": 242,
      "nombreEps": "Farsitas",
      "telefonoEps": "3271475896",
      "emailEps": "farsitas.eps@mail.com"
    }
  }
}
```

### Respuesta con campos vacíos `http status 400 Bad Request`

```json
[
  "El número de fórmula no puede estar vacío",
  "El nombre de la medicación no puede estar vacío",
  "La fecha de la medicación no puede estar vacía",
  "El costo de la medicación no puede estar vacío",
  "El ID del paciente no puede estar vacío"
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/formulas/80003",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/formulas/80003",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:lightsalmon">DELETE</p> Eliminar un registro

### Endpoint

```http request
/sipress-app/formulas/{id}
```

Este endpoint permite la eliminación de un registro de la entidad Fórmula. El ID del registro debe ser especificado en
la petición.

### Request

Esta solicitud no requiere un cuerpo, solo necesita especificar el ID del registro en la petición HTTP

### Response Body

La respuesta generada tendrá la siguiente estructura:

- `Eliminado`: Un valor booleano si la eliminación fue exitosa (`true`) o no (`false`).

### Response HTTP

- Status: 200
- Content-Type: application/json

### Ejemplo de respuesta:

``` json
{
    "Eliminado": boolean
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "Eliminado": true
}
```

### Respuesta con ID no encontrado `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "Fórmula no encontrada con el id: 80004",
  "instance": "/sipress-app/formulas/80004",
  "description": "Error interno del servidor"
}
```

---

# Servicio de factura

Los endpoints `/facturas` y `/facturas/{id}` permiten la gestión de información relacionada con las facturas médicas.
Estos servicios permiten la creación, actualización, eliminación y consulta de facturas, así como los datos específicos
relacionados con loa costos de las consultas y fórmulas médicas.

## Autorización *`Bearer token`

---

## <p style="color:lightgreen">GET</p> Obtener todos los registros

### Endpoint

```http request
/sipress-app/facturas
```

Este endpoint permite la recuperación de todos los objetos de tipo Factura registrados en la base de datos.

### Request

Es necesario el inicio de sesión y el uso del token de tipo bearer que se genera.

### Response

- Status: 200

- Content-Type: application/json

### Ejemplo *response body*:

``` json
[
    {
        "numeroFactura": 0,
        "descripcionServicio": "",
        "valor": 0,
        "total": 0,
        "paciente": {
            "idPaciente": 0,
            "nombrePaciente": "",
            "apellidoPaciente": "",
            "direccionPaciente": "",
            "telefonoPaciente": "",
            "emailPaciente": "",
            "eps": {
                "idEps": 0,
                "nombreEps": "",
                "telefonoEps": "",
                "emailEps": ""
            }
        }
    }
]
```

## Autorización *`Bearer token`

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
[
  {
    "numeroFactura": 50001,
    "descripcionServicio": "Valoración médica general",
    "valor": 100000,
    "total": 125000,
    "paciente": {
      "idPaciente": 1,
      "nombrePaciente": "Sara Fernanda (dato actualizado)",
      "apellidoPaciente": "Tabares",
      "direccionPaciente": "Dirección Actualizada 4514",
      "telefonoPaciente": "3144421001",
      "emailPaciente": "correo.actualizado@mail.com",
      "eps": {
        "idEps": 243,
        "nombreEps": "Medicalsalud",
        "telefonoEps": "3226987543",
        "emailEps": "medisalud.eps@mail.com"
      }
    }
  },
  {
    "numeroFactura": 50002,
    "descripcionServicio": "Tomografía",
    "valor": 300000,
    "total": 450000,
    "paciente": {
      "idPaciente": 2,
      "nombrePaciente": "Diego",
      "apellidoPaciente": "Norrea",
      "direccionPaciente": "Barrio Nueva Vista 97-458",
      "telefonoPaciente": "3295478522",
      "emailPaciente": "diego.norrea@mail.com",
      "eps": {
        "idEps": 242,
        "nombreEps": "Farsitas",
        "telefonoEps": "3271475896",
        "emailEps": "farsitas.eps@mail.com"
      }
    }
  }
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/facturas",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/facturas",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:lightgreen">GET</p> Obtener un registro

### Endpoint

```http request
/sipress-app/facturas/{id}
```

Este endpoint permite la recuperación de un solo registro de la entidad Factura de la base de datos.

### Request

Para el acceso a este endpoint es necesario iniciar sesión y obtener el token de tipo bearer. También es necesario
especificar el ID del registro en la petición HTTP.

### Response

La respuesta mostrará la información con la siguiente estructura de tipo JSON:

``` json
{
    "numeroFactura": 0,
    "descripcionServicio": "",
    "valor": 0,
    "total": 0,
    "paciente": {
        "idPaciente": 0,
        "nombrePaciente": "",
        "apellidoPaciente": "",
        "direccionPaciente": "",
        "telefonoPaciente": "",
        "emailPaciente": "",
        "eps": {
            "idEps": 0,
            "nombreEps": "",
            "telefonoEps": "",
            "emailEps": ""
        }
    }
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "numeroFactura": 50002,
  "descripcionServicio": "Tomografía",
  "valor": 300000,
  "total": 450000,
  "paciente": {
    "idPaciente": 2,
    "nombrePaciente": "Diego",
    "apellidoPaciente": "Norrea",
    "direccionPaciente": "Barrio Nueva Vista 97-458",
    "telefonoPaciente": "3295478522",
    "emailPaciente": "diego.norrea@mail.com",
    "eps": {
      "idEps": 242,
      "nombreEps": "Farsitas",
      "telefonoEps": "3271475896",
      "emailEps": "farsitas.eps@mail.com"
    }
  }
}
```

### Respuesta con ID no encontrado `http status 404 Not Found`

```json
{
  "type": "about:blank",
  "title": "Not Found",
  "status": 404,
  "detail": "Factura no encontrada",
  "instance": "/sipress-app/facturas/50003",
  "description": "No se encontró la factura con el ID 50003"
}
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/facturas/50002",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/facturas/50002",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:khaki">POST</p> Agregar un nuevo registro

### Endpoint

```http request
/sipress-app/facturas
```

Este endpoint permite el registro de un nuevo objeto de la entidad Factura.

### Request Body

La solicitud requiere el inicio de sesión para obtener el token de tipo bearer, y agregar la siguiente estructura de
datos en un formato de tipo JSON:

- `numeroFactura` (number): El número de identificación de la factura.

- `descripcionServicio` (string): La descripción del servicio o servicios facturados.

- `valor` (number): El valor de cada servicio.

- `total` (number): El valor total de los servicios facturados.

- `paciente` (object): Se incluye un objeto de tipo Paciente relacionado con la factura:

    - `idPaciente` (number): El número del ID del paciente.

    - `eps` (object): Un objeto de tipo Eps asociado con el paciente:

        - `idEps` (number): El número de identificación de la Eps.

### Response

La respuesta se mostrará en un esquema de tipo JSON con la siguiente estructura:

``` json
{
  "numeroFactura": 0,
  "descripcionServicio": "",
  "valor": 0,
  "total": 0,
  "paciente": {
    "idPaciente": 0,
    "nombrePaciente": "",
    "apellidoPaciente": "",
    "direccionPaciente": "",
    "telefonoPaciente": "",
    "emailPaciente": "",
    "eps": {
      "idEps": 0,
      "nombreEps": "",
      "telefonoEps": "",
      "emailEps": ""
    }
  }
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Body Parameters

```json
{
  "numeroFactura": 50003,
  "descripcionServicio": "Consulta médica general",
  "valor": 25000,
  "total": 30000,
  "paciente": {
    "idPaciente": 3,
    "eps": {
      "idEps": 242
    }
  }
}
```

### Respuesta exitosa `http status 200 OK`

```json
{
  "numeroFactura": 50003,
  "descripcionServicio": "Consulta médica general",
  "valor": 25000,
  "total": 30000,
  "paciente": {
    "idPaciente": 3,
    "nombrePaciente": "Camila",
    "apellidoPaciente": "Vidal",
    "direccionPaciente": "Urbanización Los Bloques 582",
    "telefonoPaciente": "3148569632",
    "emailPaciente": "camila.vidal@mail.com",
    "eps": {
      "idEps": 242,
      "nombreEps": "Farsitas",
      "telefonoEps": "3271475896",
      "emailEps": "farsitas.eps@email.com"
    }
  }
}
```

### Respuesta con campos vacíos `http status 400 Bad Request`

```json
[
  "El número de factura no puede estar vacío",
  "La descripción del servicio no puede estar vacía",
  "El valor del servicio no puede estar vacío",
  "El total de la factura no puede estar vacío",
  "El ID del paciente no puede estar vacío"
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/facturas",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/facturas",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:cornflowerblue">PUT</p> Actualizar un registro

Este endpoint permite la actualización de un registro existente de la entidad Factura.

### Request Body

La solicitud requiere el inicio de sesión para obtener el token de tipo bearer, especificar el ID del registro que se
quiere modificar en la petición HTTP, y agregar la siguiente estructura de datos en un formato de tipo JSON:

- `numeroFactura` (number): El número de identificación de la factura.

- `descripcionServicio` (string): La descripción del servicio o servicios facturados.

- `valor` (number): El valor de cada servicio.

- `total` (number): El valor total de los servicios facturados.

- `paciente` (object): Se incluye un objeto de tipo Paciente relacionado con la factura:

    - `idPaciente` (number): El número del ID del paciente.

    - `eps` (object): Un objeto de tipo Eps asociado con el paciente:

        - `idEps` (number): El número de identificación de la Eps.

### Response

La respuesta se mostrará en un esquema de tipo JSON con la siguiente estructura:

``` json
{
  "numeroFactura": 0,
  "descripcionServicio": "",
  "valor": 0,
  "total": 0,
  "paciente": {
    "idPaciente": 0,
    "nombrePaciente": "",
    "apellidoPaciente": "",
    "direccionPaciente": "",
    "telefonoPaciente": "",
    "emailPaciente": "",
    "eps": {
      "idEps": 0,
      "nombreEps": "",
      "telefonoEps": "",
      "emailEps": ""
    }
  }
}
```

### Importante

Se debe tener precaución con el uso de este endpoint, ya que reemplaza los valores originales del registro especificado.

## Autorización *`Bearer token`

---

## Ejemplos

### Body Parameters

```json
{
  "numeroFactura": 50003,
  "descripcionServicio": "Consulta médica general",
  "valor": 25000,
  "total": 30000,
  "paciente": {
    "idPaciente": 3,
    "eps": {
      "idEps": 242
    }
  }
}
```

### Respuesta exitosa `http status 200 OK`

```json
{
  "numeroFactura": 50003,
  "descripcionServicio": "Consulta médica general",
  "valor": 25000,
  "total": 30000,
  "paciente": {
    "idPaciente": 3,
    "nombrePaciente": "Camila",
    "apellidoPaciente": "Vidal",
    "direccionPaciente": "Urbanización Los Bloques 582",
    "telefonoPaciente": "3148569632",
    "emailPaciente": "camila.vidal@email.com",
    "eps": {
      "idEps": 242,
      "nombreEps": "Farsitas",
      "telefonoEps": "3271475896",
      "emailEps": "farsitas.eps@email.com"
    }
  }
}
```

### Respuesta con campos vacíos `http status 400 Bad Request`

```json
[
  "El número de factura no puede estar vacío",
  "La descripción del servicio no puede estar vacía",
  "El valor del servicio no puede estar vacío",
  "El total de la factura no puede estar vacío",
  "El ID del paciente no puede estar vacío"
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/facturas/50003",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/facturas/50003",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:lightsalmon">DELETE</p> Eliminar un registro

### Endpoint

```http request
/sipress-app/facturas/{id}
```

Este endpoint permite la eliminación de un registro de la entidad Factura. El ID del registro debe ser especificado en
la petición.

### Request

Esta solicitud no requiere un cuerpo, solo necesita especificar el ID del registro en la petición HTTP.

### Response Body

La respuesta generada tendrá la siguiente estructura:

- `Eliminado`: Un valor booleano si la eliminación fue exitosa (`true`) o no (`false`).

### Response HTTP

- Status: 200
- Content-Type: application/json

### Ejemplo de respuesta:

``` json
{
    "Eliminado": boolean
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "Eliminado": true
}
```

### Respuesta con ID no encontrado `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "Factura no encontrada con el id: 50004",
  "instance": "/sipress-app/facturas/50004",
  "description": "Error interno del servidor"
}
```

---

# Servicio de eps

Los endpoints `/epsS` y `/epsS/{id}` permiten la gestión de información relacionada con las Entidades Promotoras de
Salud (EPS). Estos servicios permiten la creación, actualización, eliminación y consulta de las EPS.

## Autorización *`Bearer token`

---

## <p style="color:lightgreen">GET</p> Obtener todos los registros

### Endpoint

```http request
/sipress-app/epsS
```

Este endpoint permite la recuperación de todos los objetos de tipo Eps registrados en la base de datos.

### Request

Es necesario el inicio de sesión y el uso del token de tipo bearer que se genera.

### Response

- Status: 200
- Content-Type: application/json

### Ejemplo *response body*:

``` json
[
    {
        "idEps": 0,
        "nombreEps": "",
        "telefonoEps": "",
        "emailEps": ""
    }
]
```

## Autorización *`Bearer token`

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
[
  {
    "idEps": 241,
    "nombreEps": "Cleptosalud",
    "telefonoEps": "3194713365",
    "emailEps": "cleptosalud.eps@mail.com"
  },
  {
    "idEps": 242,
    "nombreEps": "Farsitas",
    "telefonoEps": "3271475896",
    "emailEps": "farsitas.eps@mail.com"
  },
  {
    "idEps": 243,
    "nombreEps": "Medicalsalud",
    "telefonoEps": "3226987543",
    "emailEps": "medisalud.eps@mail.com"
  },
  {
    "idEps": 244,
    "nombreEps": "TuSalud ",
    "telefonoEps": "6059684128",
    "emailEps": "tusalud.eps@mail.com"
  }
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/epsS",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/epsS",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:lightgreen">GET</p> Obtener un registro

### Endpoint

```http request
/sipress-app/epsS/{id}
```

Este endpoint permite la recuperación de un solo registro de la entidad Eps de la base de datos.

### Request

Para el acceso a este endpoint es necesario iniciar sesión y obtener el token de tipo bearer. También es necesario
especificar el ID del registro en la petición HTTP.

### Response

La respuesta mostrará la información con la siguiente estructura de tipo JSON:

``` json
{
    "idEps": 0,
    "nombreEps": "",
    "telefonoEps": "",
    "emailEps": ""
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "idEps": 243,
  "nombreEps": "Medicalsalud",
  "telefonoEps": "3226987543",
  "emailEps": "medisalud.eps@mail.com"
}
```

### Respuesta con ID no encontrado `http status 404 Not Found`

```json
{
  "type": "about:blank",
  "title": "Not Found",
  "status": 404,
  "detail": "EPS no encontrada",
  "instance": "/sipress-app/epsS/245",
  "description": "No se encontró la EPS con el ID 245"
}
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/epsS/243",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/epsS/243",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:khaki">POST</p> Agregar un nuevo registro

### Endpoint

```http request
/sipress-app/epsS
```

Este endpoint permite el registro de un nuevo objeto de la entidad Eps.

### Request Body

Este endpoint permite agregar un nuevo registro de la entidad Eps.

### Request Body

Para procesar la solicitud es necesario la autenticación con el token de tipo bearer obtenido al iniciar sesión y
diligenciar la información en una estructura tipo JSON con las siguientes propiedades:

- `idEps` — El identificador único de la Eps.

- `nombreEps` — El nombre de la Eps.

- `telefonoEps` — El número telefónico de la Eps.

- `emailEps` — El email de contacto de la Eps.

### Response

La respuesta se mostrará en formato JSON con la siguiente estructura:

``` json
{
  "idEps": 0,
  "nombreEps": "",
  "telefonoEps": "",
  "emailEps": ""
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Body Parameters

```json
{
  "idEps": 245,
  "nombreEps": "NuevaEPS Agregada",
  "telefonoEps": "3226987543",
  "emailEps": "nueva.eps@agregada.com"
}
```

### Respuesta exitosa `http status 200 OK`

```json
{
  "idEps": 245,
  "nombreEps": "NuevaEPS Agregada",
  "telefonoEps": "3226987543",
  "emailEps": "nueva.eps@agregada.com"
}
```

### Respuesta con campos vacíos `http status 400 Bad Request`

```json
[
  "El ID de la EPS no puede estar vacío",
  "El nombre de la EPS no puede estar vacío",
  "El teléfono de la EPS no puede estar vacío",
  "El email de la EPS no puede estar vacío"
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/epsS",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/epsS",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:cornflowerblue">PUT</p> Actualizar un registro

Este endpoint permite la actualización de un registro específico de la entidad Eps.

### Request Body

Para ejecutar la solicitud es necesario el inicio de sesión para obtener el token de tipo bearer y especificar en la
petición HTTP el ID del registro que se quiere actualizar, e incluir una solicitud que contenga las siguientes
propiedades:

- `idEps` — El identificador único de la Eps.
- `nombreEps` — El nombre de la Eps.
- `telefonoEps` — El número telefónico de la Eps.
- `emailEps` — El email de contacto de la Eps.

### Response

La respuesta se mostrará en un formato de tipo JSON con la siguiente estructura:

``` json
{
  "idEps": 0,
  "nombreEps": "",
  "telefonoEps": "",
  "emailEps": ""
}
```

### Importante

Se debe tener en cuenta que al usar este endpoint, la información del registro seleccionado se cambiará de forma
permanente.

## Autorización *`Bearer token`

---

## Ejemplos

### Body Parameters

```json
{
  "idEps": 241,
  "nombreEps": "Todo Salud (dato actualizado)",
  "telefonoEps": "3194713365",
  "emailEps": "cleptosalud.eps@mail.com"
}
```

### Respuesta exitosa `http status 200 OK`

```json
{
  "idEps": 241,
  "nombreEps": "Todo Salud (dato actualizado)",
  "telefonoEps": "3194713365",
  "emailEps": "cleptosalud.eps@mail.com"
}
```

### Respuesta con campos vacíos `http status 400 Bad Request`

```json
[
  "El ID de la EPS no puede estar vacío",
  "El nombre de la EPS no puede estar vacío",
  "El teléfono de la EPS no puede estar vacío",
  "El email de la EPS no puede estar vacío"
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/epsS/241",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/epsS/241",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:lightsalmon">DELETE</p> Eliminar un registro

### Endpoint

```http request
/sipress-app/epsS/{id}
```

Este endpoint permite la eliminación de un registro de la entidad Eps. El ID del registro debe ser especificado en la
petición.

### Request

Esta solicitud no requiere un cuerpo, solo necesita especificar el ID del registro en la petición HTTP.

### Response Body

La respuesta generada tendrá la siguiente estructura:

- `Eliminado`: Un valor booleano si la eliminación fue exitosa (`true`) o no (`false`).

### Response HTTP

- Status: 200
- Content-Type: application/json

### Ejemplo de respuesta:

``` json
{
    "Eliminado": boolean
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "Eliminado": true
}
```

### Respuesta con ID no encontrado `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "EPS no encontrada con el id: 245",
  "instance": "/sipress-app/epsS/245",
  "description": "Error interno del servidor"
}
```

---

# Servicio de doctor

Los endpoints `/doctores` y `/doctores/{id}` permiten la gestión de información relacionada con la entidad Doctor. Estos
servicios permiten la creación, actualización, eliminación y consulta de los registros de doctores.

## Autorización *`Bearer token`

---

## <p style="color:lightgreen">GET</p> Obtener todos los registros

### Endpoint

```http request
/sipress-app/doctores
```

Este endpoint permite la recuperación de todos los objetos de tipo Doctor registrados en la base de datos.

### Request

Es necesario el inicio de sesión y el uso del token de tipo bearer que se genera.

### Response

- Status: 200
- Content-Type: application/json

### Ejemplo *response body*:

``` json
[
    {
        "idDoctor": 0,
        "nombreDoctor": "",
        "apellidoDoctor": "",
        "telefonoDoctor": "",
        "emailDoctor": "",
        "dependencia": {
            "idDependencia": 0,
            "nombreDependencia": "",
            "institucion": {
                "idInstitucion": 0,
                "nombreInstitucion": "",
                "direccionInstitucion": "",
                "telefonoInstitucion": "",
                "codigoPostal": ""
            }
        }
    }
]
```

## Autorización *`Bearer token`

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
[
  {
    "idDoctor": 2001,
    "nombreDoctor": "Alexander",
    "apellidoDoctor": "Ferrara",
    "telefonoDoctor": "3154745582",
    "emailDoctor": "alexander.ferrara@mail.com",
    "dependencia": {
      "idDependencia": 1408,
      "nombreDependencia": "Psiquiatría",
      "institucion": {
        "idInstitucion": 102,
        "nombreInstitucion": "Centro Médico Quahog",
        "direccionInstitucion": "Calle Evergreen 742",
        "telefonoInstitucion": "6063218277",
        "codigoPostal": "047025"
      }
    }
  },
  {
    "idDoctor": 2002,
    "nombreDoctor": "Gustavo",
    "apellidoDoctor": "Restrepo",
    "telefonoDoctor": "3214471023",
    "emailDoctor": "gustavo.restrepo@mail.com",
    "dependencia": {
      "idDependencia": 1403,
      "nombreDependencia": "Radiología",
      "institucion": {
        "idInstitucion": 103,
        "nombreInstitucion": "Clínica Salutia IPS",
        "direccionInstitucion": "Avenida Laputa 4152",
        "telefonoInstitucion": "6012541133",
        "codigoPostal": "023658"
      }
    }
  },
  {
    "idDoctor": 2003,
    "nombreDoctor": "Miguel",
    "apellidoDoctor": "Carmona",
    "telefonoDoctor": "3145987433",
    "emailDoctor": "miguel.carmona@mail.com",
    "dependencia": {
      "idDependencia": 1409,
      "nombreDependencia": "Odontología",
      "institucion": {
        "idInstitucion": 101,
        "nombreInstitucion": "IPS Fundación Hospitalaria Alexander Fleming",
        "direccionInstitucion": "Calle Falsa 123",
        "telefonoInstitucion": "6045033892",
        "codigoPostal": "050040"
      }
    }
  }
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/doctores",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/doctores",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:lightgreen">GET</p> Obtener un registro

### Endpoint

```http request
/sipress-app/doctores/{id}
```

Este endpoint permite la recuperación de un solo registro de la entidad Doctor de la base de datos.

### Request

Para el acceso a este endpoint es necesario iniciar sesión y obtener el token de tipo bearer. También es necesario
especificar el ID del registro en la petición HTTP.

### Response

La respuesta mostrará la información con la siguiente estructura de tipo JSON:

``` json
{
    "idDoctor": 0,
    "nombreDoctor": "",
    "apellidoDoctor": "",
    "telefonoDoctor": "",
    "emailDoctor": "",
    "dependencia": {
        "idDependencia": 0,
        "nombreDependencia": "",
        "institucion": {
            "idInstitucion": 0,
            "nombreInstitucion": "",
            "direccionInstitucion": "",
            "telefonoInstitucion": "",
            "codigoPostal": ""
        }
    }
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "idDoctor": 2002,
  "nombreDoctor": "Gustavo",
  "apellidoDoctor": "Restrepo",
  "telefonoDoctor": "3214471023",
  "emailDoctor": "gustavo.restrepo@mail.com",
  "dependencia": {
    "idDependencia": 1403,
    "nombreDependencia": "Radiología",
    "institucion": {
      "idInstitucion": 103,
      "nombreInstitucion": "Clínica Salutia IPS",
      "direccionInstitucion": "Avenida Laputa 4152",
      "telefonoInstitucion": "6012541133",
      "codigoPostal": "023658"
    }
  }
}
```

### Respuesta con ID no encontrado `http status 404 Not Found`

```json
{
  "type": "about:blank",
  "title": "Not Found",
  "status": 404,
  "detail": "Doctor no encontrado",
  "instance": "/sipress-app/doctores/2004",
  "description": "No se encontró el doctor con el ID 2004"
}
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/doctores/2002",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/doctores/2002",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:khaki">POST</p> Agregar un nuevo registro

### Endpoint

```http request
/sipress-app/doctores
```

Este endpoint permite el registro de un nuevo objeto de la entidad Doctor.

### Request Body

La solicitud requiere el inicio de sesión para obtener el token de tipo bearer, y agregar la siguiente estructura de
datos en un formato de tipo JSON:

- `idDoctor` (number): El número de identificación del doctor.
- `nombreDoctor` (string): El nombre del doctor.
- `apellidoDoctor` (string): El apellido del doctor.
- `telefonoDoctor` (string): El número telefónico del doctor.
- `emailDoctor` (string): El email de contacto del doctor.
- `dependencia` (object): Se incluye un objeto de tipo Dependencia relacionado con el doctor:
    - `idDependencia` (number): El número del ID de la dependencia.
    - `institucion` (object): Un objeto de tipo Institución asociado con la dependencia:
        - `idInstitucion` (number): El número de identificación de la institución.

### Response

La respuesta se mostrará en un esquema de tipo JSON con la siguiente estructura:

``` json
{
    "idDoctor": 0,
    "nombreDoctor": "",
    "apellidoDoctor": "",
    "telefonoDoctor": "",
    "emailDoctor": "",
    "dependencia": {
        "idDependencia": 0,
        "nombreDependencia": "",
        "institucion": {
            "idInstitucion": 0,
            "nombreInstitucion": "",
            "direccionInstitucion": "",
            "telefonoInstitucion": "",
            "codigoPostal": ""
        }
    }
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Body Parameters

```json
{
  "idDoctor": 2004,
  "nombreDoctor": "Ramiro",
  "apellidoDoctor": "Chapatín",
  "telefonoDoctor": "6013410111",
  "emailDoctor": "ramiro.chapatin@correo.com",
  "dependencia": {
    "idDependencia": 1408,
    "institucion": {
      "idInstitucion": 102
    }
  }
}
```

### Respuesta exitosa `http status 200 OK`

```json
{
  "idDoctor": 2004,
  "nombreDoctor": "Ramiro",
  "apellidoDoctor": "Chapatín",
  "telefonoDoctor": "6013410111",
  "emailDoctor": "ramiro.chapatin@correo.com",
  "dependencia": {
    "idDependencia": 1408,
    "nombreDependencia": "Psiquiatría",
    "institucion": {
      "idInstitucion": 102,
      "nombreInstitucion": "Centro Médico Quahog",
      "direccionInstitucion": "Calle Evergreen 742",
      "telefonoInstitucion": "6063218277",
      "codigoPostal": "047025"
    }
  }
}
```

### Respuesta con campos vacíos `http status 400 Bad Request`

```json
[
  "El ID del doctor no puede estar vacío",
  "El nombre del doctor no puede estar vacío",
  "El apellido del doctor no puede estar vacío",
  "El teléfono del doctor no puede estar vacío",
  "El email del doctor no puede estar vacío",
  "El ID de la dependencia no puede estar vacío"
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/doctores",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/doctores",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:cornflowerblue">PUT</p> Actualizar un registro

Este endpoint permite la actualización de un registro existente de la entidad Doctor.

### Request Body

La solicitud requiere el inicio de sesión para obtener el token de tipo bearer, especificar el ID del registro que se
quiere modificar en la petición HTTP, y agregar la siguiente estructura de datos en un formato de tipo JSON:

- `idDoctor` (number): El número de identificación del doctor.
- `nombreDoctor` (string): El nombre del doctor.
- `apellidoDoctor` (string): El apellido del doctor.
- `telefonoDoctor` (string): El número telefónico del doctor.
- `emailDoctor` (string): El email de contacto del doctor.
- `dependencia` (object): Se incluye un objeto de tipo Dependencia relacionado con el doctor:
    - `idDependencia` (number): El número del ID de la dependencia.
    - `institucion` (object): Un objeto de tipo Institución asociado con la dependencia:
        - `idInstitucion` (number): El número de identificación de la institución.

### Response

La respuesta se mostrará en un esquema de tipo JSON con la siguiente estructura:

``` json
{
    "idDoctor": 0,
    "nombreDoctor": "",
    "apellidoDoctor": "",
    "telefonoDoctor": "",
    "emailDoctor": "",
    "dependencia": {
        "idDependencia": 0,
        "nombreDependencia": "",
        "institucion": {
            "idInstitucion": 0,
            "nombreInstitucion": "",
            "direccionInstitucion": "",
            "telefonoInstitucion": "",
            "codigoPostal": ""
        }
    }
}
```

### Importante

Se debe tener precaución con el uso de este endpoint, ya que no solo reemplaza los valores originales del registro
especificado, sino que también puede cambiar datos de la dependencia y la institución asociadas.

## Autorización *`Bearer token`

---

## Ejemplos

### Body Parameters

```json
 {
  "idDoctor": 2003,
  "nombreDoctor": "Miguel Ángel (dato actualizado)",
  "apellidoDoctor": "Carmona Tabares (dato actualizado)",
  "telefonoDoctor": "3145987433",
  "emailDoctor": "miguel.carmona@mail.com",
  "dependencia": {
    "idDependencia": 1409,
    "nombreDependencia": "Odontología",
    "institucion": {
      "idInstitucion": 101,
      "nombreInstitucion": "IPS Fundación Hospitalaria Alexander Fleming",
      "direccionInstitucion": "Calle Falsa 123",
      "telefonoInstitucion": "6045033892",
      "codigoPostal": "050040"
    }
  }
}
```

### Respuesta exitosa `http status 200 OK`

```json
{
  "idDoctor": 2003,
  "nombreDoctor": "Miguel Ángel (dato actualizado)",
  "apellidoDoctor": "Carmona Tabares (dato actualizado)",
  "telefonoDoctor": "3145987433",
  "emailDoctor": "miguel.carmona@mail.com",
  "dependencia": {
    "idDependencia": 1409,
    "nombreDependencia": "Odontología",
    "institucion": {
      "idInstitucion": 101,
      "nombreInstitucion": "IPS Fundación Hospitalaria Alexander Fleming",
      "direccionInstitucion": "Calle Falsa 123",
      "telefonoInstitucion": "6045033892",
      "codigoPostal": "050040"
    }
  }
}
```

### Respuesta con campos vacíos `http status 400 Bad Request`

```json
[
  "El ID del doctor no puede estar vacío",
  "El nombre del doctor no puede estar vacío",
  "El apellido del doctor no puede estar vacío",
  "El teléfono del doctor no puede estar vacío",
  "El email del doctor no puede estar vacío",
  "El ID de la dependencia no puede estar vacío"
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/doctores/2003",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/doctores/2003",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:lightsalmon">DELETE</p> Eliminar un registro

### Endpoint

```http request
/sipress-app/doctores/{id}
```

Este endpoint permite la eliminación de un registro de la entidad Doctor. El ID del registro debe ser especificado en la
petición.

### Request

Esta solicitud no requiere un cuerpo, solo necesita especificar el ID del registro en la petición HTTP.

### Response Body

La respuesta generada tendrá la siguiente estructura:

- `Eliminado`: Un valor booleano si la eliminación fue exitosa (`true`) o no (`false`).

### Response HTTP

- Status: 200
- Content-Type: application/json

### Ejemplo de respuesta:

``` json
{
    "Eliminado": boolean
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "Eliminado": true
}
```

### Respuesta con ID no encontrado `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "Doctor no encontrado con el id: 2004",
  "instance": "/sipress-app/doctores/2004",
  "description": "Error interno del servidor"
}
```

---

# Servicio de dependencia

Los endpoints `/dependencias` y `/dependencias/{id}` permiten la gestión de información relacionada con la entidad
Dependencia. Estos servicios permiten la creación, actualización, eliminación y consulta de los registros de
dependencias.

## Autorización *`Bearer token`

---

## <p style="color:lightgreen">GET</p> Obtener todos los registros

### Endpoint

```http request
/sipress-app/dependencias
```

Este endpoint permite recuperar todos los registros de la entidad dependencia de la bse de datos.

### Request

Para hacer efectiva la solicitud es necesario el uso del token de tipo bearer que se obtiene al iniciar sesión.

### Response

- Status: 200

- Content-Type: application/json

### Ejemplo de respuesta:

``` json
[
    {
        "idDependencia": 0,
        "nombreDependencia": "string",
        "institucion": {
            "idInstitucion": 0,
            "nombreInstitucion": "string",
            "direccionInstitucion": "string",
            "telefonoInstitucion": "string",
            "codigoPostal": "string"
        }
    }
]
```

## Autorización *`Bearer token`

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
[
  {
    "idDependencia": 1401,
    "nombreDependencia": "Cardiología",
    "institucion": {
      "idInstitucion": 101,
      "nombreInstitucion": "IPS Fundación Hospitalaria Alexander Fleming",
      "direccionInstitucion": "Calle Falsa 123",
      "telefonoInstitucion": "6045033892",
      "codigoPostal": "050040"
    }
  },
  {
    "idDependencia": 1402,
    "nombreDependencia": "Ginecología",
    "institucion": {
      "idInstitucion": 101,
      "nombreInstitucion": "IPS Fundación Hospitalaria Alexander Fleming",
      "direccionInstitucion": "Calle Falsa 123",
      "telefonoInstitucion": "6045033892",
      "codigoPostal": "050040"
    }
  },
  {
    "idDependencia": 1403,
    "nombreDependencia": "Radiología",
    "institucion": {
      "idInstitucion": 103,
      "nombreInstitucion": "Clínica Salutia IPS",
      "direccionInstitucion": "Avenida Laputa 4152",
      "telefonoInstitucion": "6012541133",
      "codigoPostal": "023658"
    }
  }
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/dependencias",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/dependencias",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:lightgreen">GET</p> Obtener un registro

### Endpoint

```http request
/sipress-app/dependencias/{id}
```

Este endpoint permite la recuperación de un solo registro de la entidad Dependencia de la base de datos.

### Request

Para el acceso a este endpoint es necesario iniciar sesión y obtener el token de tipo bearer. También es necesario
especificar el ID del registro en la petición HTTP.

### Response

La respuesta mostrará la información con la siguiente estructura de tipo JSON:

``` json
{
    "idDependencia": 0,
    "nombreDependencia": "string",
    "institucion": {
        "idInstitucion": 0,
        "nombreInstitucion": "string",
        "direccionInstitucion": "string",
        "telefonoInstitucion": "string",
        "codigoPostal": "string"
    }
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "idDependencia": 1403,
  "nombreDependencia": "Radiología",
  "institucion": {
    "idInstitucion": 103,
    "nombreInstitucion": "Clínica Salutia IPS",
    "direccionInstitucion": "Avenida Laputa 4152",
    "telefonoInstitucion": "6012541133",
    "codigoPostal": "023658"
  }
}
```

### Respuesta con ID no encontrado `http status 404 Not Found`

```json
{
  "type": "about:blank",
  "title": "Not Found",
  "status": 404,
  "detail": "Dependencia no encontrada",
  "instance": "/sipress-app/dependencias/1404",
  "description": "No se encontró la dependencia con el ID 1404"
}
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/dependencias/1403",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/dependencias/1403",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:khaki">POST</p> Agregar un nuevo registro

### Endpoint

```http request
/sipress-app/dependencias
```

Este endpoint permite el registro de un nuevo objeto de la entidad Dependencia.

### Request Body

La solicitud requiere el inicio de sesión para obtener el token de tipo bearer, y agregar la siguiente estructura de
datos en un formato de tipo JSON:

- `idDependencia` (number): El número de identificación de la dependencia.
- `nombreDependencia` (string): El nombre de la dependencia.
- `institucion` (object): Un objeto de tipo Institución asociado con la dependencia:
    - `idInstitucion` (number): El número de identificación de la institución.

### Response

La respuesta se mostrará en un esquema de tipo JSON con la siguiente estructura:

``` json
{
    "idDependencia": 0,
    "nombreDependencia": "string",
    "institucion": {
        "idInstitucion": 0,
        "nombreInstitucion": "string",
        "direccionInstitucion": "string",
        "telefonoInstitucion": "string",
        "codigoPostal": "string"
    }
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Body Parameters

```json
{
  "idDependencia": 1404,
  "nombreDependencia": "Neurología",
  "institucion": {
    "idInstitucion": 101
  }
}
```

### Respuesta exitosa `http status 200 OK`

```json
{
  "idDependencia": 1404,
  "nombreDependencia": "Neurología",
  "institucion": {
    "idInstitucion": 101,
    "nombreInstitucion": "IPS Fundación Hospitalaria Alexander Fleming",
    "direccionInstitucion": "Calle Falsa 123",
    "telefonoInstitucion": "6045033892",
    "codigoPostal": "050040"
  }
}
```

### Respuesta con campos vacíos `http status 400 Bad Request`

```json
[
  "El ID de la dependencia no puede estar vacío",
  "El nombre de la dependencia no puede estar vacío",
  "El ID de la institución no puede estar vacío"
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/dependencias",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/dependencias",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:cornflowerblue">PUT</p> Actualizar un registro

Este endpoint permite la actualización de un registro existente de la entidad Dependencia.

### Request Body

La solicitud requiere el inicio de sesión para obtener el token de tipo bearer, especificar el ID del registro que se
quiere modificar en la petición HTTP, y agregar la siguiente estructura de datos en un formato de tipo JSON:

- `idDependencia` (number): El número de identificación de la dependencia.
- `nombreDependencia` (string): El nombre de la dependencia.
- `institucion` (object): Un objeto de tipo Institución asociado con la dependencia:
    - `idInstitucion` (number): El número de identificación de la institución.

### Response

La respuesta se mostrará en un esquema de tipo JSON con la siguiente estructura:

``` json
{
    "idDependencia": 0,
    "nombreDependencia": "string",
    "institucion": {
        "idInstitucion": 0,
        "nombreInstitucion": "string",
        "direccionInstitucion": "string",
        "telefonoInstitucion": "string",
        "codigoPostal": "string"
    }
}
```

### Importante

Se debe tener precaución con el uso de este endpoint, ya que no solo reemplaza los valores originales del registro
especificado, sino que también puede cambiar datos de la institución asociada.

## Autorización *`Bearer token`

---

## Ejemplos

### Body Parameters

```json
{
  "idDependencia": 1404,
  "nombreDependencia": "Neurología (dato actualizado)",
  "institucion": {
    "idInstitucion": 101
  }
}
```

### Respuesta exitosa `http status 200 OK`

```json
{
  "idDependencia": 1404,
  "nombreDependencia": "Neurología (dato actualizado)",
  "institucion": {
    "idInstitucion": 101,
    "nombreInstitucion": "IPS Fundación Hospitalaria Alexander Fleming",
    "direccionInstitucion": "Calle Falsa 123",
    "telefonoInstitucion": "6045033892",
    "codigoPostal": "050040"
  }
}
```

### Respuesta con campos vacíos `http status 400 Bad Request`

```json
[
  "El ID de la dependencia no puede estar vacío",
  "El nombre de la dependencia no puede estar vacío",
  "El ID de la institución no puede estar vacío"
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/dependencias/1404",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/dependencias/1404",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:lightsalmon">DELETE</p> Eliminar un registro

### Endpoint

```http request
/sipress-app/dependencias/{id}
```

Este endpoint permite la eliminación de un registro de la entidad Dependencia. El ID del registro debe ser especificado
en la petición.

### Request

Esta solicitud no requiere un cuerpo, solo necesita especificar el ID del registro en la petición HTTP.

### Response Body

La respuesta generada tendrá la siguiente estructura:

- `Eliminado`: Un valor booleano si la eliminación fue exitosa (`true`) o no (`false`).

### Response HTTP

- Status: 200
- Content-Type: application/json

### Ejemplo de respuesta:

``` json
{
    "Eliminado": boolean
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "Eliminado": true
}
```

### Respuesta con ID no encontrado `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "Dependencia no encontrada con el id: 1404",
  "instance": "/sipress-app/dependencias/1404",
  "description": "Error interno del servidor"
}
```

---

# Servicio de consultorio

Los endpoints `/consultorios` y `/consultorios/{id}` permiten la gestión de información relacionada con la entidad
Consultorio. Estos servicios permiten la creación, actualización, eliminación y consulta de los registros de
consultorios.

## Autorización *`Bearer token`

---

## <p style="color:lightgreen">GET</p> Obtener todos los registros

### Endpoint

```http request
/sipress-app/consultorios
```

Este endpoint permite recuperar todos los registros de la entidad consultorio de la base de datos.

### Request

Para hacer efectiva la solicitud es necesario el uso del token de tipo bearer que se obtiene al iniciar sesión.

### Response

- Status: 200
- Content-Type: application/json

### Ejemplo de respuesta:

``` json
[
    {
        "numeroConsultorio": 0,
        "fechaAdmision": "",
        "paciente": {
            "idPaciente": 0,
            "nombrePaciente": "",
            "apellidoPaciente": "",
            "direccionPaciente": "",
            "telefonoPaciente": "",
            "emailPaciente": "",
            "eps": {
                "idEps": 0,
                "nombreEps": "",
                "telefonoEps": "",
                "emailEps": ""
            }
        },
        "personal": {
            "idPersonal": 0,
            "nombrePersonal": "",
            "apellidoPersonal": "",
            "telefonoPersonal": "",
            "emailPersonal": "",
            "dependencia": {
                "idDependencia": 0,
                "nombreDependencia": "",
                "institucion": {
                    "idInstitucion": 0,
                    "nombreInstitucion": "",
                    "direccionInstitucion": "",
                    "telefonoInstitucion": "",
                    "codigoPostal": ""
                }
            }
        }
    }
]
```

## Autorización *`Bearer token`

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
[
  {
    "numeroConsultorio": 406,
    "fechaAdmision": "2024-07-03",
    "paciente": {
      "idPaciente": 1,
      "nombrePaciente": "Sara Fernanda (dato actualizado)",
      "apellidoPaciente": "Tabares",
      "direccionPaciente": "Dirección Actualizada 4514",
      "telefonoPaciente": "3144421001",
      "emailPaciente": "correo.actualizado@mail.com",
      "eps": {
        "idEps": 243,
        "nombreEps": "Medicalsalud",
        "telefonoEps": "3226987543",
        "emailEps": "medisalud.eps@mail.com"
      }
    },
    "personal": {
      "idPersonal": 4101,
      "nombrePersonal": "Carla",
      "apellidoPersonal": "Posada",
      "telefonoPersonal": "3206639874",
      "emailPersonal": "carla.franco@mail.com",
      "dependencia": {
        "idDependencia": 1401,
        "nombreDependencia": "Servicios Generales",
        "institucion": {
          "idInstitucion": 101,
          "nombreInstitucion": "IPS Fundación Hospitalaria Alexander Fleming",
          "direccionInstitucion": "Calle Falsa 123",
          "telefonoInstitucion": "6045033892",
          "codigoPostal": "050040"
        }
      }
    }
  },
  {
    "numeroConsultorio": 624,
    "fechaAdmision": "2024-07-31",
    "paciente": {
      "idPaciente": 2,
      "nombrePaciente": "Diego",
      "apellidoPaciente": "Norrea",
      "direccionPaciente": "Barrio Nueva Vista 97-458",
      "telefonoPaciente": "3295478522",
      "emailPaciente": "diego.norrea@mail.com",
      "eps": {
        "idEps": 242,
        "nombreEps": "Farsitas",
        "telefonoEps": "3271475896",
        "emailEps": "farsitas.eps@mail.com"
      }
    },
    "personal": {
      "idPersonal": 4104,
      "nombrePersonal": "Juana Mercedes (dato actualizado)",
      "apellidoPersonal": "Díaz (dato actualizado)",
      "telefonoPersonal": "314258963",
      "emailPersonal": "correo@actualizado.com",
      "dependencia": {
        "idDependencia": 1401,
        "nombreDependencia": "Servicios Generales",
        "institucion": {
          "idInstitucion": 101,
          "nombreInstitucion": "IPS Fundación Hospitalaria Alexander Fleming",
          "direccionInstitucion": "Calle Falsa 123",
          "telefonoInstitucion": "6045033892",
          "codigoPostal": "050040"
        }
      }
    }
  }
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/consultorios",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/consultorios",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:lightgreen">GET</p> Obtener un registro

### Endpoint

```http request
/sipress-app/consultorios/{id}
```

Este endpoint permite la recuperación de un solo registro de la entidad Consultorio de la base de datos.

### Request

Para el acceso a este endpoint es necesario iniciar sesión y obtener el token de tipo bearer. También es necesario
especificar el ID del registro en la petición HTTP.

### Response

La respuesta mostrará la información con la siguiente estructura de tipo JSON:

``` json
{
    "numeroConsultorio": 0,
    "fechaAdmision": "",
    "paciente": {
        "idPaciente": 0,
        "nombrePaciente": "",
        "apellidoPaciente": "",
        "direccionPaciente": "",
        "telefonoPaciente": "",
        "emailPaciente": "",
        "eps": {
            "idEps": 0,
            "nombreEps": "",
            "telefonoEps": "",
            "emailEps": ""
        }
    },
    "personal": {
        "idPersonal": 0,
        "nombrePersonal": "",
        "apellidoPersonal": "",
        "telefonoPersonal": "",
        "emailPersonal": "",
        "dependencia": {
            "idDependencia": 0,
            "nombreDependencia": "",
            "institucion": {
                "idInstitucion": 0,
                "nombreInstitucion": "",
                "direccionInstitucion": "",
                "telefonoInstitucion": "",
                "codigoPostal": ""
            }
        }
    }
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "numeroConsultorio": 624,
  "fechaAdmision": "2024-07-31",
  "paciente": {
    "idPaciente": 2,
    "nombrePaciente": "Diego",
    "apellidoPaciente": "Norrea",
    "direccionPaciente": "Barrio Nueva Vista 97-458",
    "telefonoPaciente": "3295478522",
    "emailPaciente": "diego.norrea@mail.com",
    "eps": {
      "idEps": 242,
      "nombreEps": "Farsitas",
      "telefonoEps": "3271475896",
      "emailEps": "farsitas.eps@mail.com"
    }
  },
  "personal": {
    "idPersonal": 4104,
    "nombrePersonal": "Juana Mercedes (dato actualizado)",
    "apellidoPersonal": "Díaz (dato actualizado)",
    "telefonoPersonal": "314258963",
    "emailPersonal": "correo@actualizado.com",
    "dependencia": {
      "idDependencia": 1401,
      "nombreDependencia": "Servicios Generales",
      "institucion": {
        "idInstitucion": 101,
        "nombreInstitucion": "IPS Fundación Hospitalaria Alexander Fleming",
        "direccionInstitucion": "Calle Falsa 123",
        "telefonoInstitucion": "6045033892",
        "codigoPostal": "050040"
      }
    }
  }
}
```

### Respuesta con ID no encontrado `http status 404 Not Found`

```json
{
  "type": "about:blank",
  "title": "Not Found",
  "status": 404,
  "detail": "Consultorio no encontrado",
  "instance": "/sipress-app/consultorios/625",
  "description": "No se encontró el consultorio con el ID 625"
}
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/consultorios/624",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/consultorios/624",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:khaki">POST</p> Agregar un nuevo registro

### Endpoint

```http request
/sipress-app/consultorios
```

Este endpoint permite el registro de un nuevo objeto de la entidad Consultorio.

### Request Body

La solicitud requiere el inicio de sesión para obtener el token de tipo bearer, y agregar la siguiente estructura de
datos en un formato de tipo JSON:

- `numeroConsultorio` (number): El número de identificación del consultorio.
- `fechaAdmision` (string): La fecha de admisión del paciente.
- `paciente` (object): Un objeto de tipo Paciente asociado con el consultorio:
    - `idPaciente` (number): El número de identificación del paciente.
    - `eps` (object): Un objeto de tipo EPS asociado con el paciente:
        - `idEps` (number): El número de identificación de la EPS.
- `personal` (object): Un objeto de tipo Personal asociado con el consultorio:
    - `idPersonal` (number): El número de identificación del personal.
    - `dependencia` (object): Un objeto de tipo Dependencia asociado con el personal:
        - `idDependencia` (number): El número de identificación de la dependencia.
        - `institucion` (object): Un objeto de tipo Institución asociado con la dependencia:
            - `idInstitucion` (number): El número de identificación de la institución.

### Response

La respuesta se mostrará en un esquema de tipo JSON con la siguiente estructura:

``` json
{
    "numeroConsultorio": 0,
    "fechaAdmision": "",
    "paciente": {
        "idPaciente": 0,
        "nombrePaciente": "",
        "apellidoPaciente": "",
        "direccionPaciente": "",
        "telefonoPaciente": "",
        "emailPaciente": "",
        "eps": {
            "idEps": 0,
            "nombreEps": "",
            "telefonoEps": "",
            "emailEps": ""
        }
    },
    "personal": {
        "idPersonal": 0,
        "nombrePersonal": "",
        "apellidoPersonal": "",
        "telefonoPersonal": "",
        "emailPersonal": "",
        "dependencia": {
            "idDependencia": 0,
            "nombreDependencia": "",
            "institucion": {
                "idInstitucion": 0,
                "nombreInstitucion": "",
                "direccionInstitucion": "",
                "telefonoInstitucion": "",
                "codigoPostal": ""
            }
        }
    }
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Body Parameters

```json
{
  "numeroConsultorio": 345,
  "fechaAdmision": "2024-08-18",
  "paciente": {
    "idPaciente": 4,
    "eps": {
      "idEps": 243
    }
  },
  "personal": {
    "idPersonal": 4106,
    "dependencia": {
      "idDependencia": 1402,
      "institucion": {
        "idInstitucion": 102
      }
    }
  }
}
```

### Respuesta exitosa `http status 200 OK`

```json
{
  "numeroConsultorio": 345,
  "fechaAdmision": "2024-08-18",
  "paciente": {
    "idPaciente": 4,
    "nombrePaciente": "Nuevo",
    "apellidoPaciente": "Paciente",
    "direccionPaciente": "Dirección nuevo paciente",
    "telefonoPaciente": "123456789",
    "emailPaciente": "nuevo.paciente@correo.com",
    "eps": {
      "idEps": 241,
      "nombreEps": "Todo Salud (dato actualizado)",
      "telefonoEps": "3194713365",
      "emailEps": "cleptosalud.eps@mail.com"
    }
  },
  "personal": {
    "idPersonal": 4106,
    "nombrePersonal": "Juan",
    "apellidoPersonal": "Tello",
    "telefonoPersonal": "3254789632",
    "emailPersonal": "juan.tello@correo.com",
    "dependencia": {
      "idDependencia": 1402,
      "nombreDependencia": "Laboratorio Clínico",
      "institucion": {
        "idInstitucion": 102,
        "nombreInstitucion": "Centro Médico Quahog",
        "direccionInstitucion": "Calle Evergreen 742",
        "telefonoInstitucion": "6063218277",
        "codigoPostal": "047025"
      }
    }
  }
}
```

### Respuesta con campos vacíos `http status 400 Bad Request`

```json
[
  "El número del consultorio no puede estar vacío",
  "La fecha de admisión no puede estar vacía",
  "El ID del paciente no puede estar vacío",
  "El ID de la EPS no puede estar vacío",
  "El ID del personal no puede estar vacío",
  "El ID de la dependencia no puede estar vacío",
  "El ID de la institución no puede estar vacío"
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/consultorios",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/consultorios",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:cornflowerblue">PUT</p> Actualizar un registro

Este endpoint permite la actualización de un registro existente de la entidad Consultorio.

### Request Body

La solicitud requiere el inicio de sesión para obtener el token de tipo bearer, especificar el ID del registro que se
quiere modificar en la petición HTTP, y agregar la siguiente estructura de datos en un formato de tipo JSON:

- `numeroConsultorio` (number): El número de identificación del consultorio.
- `fechaAdmision` (string): La fecha de admisión del paciente.
- `paciente` (object): Un objeto de tipo Paciente asociado con el consultorio:
    - `idPaciente` (number): El número de identificación del paciente.
    - `eps` (object): Un objeto de tipo EPS asociado con el paciente:
        - `idEps` (number): El número de identificación de la EPS.
- `personal` (object): Un objeto de tipo Personal asociado con el consultorio:
- `idPersonal` (number): El número de identificación del personal.
    - `dependencia` (object): Un objeto de tipo Dependencia asociado con el personal:
        - `idDependencia` (number): El número de identificación de la dependencia.
        - `institucion` (object): Un objeto de tipo Institución asociado con la dependencia:
            - `idInstitucion` (number): El número de identificación de la institución.

### Response

La respuesta se mostrará en un esquema de tipo JSON con la siguiente estructura:

``` json
{
    "numeroConsultorio": 0,
    "fechaAdmision": "",
    "paciente": {
        "idPaciente": 0,
        "nombrePaciente": "",
        "apellidoPaciente": "",
        "direccionPaciente": "",
        "telefonoPaciente": "",
        "emailPaciente": "",
        "eps": {
            "idEps": 0,
            "nombreEps": "",
            "telefonoEps": "",
            "emailEps": ""
        }
    },
    "personal": {
        "idPersonal": 0,
        "nombrePersonal": "",
        "apellidoPersonal": "",
        "telefonoPersonal": "",
        "emailPersonal": "",
        "dependencia": {
            "idDependencia": 0,
            "nombreDependencia": "",
            "institucion": {
                "idInstitucion": 0,
                "nombreInstitucion": "",
                "direccionInstitucion": "",
                "telefonoInstitucion": "",
                "codigoPostal": ""
            }
        }
    }
}
```

### Importante

Se debe tener precaución con el uso de este endpoint, ya que no solo reemplaza los valores originales del registro
especificado, sino que también puede cambiar datos del paciente, EPS, personal, dependencia y la institución asociada.

## Autorización *`Bearer token`

---

## Ejemplos

### Body Parameters

```json
{
  "numeroConsultorio": 345,
  "fechaAdmision": "2024-08-18",
  "paciente": {
    "idPaciente": 4,
    "eps": {
      "idEps": 243
    }
  },
  "personal": {
    "idPersonal": 4106,
    "dependencia": {
      "idDependencia": 1402,
      "institucion": {
        "idInstitucion": 102
      }
    }
  }
}
```

### Respuesta exitosa `http status 200 OK`

```json
{
  "numeroConsultorio": 345,
  "fechaAdmision": "2024-08-18",
  "paciente": {
    "idPaciente": 4,
    "nombrePaciente": "Nuevo",
    "apellidoPaciente": "Paciente",
    "direccionPaciente": "Dirección nuevo paciente",
    "telefonoPaciente": "123456789",
    "emailPaciente": "nuevo.paciente@correo.com",
    "eps": {
      "idEps": 241,
      "nombreEps": "Todo Salud (dato actualizado)",
      "telefonoEps": "3194713365",
      "emailEps": "cleptosalud.eps@mail.com"
    }
  },
  "personal": {
    "idPersonal": 4106,
    "nombrePersonal": "Juan",
    "apellidoPersonal": "Tello",
    "telefonoPersonal": "3254789632",
    "emailPersonal": "juan.tello@correo.com",
    "dependencia": {
      "idDependencia": 1402,
      "nombreDependencia": "Laboratorio Clínico",
      "institucion": {
        "idInstitucion": 102,
        "nombreInstitucion": "Centro Médico Quahog",
        "direccionInstitucion": "Calle Evergreen 742",
        "telefonoInstitucion": "6063218277",
        "codigoPostal": "047025"
      }
    }
  }
}
```

### Respuesta con campos vacíos `http status 400 Bad Request`

```json
[
  "El número del consultorio no puede estar vacío",
  "La fecha de admisión no puede estar vacía",
  "El ID del paciente no puede estar vacío",
  "El ID de la EPS no puede estar vacío",
  "El ID del personal no puede estar vacío",
  "El ID de la dependencia no puede estar vacío",
  "El ID de la institución no puede estar vacío"
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/consultorios/345",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/consultorios/345",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:lightsalmon">DELETE</p> Eliminar un registro

### Endpoint

```http request
/sipress-app/consultorios/{id}
```

Este endpoint permite la eliminación de un registro de la entidad Consultorio. El ID del registro debe ser especificado
en la petición.

### Request

Esta solicitud no requiere un cuerpo, solo necesita especificar el ID del registro en la petición HTTP.

### Response Body

La respuesta generada tendrá la siguiente estructura:

- `Eliminado`: Un valor booleano si la eliminación fue exitosa (`true`) o no (`false`).

### Response HTTP

- Status: 200
- Content-Type: application/json

### Ejemplo de respuesta:

``` json
{
    "Eliminado": boolean
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "Eliminado": true
}
```

### Respuesta con ID no encontrado `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "Consultorio no encontrado con el id: 345",
  "instance": "/sipress-app/consultorios/345",
  "description": "Error interno del servidor"
}
```

---

# Servicio de consulta

Los endpoints `/consultas` y `/consultas/{idPaciente}/{idDoctor}` permiten la gestión de información relacionada con la
entidad Consulta. Estos servicios permiten la creación, actualización, eliminación y consulta de los registros de
consultas.

## Autorización *`Bearer token`

---

## <p style="color:lightgreen">GET</p> Obtener todos los registros

### Endpoint

```http request
/sipress-app/consultas
```

Este endpoint permite recuperar todos los registros de la entidad consulta de la base de datos.

### Request

Para hacer efectiva la solicitud es necesario el uso del token de tipo bearer que se obtiene al iniciar sesión.

### Response

- Status: 200
- Content-Type: application/json

### Ejemplo de respuesta:

``` json
[
    {
        "consultaPK": {
            "pacienteId": 0,
            "doctorId": 0
        },
        "fechaConsulta": "",
        "horaConsulta": "",
        "doctor": {
            "idDoctor": 0,
            "nombreDoctor": "",
            "apellidoDoctor": "",
            "telefonoDoctor": "",
            "emailDoctor": "",
            "dependencia": {
                "idDependencia": 0,
                "nombreDependencia": "",
                "institucion": {
                    "idInstitucion": 0,
                    "nombreInstitucion": "",
                    "direccionInstitucion": "",
                    "telefonoInstitucion": "",
                    "codigoPostal": ""
                }
            }
        },
        "paciente": {
            "idPaciente": 0,
            "nombrePaciente": "",
            "apellidoPaciente": "",
            "direccionPaciente": "",
            "telefonoPaciente": "",
            "emailPaciente": "",
            "eps": {
                "idEps": 0,
                "nombreEps": "",
                "telefonoEps": "",
                "emailEps": ""
            }
        }
    }
]
```

## Autorización *`Bearer token`

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
[
  {
    "consultaPK": {
      "pacienteId": 1,
      "doctorId": 2001
    },
    "fechaConsulta": "2024-08-14",
    "horaConsulta": "11:20:00",
    "doctor": {
      "idDoctor": 2001,
      "nombreDoctor": "Alexander",
      "apellidoDoctor": "Ferrara",
      "telefonoDoctor": "3154745582",
      "emailDoctor": "alexander.ferrara@mail.com",
      "dependencia": {
        "idDependencia": 1408,
        "nombreDependencia": "Psiquiatría",
        "institucion": {
          "idInstitucion": 102,
          "nombreInstitucion": "Centro Médico Quahog",
          "direccionInstitucion": "Calle Evergreen 742",
          "telefonoInstitucion": "6063218277",
          "codigoPostal": "047025"
        }
      }
    },
    "paciente": {
      "idPaciente": 1,
      "nombrePaciente": "Sara Fernanda (dato actualizado)",
      "apellidoPaciente": "Tabares",
      "direccionPaciente": "Dirección Actualizada 4514",
      "telefonoPaciente": "3144421001",
      "emailPaciente": "correo.actualizado@mail.com",
      "eps": {
        "idEps": 243,
        "nombreEps": "Medicalsalud",
        "telefonoEps": "3226987543",
        "emailEps": "medisalud.eps@mail.com"
      }
    }
  },
  {
    "consultaPK": {
      "pacienteId": 2,
      "doctorId": 2004
    },
    "fechaConsulta": "2024-08-30",
    "horaConsulta": "11:00:00",
    "doctor": {
      "idDoctor": 2004,
      "nombreDoctor": "Ramiro",
      "apellidoDoctor": "Chapatín",
      "telefonoDoctor": "6013410111",
      "emailDoctor": "ramiro.chapatin@correo.com",
      "dependencia": {
        "idDependencia": 1408,
        "nombreDependencia": "Psiquiatría",
        "institucion": {
          "idInstitucion": 102,
          "nombreInstitucion": "Centro Médico Quahog",
          "direccionInstitucion": "Calle Evergreen 742",
          "telefonoInstitucion": "6063218277",
          "codigoPostal": "047025"
        }
      }
    },
    "paciente": {
      "idPaciente": 2,
      "nombrePaciente": "Diego",
      "apellidoPaciente": "Norrea",
      "direccionPaciente": "Barrio Nueva Vista 97-458",
      "telefonoPaciente": "3295478522",
      "emailPaciente": "diego.norrea@mail.com",
      "eps": {
        "idEps": 242,
        "nombreEps": "Farsitas",
        "telefonoEps": "3271475896",
        "emailEps": "farsitas.eps@mail.com"
      }
    }
  },
  {
    "consultaPK": {
      "pacienteId": 4,
      "doctorId": 2001
    },
    "fechaConsulta": "2024-08-21",
    "horaConsulta": "03:00:00",
    "doctor": {
      "idDoctor": 2001,
      "nombreDoctor": "Alexander",
      "apellidoDoctor": "Ferrara",
      "telefonoDoctor": "3154745582",
      "emailDoctor": "alexander.ferrara@mail.com",
      "dependencia": {
        "idDependencia": 1408,
        "nombreDependencia": "Psiquiatría",
        "institucion": {
          "idInstitucion": 102,
          "nombreInstitucion": "Centro Médico Quahog",
          "direccionInstitucion": "Calle Evergreen 742",
          "telefonoInstitucion": "6063218277",
          "codigoPostal": "047025"
        }
      }
    },
    "paciente": {
      "idPaciente": 4,
      "nombrePaciente": "Nuevo",
      "apellidoPaciente": "Paciente",
      "direccionPaciente": "Dirección nuevo paciente",
      "telefonoPaciente": "123456789",
      "emailPaciente": "nuevo.paciente@correo.com",
      "eps": {
        "idEps": 241,
        "nombreEps": "Todo Salud (dato actualizado)",
        "telefonoEps": "3194713365",
        "emailEps": "cleptosalud.eps@mail.com"
      }
    }
  },
  {
    "consultaPK": {
      "pacienteId": 4,
      "doctorId": 2002
    },
    "fechaConsulta": "2024-08-23",
    "horaConsulta": "15:00:00",
    "doctor": {
      "idDoctor": 2002,
      "nombreDoctor": "Gustavo",
      "apellidoDoctor": "Restrepo",
      "telefonoDoctor": "3214471023",
      "emailDoctor": "gustavo.restrepo@mail.com",
      "dependencia": {
        "idDependencia": 1403,
        "nombreDependencia": "Radiología",
        "institucion": {
          "idInstitucion": 103,
          "nombreInstitucion": "Clínica Salutia IPS",
          "direccionInstitucion": "Avenida Laputa 4152",
          "telefonoInstitucion": "6012541133",
          "codigoPostal": "023658"
        }
      }
    },
    "paciente": {
      "idPaciente": 4,
      "nombrePaciente": "Nuevo",
      "apellidoPaciente": "Paciente",
      "direccionPaciente": "Dirección nuevo paciente",
      "telefonoPaciente": "123456789",
      "emailPaciente": "nuevo.paciente@correo.com",
      "eps": {
        "idEps": 241,
        "nombreEps": "Todo Salud (dato actualizado)",
        "telefonoEps": "3194713365",
        "emailEps": "cleptosalud.eps@mail.com"
      }
    }
  }
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/consultas",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/consultas",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:lightgreen">GET</p> Obtener un registro

### Endpoint

```http request
/sipress-app/consultas/{idPaciente}/{idDoctor}
```

Este endpoint permite la recuperación de un solo registro de la entidad Consulta de la base de datos.

### Request

Para la solicitud es necesario haber iniciado sesión y usar el token de tipo bearer. Se debe tener en cuenta que esta
entidad no tiene un identificador propio, por lo que es necesario usar los ID de la entidad Paciente y Doctor, los
cuales deben ser especificados en este orden en la petición HTTP.

### Response

La respuesta mostrará la información con la siguiente estructura de tipo JSON:

``` json
{
    "consultaPK": {
        "pacienteId": 0,
        "doctorId": 0
    },
    "fechaConsulta": "",
    "horaConsulta": "",
    "doctor": {
        "idDoctor": 0,
        "nombreDoctor": "",
        "apellidoDoctor": "",
        "telefonoDoctor": "",
        "emailDoctor": "",
        "dependencia": {
            "idDependencia": 0,
            "nombreDependencia": "",
            "institucion": {
                "idInstitucion": 0,
                "nombreInstitucion": "",
                "direccionInstitucion": "",
                "telefonoInstitucion": "",
                "codigoPostal": ""
            }
        }
    },
    "paciente": {
        "idPaciente": 0,
        "nombrePaciente": "",
        "apellidoPaciente": "",
        "direccionPaciente": "",
        "telefonoPaciente": "",
        "emailPaciente": "",
        "eps": {
            "idEps": 0,
            "nombreEps": "",
            "telefonoEps": "",
            "emailEps": ""
        }
    }
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "consultaPK": {
    "pacienteId": 2,
    "doctorId": 2004
  },
  "fechaConsulta": "18-08-2024",
  "horaConsulta": "12:00:00",
  "doctor": {
    "idDoctor": 2004,
    "nombreDoctor": "Ramiro",
    "apellidoDoctor": "Chapatín",
    "telefonoDoctor": "6013410111",
    "emailDoctor": "ramiro.chapatin@correo.com",
    "dependencia": {
      "idDependencia": 1408,
      "nombreDependencia": "Psiquiatría",
      "institucion": {
        "idInstitucion": 102,
        "nombreInstitucion": "Centro Médico Quahog",
        "direccionInstitucion": "Calle Evergreen 742",
        "telefonoInstitucion": "6063218277",
        "codigoPostal": "047025"
      }
    }
  },
  "paciente": {
    "idPaciente": 2,
    "nombrePaciente": "Diego",
    "apellidoPaciente": "Norrea",
    "direccionPaciente": "Barrio Nueva Vista 97-458",
    "telefonoPaciente": "3295478522",
    "emailPaciente": "diego.norrea@mail.com",
    "eps": {
      "idEps": 242,
      "nombreEps": "Farsitas",
      "telefonoEps": "3271475896",
      "emailEps": "farsitas.eps@mail.com"
    }
  }
}
```

### Respuesta con ID no encontrado `http status 404 Not Found`

```json
{
  "type": "about:blank",
  "title": "Not Found",
  "status": 404,
  "detail": "Consulta no encontrada",
  "instance": "/sipress-app/consultas/2/2004",
  "description": "No se encontró la consulta con el ID del paciente 2 y el ID del doctor 2004"
}
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/consultas/2/2004",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/consultas/2/2004",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:khaki">POST</p> Agregar un nuevo registro

### Endpoint

```http request
/sipress-app/consultas
```

Este endpoint permite el registro de un nuevo objeto de la entidad Consulta.

### Request Body

La solicitud requiere el inicio de sesión para obtener el token de tipo bearer, y agregar la siguiente estructura de
datos en un formato de tipo JSON:

- `consultaPK` (object): Un objeto de tipo ConsultaPK asociado con la consulta:
    - `pacienteId` (number): El número de identificación del paciente.
    - `doctorId` (number): El número de identificación del doctor.
- `fechaConsulta` (string): La fecha de la consulta.
- `horaConsulta` (string): La hora de la consulta.
- `doctor` (object): Un objeto de tipo Doctor asociado con la consulta:
    - `idDoctor` (number): El número de identificación del doctor.
    - `dependencia` (object): Un objeto de tipo Dependencia asociado con el doctor:
        - `idDependencia` (number): El número de identificación de la dependencia.
        - `institucion` (object): Un objeto de tipo Institución asociado con la dependencia:
            - `idInstitucion` (number): El número de identificación de la institución.
        - `paciente` (object): Un objeto de tipo Paciente asociado con la consulta:
            - `idPaciente` (number): El número de identificación del paciente.
            - `eps` (object): Un objeto de tipo EPS asociado con el paciente:
                - `idEps` (number): El número de identificación de la EPS.

### Response

La respuesta se mostrará en un esquema de tipo JSON con la siguiente estructura:

``` json
{
    "consultaPK": {
        "pacienteId": 0,
        "doctorId": 0
    },
    "fechaConsulta": "",
    "horaConsulta": "",
    "doctor": {
        "idDoctor": 0,
        "nombreDoctor": "",
        "apellidoDoctor": "",
        "telefonoDoctor": "",
        "emailDoctor": "",
        "dependencia": {
            "idDependencia": 0,
            "nombreDependencia": "",
            "institucion": {
                "idInstitucion": 0,
                "nombreInstitucion": "",
                "direccionInstitucion": "",
                "telefonoInstitucion": "",
                "codigoPostal": ""
            }
        }
    },
    "paciente": {
        "idPaciente": 0,
        "nombrePaciente": "",
        "apellidoPaciente": "",
        "direccionPaciente": "",
        "telefonoPaciente": "",
        "emailPaciente": "",
        "eps": {
            "idEps": 0,
            "nombreEps": "",
            "telefonoEps": "",
            "emailEps": ""
        }
    }
}
```

## Autorización *`Bearer token`

---

## Ejemplos

### Body Parameters

```json
{
  "consultaPK": {
    "pacienteId": 4,
    "doctorId": 2001
  },
  "fechaConsulta": "2024-08-21",
  "horaConsulta": "08:00:00",
  "doctor": {
    "idDoctor": 2001,
    "dependencia": {
      "idDependencia": 1408,
      "institucion": {
        "idInstitucion": 102
      }
    }
  },
  "paciente": {
    "idPaciente": 4,
    "eps": {
      "idEps": 241
    }
  }
}
```

### Respuesta exitosa `http status 200 OK`

```json
{
  "consultaPK": {
    "pacienteId": 4,
    "doctorId": 2001
  },
  "fechaConsulta": "2024-08-21",
  "horaConsulta": "03:00:00",
  "doctor": {
    "idDoctor": 2001,
    "nombreDoctor": "Alexander",
    "apellidoDoctor": "Ferrara",
    "telefonoDoctor": "3154745582",
    "emailDoctor": "alexander.ferrara@mail.com",
    "dependencia": {
      "idDependencia": 1408,
      "nombreDependencia": "Psiquiatría",
      "institucion": {
        "idInstitucion": 102,
        "nombreInstitucion": "Centro Médico Quahog",
        "direccionInstitucion": "Calle Evergreen 742",
        "telefonoInstitucion": "6063218277",
        "codigoPostal": "047025"
      }
    }
  },
  "paciente": {
    "idPaciente": 4,
    "nombrePaciente": "Nuevo",
    "apellidoPaciente": "Paciente",
    "direccionPaciente": "Dirección nuevo paciente",
    "telefonoPaciente": "123456789",
    "emailPaciente": "nuevo.paciente@correo.com",
    "eps": {
      "idEps": 241,
      "nombreEps": "Todo Salud (dato actualizado)",
      "telefonoEps": "3194713365",
      "emailEps": "cleptosalud.eps@mail.com"
    }
  }
}
```

### Respuesta con campos vacíos `http status 400 Bad Request`

```json
[
  "El ID del paciente no puede estar vacío",
  "El ID del doctor no puede estar vacío",
  "La fecha de la consulta no puede estar vacía",
  "La hora de la consulta no puede estar vacía"
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/consultas",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/consultas",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:cornflowerblue">PUT</p> Actualizar un registro

### Endpoint

```http request
/sipress-app/consultas/{idPaciente}/{idDoctor}
```

Este endpoint permite la actualización de un registro existente de la entidad Consulta.

### Request Body

La solicitud requiere el inicio de sesión para obtener el token de tipo bearer, especificar el ID del registro que se
quiere modificar en la petición HTTP, y agregar la siguiente estructura de datos en un formato de tipo JSON:

- `consultaPK` (object): Un objeto de tipo ConsultaPK asociado con la consulta:
    - `pacienteId` (number): El número de identificación del paciente.
    - `doctorId` (number): El número de identificación del doctor.
- `fechaConsulta` (string): La fecha de la consulta.
- `horaConsulta` (string): La hora de la consulta.
- `doctor` (object): Un objeto de tipo Doctor asociado con la consulta:
    - `idDoctor` (number): El número de identificación del doctor.
    - `dependencia` (object): Un objeto de tipo Dependencia asociado con el doctor:
        - `idDependencia` (number): El número de identificación de la dependencia.
        - `institucion` (object): Un objeto de tipo Institución asociado con la dependencia:
            - `idInstitucion` (number): El número de identificación de la institución.
        - `paciente` (object): Un objeto de tipo Paciente asociado con la consulta:
            - `idPaciente` (number): El número de identificación del paciente.
            - `eps` (object): Un objeto de tipo EPS asociado con el paciente:
                - `idEps` (number): El número de identificación de la EPS.

### Response

La respuesta se mostrará en un esquema de tipo JSON con la siguiente estructura:

``` json
{
    "consultaPK": {
        "pacienteId": 0,
        "doctorId": 0
    },
    "fechaConsulta": "",
    "horaConsulta": "",
    "doctor": {
        "idDoctor": 0,
        "nombreDoctor": "",
        "apellidoDoctor": "",
        "telefonoDoctor": "",
        "emailDoctor": "",
        "dependencia": {
            "idDependencia": 0,
            "nombreDependencia": "",
            "institucion": {
                "idInstitucion": 0,
                "nombreInstitucion": "",
                "direccionInstitucion": "",
                "telefonoInstitucion": "",
                "codigoPostal": ""
            }
        }
    },
    "paciente": {
        "idPaciente": 0,
        "nombrePaciente": "",
        "apellidoPaciente": "",
        "direccionPaciente": "",
        "telefonoPaciente": "",
        "emailPaciente": "",
        "eps": {
            "idEps": 0,
            "nombreEps": "",
            "telefonoEps": "",
            "emailEps": ""
        }
    }
}
```

### Importante

Se debe tener precaución con el uso de este endpoint, ya que no solo reemplaza los valores originales del registro
especificado, sino que también puede cambiar datos del paciente, el doctor, la dependencia y la institución asociada.

## Autorización ```Bearer token```

---

## Ejemplos

### Body Parameters

```json
{
  "consultaPK": {
    "pacienteId": 4,
    "doctorId": 2001
  },
  "fechaConsulta": "2024-08-21",
  "horaConsulta": "03:00:00",
  "doctor": {
    "idDoctor": 2001,
    "dependencia": {
      "idDependencia": 1408,
      "institucion": {
        "idInstitucion": 102
      }
    }
  },
  "paciente": {
    "idPaciente": 4,
    "eps": {
      "idEps": 241
    }
  }
}
```

### Respuesta exitosa `http status 200 OK`

```json
{
  "consultaPK": {
    "pacienteId": 4,
    "doctorId": 2001
  },
  "fechaConsulta": "2024-08-21",
  "horaConsulta": "03:00:00",
  "doctor": {
    "idDoctor": 2001,
    "nombreDoctor": "Alexander",
    "apellidoDoctor": "Ferrara",
    "telefonoDoctor": "3154745582",
    "emailDoctor": "alexander.ferrara@mail.com",
    "dependencia": {
      "idDependencia": 1408,
      "nombreDependencia": "Psiquiatría",
      "institucion": {
        "idInstitucion": 102,
        "nombreInstitucion": "Centro Médico Quahog",
        "direccionInstitucion": "Calle Evergreen 742",
        "telefonoInstitucion": "6063218277",
        "codigoPostal": "047025"
      }
    }
  },
  "paciente": {
    "idPaciente": 4,
    "nombrePaciente": "Nuevo",
    "apellidoPaciente": "Paciente",
    "direccionPaciente": "Dirección nuevo paciente",
    "telefonoPaciente": "123456789",
    "emailPaciente": "nuevo.paciente@correo.com",
    "eps": {
      "idEps": 241,
      "nombreEps": "Todo Salud (dato actualizado)",
      "telefonoEps": "3194713365",
      "emailEps": "cleptosalud.eps@mail.com"
    }
  }
}
```

### Respuesta con campos vacíos `http status 400 Bad Request`

```json
[
  "El ID del paciente no puede estar vacío",
  "El ID del doctor no puede estar vacío",
  "La fecha de la consulta no puede estar vacía",
  "La hora de la consulta no puede estar vacía"
]
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/consultas/4/2001",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/consultas/4/2001",
  "description": "El token JWT ha expirado"
}
```

---

## <p style="color:lightsalmon">DELETE</p> Eliminar un registro

### Endpoint

```http request
/sipress-app/consultas/{idPaciente}/{idDoctor}
```

Este endpoint permite la eliminación de un registro de la entidad Consulta. Los ID del paciente y del doctor deben ser
especificados en la petición.

### Request

Esta solicitud no requiere un cuerpo, solo necesita especificar los ID del paciente y del doctor en la petición HTTP.

### Response Body

La respuesta generada tendrá la siguiente estructura:

- `Eliminado`: Un valor booleano si la eliminación fue exitosa (`true`) o no (`false`).

### Response HTTP

- Status: 200
- Content-Type: application/json

### Ejemplo de respuesta:

``` json
{
    "Eliminado": boolean
}
```

## Autorización

---

## Ejemplos

### Respuesta exitosa `http status 200 OK`

```json
{
  "Eliminado": true
}
```

### Respuesta con ID no encontrado `http status 404 Not Found`

```json
{
  "type": "about:blank",
  "title": "Not Found",
  "status": 404,
  "detail": "Consulta no encontrada",
  "instance": "/sipress-app/consultas/4/2001",
  "description": "No se encontró la consulta con el ID del paciente 4 y el ID del doctor 2001"
}
```

### Respuesta con token inválido `http status 500 Internal Server Error`

```json
{
  "type": "about:blank",
  "title": "Internal Server Error",
  "status": 500,
  "detail": "JWT strings must contain exactly 2 period characters. Found: 0",
  "instance": "/sipress-app/consultas/4/2001",
  "description": "Error interno del servidor"
}
```

### Respuesta con token caducado `http status 403 Forbidden`

```json
{
  "type": "about:blank",
  "title": "Forbidden",
  "status": 403,
  "detail": "JWT expired at 2024-08-19T16:31:46Z. Current time: 2024-08-19T17:27:28Z, a difference of 3342019 milliseconds.  Allowed clock skew: 0 milliseconds.",
  "instance": "/sipress-app/consultas/4/2001",
  "description": "El token JWT ha expirado"
}
```

---

# Referencias

- [Spring Boot Validation](https://spring.io/guides/gs/validating-form-input/)
- [Spring Boot JWT](https://www.javainuse.com/spring/boot-jwt)
- [Implement JWT authentication in a Spring Boot 3 application](https://medium.com/@tericcabrel/implement-jwt-authentication-in-a-spring-boot-3-application-5839e4fd8fac)

---

# Licencia

[LICENSE.md](../LICENSE.md)

---

# Autor

## Mauricio Alberto Monroy Calle

- [GitHub](https://github.com/MauricioMonroy)

### Contacto: `mauriciomonroy@live.cl` o `mauricio.monroy0@soy.sena.edu.co`

---

# Fecha

2024-09-13

---

# Versión

1.0.0

---

