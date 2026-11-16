# Sistema de Gestión de Taller de Reparaciones

Sistema de gestión para un taller de reparación de vehículos, desarrollado en Java 21. Permite administrar clientes, vehículos y órdenes de servicio mediante una arquitectura organizada por responsabilidades y estructuras de datos implementadas manualmente.

## Objetivo

Desarrollar una aplicación que permita gestionar las operaciones principales de un taller de reparaciones, aplicando conceptos de programación orientada a objetos, estructuras de datos, algoritmos de búsqueda y organización del software.

## Tecnologías utilizadas

- **Java 21:** lenguaje de programación.
- **Maven:** gestión de dependencias y construcción del proyecto.
- **JUnit 5:** pruebas unitarias.
- **Git y GitHub:** control de versiones y alojamiento del código.

## Arquitectura del proyecto

El proyecto está organizado en diferentes paquetes, cada uno con una responsabilidad específica:

| Paquete | Responsabilidad |
|---|---|
| `main` | Punto de entrada de la aplicación. |
| `main.model` | Modelos y entidades del sistema. |
| `main.estructuras` | Implementaciones manuales de estructuras de datos. |
| `main.repository` | Almacenamiento y operaciones sobre los datos. |
| `main.service` | Lógica de negocio y validaciones. |
| `main.presentation` | Interacción con el usuario mediante el menú. |

## Estructuras de datos implementadas

El proyecto implementa estructuras de datos manualmente, sin utilizar las implementaciones equivalentes de colecciones de Java.

| Estructura | Funcionamiento |
|---|---|
| ArrayList | Almacenamiento secuencial mediante un arreglo dinámico. |
| LinkedList | Lista enlazada mediante nodos. |
| HashMap | Asociación de claves y valores mediante tablas hash. |
| HashSet | Almacenamiento de elementos únicos mediante hashing. |
| TreeMap | Almacenamiento de pares clave-valor mediante un árbol binario de búsqueda. |
| TreeSet | Conjunto de elementos únicos organizados mediante un árbol binario de búsqueda. |
| Queue | Administración de elementos bajo el principio FIFO. |
| Deque | Inserción y eliminación por ambos extremos. |
| PriorityQueue | Administración de elementos según su prioridad mediante un montículo binario. |

## Funcionalidades

### Gestión de vehículos
- Registrar vehículos.
- Buscar vehículos por placa.
- Actualizar información.
- Eliminar vehículos.

### Gestión de clientes
- Registrar clientes.
- Buscar clientes por identificación.
- Actualizar información.
- Eliminar clientes.

### Gestión de órdenes de servicio
- Registrar órdenes de reparación.
- Buscar órdenes por código.
- Actualizar órdenes.
- Eliminar órdenes.
- Cambiar el estado de una orden.
- Modificar su prioridad.
- Consultar órdenes organizadas por prioridad.

### Validaciones
- Evitar registros duplicados.
- Verificar que los clientes y vehículos estén registrados antes de asociarlos a una orden.
- Validar referencias entre las entidades del sistema.

## Modelo de prioridades

Las órdenes de servicio manejan cuatro niveles:

1. CRÍTICA
2. ALTA
3. MEDIA
4. BAJA

La cola de prioridad permite atender primero las órdenes con mayor nivel de urgencia.

## Estados de las órdenes

- PENDIENTE
- EN_DIAGNOSTICO
- EN_REPARACION
- FINALIZADA
- CANCELADA

## Requisitos

- JDK 21 o superior.
- Apache Maven 3.9 o superior.

## Instalación y ejecución

Clonar el repositorio:

```bash
git clone <https://github.com/rberriob-1432/taller-reparaciones>
cd taller_reparaciones
```

Compilar y ejecutar las pruebas:

```bash
mvn clean test
```

Compilar el proyecto:

```bash
mvn clean package
```

Ejecutar la aplicación:

```bash
java -cp target/classes main.Main
```

## Pruebas unitarias

El proyecto incluye pruebas unitarias para:

- Modelos y entidades.
- Estructuras de datos.
- Repositorios.
- Servicios.

Las pruebas se ejecutan mediante JUnit 5 y Maven:

```bash
mvn test
```

## Consideraciones técnicas

- Las estructuras de datos se implementan manualmente con fines académicos.
- No se utilizan colecciones como `java.util.ArrayList`, `java.util.HashMap` o `java.util.LinkedList`.
- Se utiliza `Scanner` para la lectura de datos desde consola.
- La lógica de negocio está separada de la presentación y del almacenamiento.
