# Sistema distribuido basado en microservicios para la gestión de ventas, pedidos y autenticación

## 1. Descripción general

Este proyecto implementa la base de un sistema distribuido orientado a producción, desarrollado bajo una arquitectura de microservicios con Spring Boot y Spring Cloud.

El sistema tiene como finalidad gestionar procesos relacionados con ventas, productos, clientes, pedidos y autenticación. En esta primera etapa se implementan los microservicios `product-service` y `customer-service`, junto con los componentes de infraestructura necesarios para operar dentro de una arquitectura distribuida.

La solución incluye configuración externa, registro y descubrimiento de servicios, acceso centralizado mediante API Gateway y preparación para ejecutar múltiples instancias de un microservicio.

---

## 2. Alcance de esta versión

En esta versión se implementa la base correspondiente a la Unidad 1 del proyecto.

### Microservicios implementados

- `product-service`: gestión de productos.
- `customer-service`: gestión de clientes.

### Microservicios proyectados

- `order-service`: gestión de pedidos.
- `auth-service`: gestión de usuarios, roles y autenticación.

### Infraestructura implementada

- `config-server`
- `registry-server`
- `gateway`

---

## 3. Arquitectura general

```text
Cliente
   |
   v
API Gateway
   |
   +--> product-service
   |
   +--> customer-service

Todos los servicios:
   -> se registran en registry-server / Eureka
   -> cargan configuración desde config-server
   -> usan persistencia propia en MySQL