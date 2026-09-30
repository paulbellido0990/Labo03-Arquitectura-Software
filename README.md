<div align="center">

# 🎓 Sistema de Gestión Académica

### Arquitectura de Software

**Universidad Nacional de San Cristóbal de Huamanga**  
**Escuela Profesional de Ingeniería de Sistemas**

<br>

Sistema desarrollado en **Java** aplicando una arquitectura organizada por capas para la gestión de estudiantes y cursos, utilizando archivos **JSON** como mecanismo de persistencia.

</div>

---

## 📌 Descripción del proyecto

El **Sistema de Gestión Académica** es una aplicación de consola desarrollada en Java que permite administrar información relacionada con estudiantes y cursos.

El sistema implementa operaciones CRUD:

- Crear registros.
- Consultar registros.
- Actualizar información.
- Eliminar registros.
- Buscar información mediante identificadores.

La aplicación fue organizada siguiendo una arquitectura por capas, separando la presentación, la lógica de negocio y el acceso a datos.

---

## ⚙️ Funcionalidades

### 👨‍🎓 Gestión de estudiantes

El módulo de estudiantes permite:

- Registrar estudiantes.
- Listar estudiantes registrados.
- Buscar estudiantes por ID.
- Actualizar datos de estudiantes.
- Eliminar estudiantes.
- Validar identificadores.
- Evitar IDs duplicados.
- Validar correos electrónicos.
- Evitar correos duplicados.
- Validar campos obligatorios.

### 📚 Gestión de cursos

El módulo de cursos permite:

- Registrar cursos.
- Listar cursos registrados.
- Buscar cursos por ID.
- Actualizar información de cursos.
- Eliminar cursos.
- Evitar IDs duplicados.
- Validar el número de créditos.
- Validar nombre del curso.
- Validar docente.
- Controlar campos obligatorios.

---

## 🏗️ Arquitectura del sistema

El sistema utiliza una arquitectura organizada en tres capas principales:

```text
┌────────────────────────────────────┐
│          PRESENTACIÓN              │
│                                    │
│  Main.java                         │
│  EstudianteUI.java                 │
│  CursoUI.java                      │
└────────────────┬───────────────────┘
                 │
                 ▼
┌────────────────────────────────────┐
│        LÓGICA DE NEGOCIO           │
│                                    │
│  Estudiante.java                   │
│  Curso.java                        │
│  EstudianteServicio.java           │
│  CursoServicio.java                │
└────────────────┬───────────────────┘
                 │
                 ▼
┌────────────────────────────────────┐
│          ACCESO A DATOS            │
│                                    │
│  EstudianteRepositorio.java        │
│  CursoRepositorio.java             │
└────────────────┬───────────────────┘
                 │
                 ▼
┌────────────────────────────────────┐
│           PERSISTENCIA             │
│                                    │
│  estudiantes.json                  │
│  cursos.json                       │
└────────────────────────────────────┘
```

### Flujo general

```text
Usuario
   │
   ▼
Main
   │
   ▼
Presentación
   │
   ▼
Servicios
   │
   ▼
Repositorios
   │
   ▼
Archivos JSON
```

La división por capas permite mantener separadas las responsabilidades del sistema y facilita su mantenimiento y evolución.

---

## 📂 Estructura del proyecto

```text
SistemaGestionAcademica/
│
├── data/
│   ├── estudiantes.json
│   └── cursos.json
│
├── src/
│   └── main/
│       ├── java/
│       │   └── org/
│       │       └── example/
│       │           │
│       │           ├── Main.java
│       │           │
│       │           ├── business/
│       │           │   ├── Estudiante.java
│       │           │   ├── Curso.java
│       │           │   ├── EstudianteServicio.java
│       │           │   └── CursoServicio.java
│       │           │
│       │           ├── data/
│       │           │   ├── EstudianteRepositorio.java
│       │           │   └── CursoRepositorio.java
│       │           │
│       │           └── presentacion/
│       │               ├── EstudianteUI.java
│       │               └── CursoUI.java
│       │
│       └── resources/
│
├── .gitignore
├── pom.xml
└── README.md
```

---

## 🧩 Responsabilidad de las capas

