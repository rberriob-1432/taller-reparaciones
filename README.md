# Taller de Reparaciones

Sistema de gestión de un taller de reparación de vehículos, desarrollado en Java. Permite organizar la información de clientes, vehículos y órdenes de servicio mediante una aplicación de consola.

## Descripción del proyecto

El proyecto implementa una estructura organizada por capas, separando las responsabilidades del dominio, la lógica de negocio, la persistencia y la presentación.

Su propósito es aplicar principios de programación orientada a objetos y buenas prácticas de diseño de software en el desarrollo de un sistema de gestión.

## Funcionalidades

- Gestión de clientes.
- Registro y administración de vehículos.
- Creación y gestión de órdenes de servicio.
- Manejo de estados, prioridades y tipos de servicio.
- Organización del código mediante separación de responsabilidades.

## Arquitectura del proyecto

El sistema está organizado en las siguientes capas:

| Capa | Responsabilidad |
|---|---|
| Domain | Contiene los modelos y enumeraciones del dominio. |
| Application | Implementa los servicios y define los puertos de salida. |
| Infrastructure | Proporciona las implementaciones de persistencia en memoria. |
| Presentation | Gestiona la interacción con el usuario mediante controladores y menús. |

### Estructura del proyecto

```text
src/
└── main/
    └── java/
        └── main/
            ├── Main.java
            ├── domain/
            │   ├── enums/
            │   └── model/
            ├── application/
            │   ├── port/
            │   │   └── out/
            │   └── service/
            ├── infrastructure/
            │   └── persistence/
            │       └── memory/
            └── presentation/
```

## Tecnologías utilizadas

- **Java 21:** lenguaje de programación.
- **Apache Maven:** gestión de dependencias y construcción del proyecto.
- **JUnit 5:** framework de pruebas unitarias configurado.
- **Git:** control de versiones.
- **GitHub:** alojamiento del repositorio.

## Requisitos previos

Antes de ejecutar el proyecto, es necesario contar con:

- JDK 21 o superior.
- Apache Maven.
- Git.

## Instalación y ejecución

Clonar el repositorio:

```bash
git clone https://github.com/rberriob-1432/taller-reparaciones.git
```

Ingresar al directorio:

```bash
cd taller-reparaciones
```

Compilar el proyecto:

```bash
mvn clean compile
```

Ejecutar la aplicación:

```bash
mvn exec:java -Dexec.mainClass="main.java.Main"
```

## Pruebas

Para ejecutar las pruebas configuradas en el proyecto:

```bash
mvn test
```

## Persistencia de datos

Actualmente, el proyecto utiliza repositorios en memoria para almacenar la información durante la ejecución de la aplicación.

Por esta razón, los datos registrados no se conservan después de cerrar el programa. La implementación de una base de datos constituye una posible ampliación futura.

## Objetivo académico

Este proyecto tiene como objetivo fortalecer los conocimientos sobre programación orientada a objetos, arquitectura de software, separación de responsabilidades, encapsulamiento y organización de aplicaciones Java.

## Autor

**Raul Alonso Berrio Blanquicett**

GitHub: [@rberriob-1432](https://github.com/rberriob-1432)