<table>
    <thead>
        <tr>
            <th>Capa</th>
            <th>Responsabilidad</th>
            <th>Clases principales</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Presentación</td>
            <td>Interacción entre el usuario y el sistema mediante consola.</td>
            <td>Main, EstudianteUI, CursoUI</td>
        </tr>
        <tr>
            <td>Negocio</td>
            <td>Aplicación de reglas, validaciones y operaciones CRUD.</td>
            <td>EstudianteServicio, CursoServicio</td>
        </tr>
        <tr>
            <td>Modelo</td>
            <td>Representación de las entidades utilizadas por el sistema.</td>
            <td>Estudiante, Curso</td>
        </tr>
        <tr>
            <td>Datos</td>
            <td>Lectura y escritura de información almacenada.</td>
            <td>EstudianteRepositorio, CursoRepositorio</td>
        </tr>
        <tr>
            <td>Persistencia</td>
            <td>Almacenamiento permanente de los registros.</td>
            <td>estudiantes.json, cursos.json</td>
        </tr>
    </tbody>
</table>

---

## 💻 Tecnologías utilizadas

<table>
    <thead>
        <tr>
            <th>Tecnología</th>
            <th>Uso</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Java 25</td>
            <td>Lenguaje principal del proyecto.</td>
        </tr>
        <tr>
            <td>Apache Maven 3.9.16</td>
            <td>Gestión de dependencias y compilación.</td>
        </tr>
        <tr>
            <td>Gson 2.11.0</td>
            <td>Serialización y deserialización de archivos JSON.</td>
        </tr>
        <tr>
            <td>JSON</td>
            <td>Persistencia de estudiantes y cursos.</td>
        </tr>
        <tr>
            <td>Git</td>
            <td>Control de versiones.</td>
        </tr>
        <tr>
            <td>GitHub</td>
            <td>Repositorio remoto y seguimiento del proyecto.</td>
        </tr>
        <tr>
            <td>Visual Studio Code</td>
            <td>Entorno utilizado para el desarrollo.</td>
        </tr>
    </tbody>
</table>

---

## 📦 Dependencias

El proyecto utiliza **Gson** para transformar objetos Java en JSON y convertir información JSON nuevamente en objetos Java.

Dependencia configurada en `pom.xml`:

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.11.0</version>
</dependency>
```

---

## 📋 Requisitos

Antes de ejecutar el proyecto se debe contar con:

- Java Development Kit 25.
- Apache Maven.
- Git.
- Visual Studio Code o cualquier IDE compatible con Java.

Para verificar Java:

```powershell
java -version
```

Ejemplo:

```text
java version "25.0.4.1"
```

Para comprobar Maven:

```powershell
mvn -version
```

Ejemplo:

```text
Apache Maven 3.9.16
Java version: 25.0.4.1
```

---

## 🚀 Instalación

Clonar el repositorio:

```bash
git clone https://github.com/paulbellido0990/Labo03-Arquitectura-Software.git
```

Ingresar a la carpeta:

```bash
cd Labo03-Arquitectura-Software
```

---

## 🔨 Compilación

Para limpiar y compilar el proyecto:

```bash
mvn clean compile
```

Cuando la compilación es correcta Maven muestra:

```text
[INFO] BUILD SUCCESS
```

---

## ▶️ Ejecución

La clase principal del sistema es:

```text
org.example.Main
```

Desde Visual Studio Code se puede abrir `Main.java` y utilizar:

```text
Run Java
```

También puede ejecutarse utilizando Maven:

```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```

---

## 🖥️ Interfaz principal

Al ejecutar el programa se muestra un menú similar al siguiente:

```text
╔══════════════════════════════════════════════════╗
║                                                  ║
║          SISTEMA DE GESTIÓN ACADÉMICA            ║
║                                                  ║
║        Arquitectura de Software - UNSCH          ║
║                                                  ║
╚══════════════════════════════════════════════════╝

╔══════════════════════════════════════════════════╗
║               MENÚ PRINCIPAL                    ║
╠══════════════════════════════════════════════════╣
║                                                  ║
║   [1] Gestión de estudiantes                     ║
║   [2] Gestión de cursos                          ║
║   [0] Salir del sistema                          ║
║                                                  ║
╚══════════════════════════════════════════════════╝

Seleccione una opción:
```

---

## 👨‍🎓 Módulo de estudiantes

Ejemplo del menú:

```text
╔══════════════════════════════════════════════╗
║          GESTIÓN DE ESTUDIANTES             ║
╠══════════════════════════════════════════════╣
║  [1] Registrar estudiante                   ║
║  [2] Listar estudiantes                     ║
║  [3] Buscar estudiante                      ║
║  [4] Actualizar estudiante                  ║
║  [5] Eliminar estudiante                    ║
║  [0] Volver al menú principal               ║
╚══════════════════════════════════════════════╝
```

---

## 📚 Módulo de cursos

Ejemplo del menú:

```text
╔══════════════════════════════════════════════╗
║              GESTIÓN DE CURSOS              ║
╠══════════════════════════════════════════════╣
║  [1] Registrar curso                        ║
║  [2] Listar cursos                          ║
║  [3] Buscar curso                           ║
║  [4] Actualizar curso                       ║
║  [5] Eliminar curso                         ║
║  [0] Volver al menú principal               ║
╚══════════════════════════════════════════════╝
```

---

## 💾 Persistencia

Los datos se almacenan dentro de la carpeta:

```text
data/
```

### estudiantes.json

```json
[
  {
    "id": 1,
    "nombre": "Paul Modesto Llallahui Bellido",
    "correo": "paul@gmail.com"
  }
]
```

### cursos.json

```json
[
  {
    "id": 101,
    "nombre": "Arquitectura de Software",
    "creditos": 4,
    "docente": "Juan Pérez"
  }
]
```

---

## ✅ Validaciones implementadas

El sistema incorpora validaciones para mejorar la integridad de los datos.

### Estudiantes

```text
✓ ID mayor que cero
✓ ID no duplicado
✓ Nombre obligatorio
✓ Correo obligatorio
✓ Formato válido de correo
✓ Correo no duplicado
```

### Cursos

```text
✓ ID mayor que cero
✓ ID no duplicado
✓ Nombre obligatorio
✓ Créditos mayores que cero
✓ Docente obligatorio
```

Además, la interfaz controla entradas numéricas incorrectas para evitar que el programa finalice inesperadamente.

---

## 🔄 Operaciones CRUD

<table>
    <thead>
        <tr>
            <th>Operación</th>
            <th>Estudiantes</th>
            <th>Cursos</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Registrar</td>
            <td>✅</td>
            <td>✅</td>
        </tr>
        <tr>
            <td>Listar</td>
            <td>✅</td>
            <td>✅</td>
        </tr>
        <tr>
            <td>Buscar</td>
            <td>✅</td>
            <td>✅</td>
        </tr>
        <tr>
            <td>Actualizar</td>
            <td>✅</td>
            <td>✅</td>
        </tr>
        <tr>
            <td>Eliminar</td>
            <td>✅</td>
            <td>✅</td>
        </tr>
    </tbody>
</table>

---

## 📐 Principios aplicados

Durante el desarrollo se buscó mantener:

- Separación de responsabilidades.
- Organización modular.
- Bajo acoplamiento entre presentación y persistencia.
- Encapsulamiento de entidades.
- Validación en la capa de negocio.
- Persistencia independiente de la interfaz.
- Código organizado por paquetes.

---

## 🔮 Posibles mejoras futuras

El proyecto puede evolucionar incorporando:

- Persistencia con MySQL o PostgreSQL.
- Interfaces para los repositorios.
- Inyección de dependencias.
- Pruebas unitarias con JUnit.
- Interfaz gráfica.
- API REST.
- Autenticación de usuarios.
- Gestión de matrículas.
- Relación entre estudiantes y cursos.
- Manejo personalizado de excepciones.

---

## 📌 Estado del proyecto

```text
Compilación      ✅ Correcta
CRUD estudiantes ✅ Implementado
CRUD cursos      ✅ Implementado
Persistencia     ✅ JSON
Validaciones     ✅ Implementadas
Arquitectura     ✅ Organizada por capas
```

---

<div align="center">

## 👨‍💻 Autor

### Paul Modesto Llallahui Bellido

**Ingeniería de Sistemas**  
**Universidad Nacional de San Cristóbal de Huamanga**

<br>

Proyecto académico desarrollado para el curso de **Arquitectura de Software**.

</div>
