# SALES MS SYSTEM

## Sistema distribuido basado en microservicios

Sales MS System es un sistema distribuido desarrollado con Java 17, Spring Boot, Spring Cloud, MySQL, Kafka, Docker, Prometheus y Grafana.

El proyecto simula un sistema de ventas compuesto por varios microservicios independientes, cada uno con una responsabilidad especifica.

## Objetivo

Implementar una arquitectura de microservicios para gestionar autenticacion, productos, clientes, inventario, pedidos y pagos.

## Arquitectura

| Modulo | Puerto | Descripcion |
|---|---:|---|
| Config Server | 7071 | Configuracion centralizada |
| Eureka Registry | 7081 | Registro y descubrimiento de servicios |
| API Gateway | 7091 | Entrada principal del sistema |
| Auth Service | 8081 | Autenticacion de usuarios |
| Product Service | 8082 | Gestion de productos |
| Customer Service | 8083 | Gestion de clientes |
| Inventory Service | 8084 | Gestion de inventario |
| Order Service | 8085 | Gestion de pedidos |
| Payment Service | 8086 | Gestion de pagos |

## Tecnologias utilizadas

- Java 17
- Spring Boot 3.5.14
- Spring Cloud 2025.0.2
- Spring Cloud Config
- Spring Cloud Gateway
- Eureka Server
- Spring Data JPA
- MySQL
- Apache Kafka
- Docker
- Prometheus
- Grafana
- Spring Boot Actuator
- Micrometer
- Postman
- Maven

## Estructura del proyecto

- config-repo
- infra/config-server
- infra/registry-server
- infra/gateway
- services/auth-service
- services/product-service
- services/customer-service
- services/inventory-service
- services/order-service
- services/payment-service
- kafka
- obs
- Sales-MS-System.postman_collection.json
- start-all.ps1
- test-system.ps1
- COMANDOS.md
- EXPLICACION_EXPOSICION.md

## Flujo principal con Kafka

1. El cliente envia una peticion desde Postman.
2. La peticion entra por el API Gateway.
3. Gateway redirige la peticion hacia Order Service.
4. Order Service crea un pedido demo.
5. Order Service publica el evento order-created en Kafka.
6. Inventory Service consume el evento y simula la reserva de stock.
7. Payment Service consume el evento y simula el procesamiento del pago.
8. Payment Service publica el evento payment-completed.
9. Order Service consume el evento y actualiza el estado del pedido.

## Topics de Kafka

| Topic | Productor | Consumidor |
|---|---|---|
| order-created | Order Service | Inventory Service y Payment Service |
| payment-completed | Payment Service | Order Service |

## Bases de datos

| Servicio | Base de datos | Puerto |
|---|---|---:|
| Product Service | nuevo_proyecto_db | 3320 |
| Auth Service | sales_auth_db | 3321 |
| Customer Service | sales_customers_db | 3322 |
| Inventory Service | sales_inventory_db | 3323 |
| Order Service | sales_orders_db | 3324 |
| Payment Service | sales_payments_db | 3325 |

## Endpoint principal

POST http://localhost:7091/api/v1/orders/demo

Este endpoint permite probar el flujo completo de pedido, inventario, pago y eventos Kafka.

## Observabilidad

El sistema cuenta con monitoreo mediante Actuator, Micrometer, Prometheus y Grafana.

Prometheus:
http://localhost:9090/targets

Grafana:
http://localhost:3000

Kafka UI:
http://localhost:8090

Eureka:
http://localhost:7081

## Ejecucion del proyecto

Primero abrir Docker Desktop.

Luego ejecutar desde la raiz del proyecto:

.\start-all.ps1

Para validar el sistema:

.\test-system.ps1

## Postman

El proyecto incluye la coleccion:

Sales-MS-System.postman_collection.json

Se puede importar en Postman para probar los endpoints principales.

## Evidencias recomendadas

- Eureka con servicios registrados
- Postman ejecutando el endpoint de pedido demo
- Kafka UI mostrando los topics
- Consolas mostrando eventos enviados y consumidos
- Prometheus con targets en estado UP
- Grafana mostrando metricas

## Conclusion

Sales MS System demuestra una arquitectura moderna de microservicios aplicada a un sistema de ventas distribuido. Integra API Gateway, Eureka, Config Server, Kafka, bases de datos independientes y observabilidad con Prometheus y Grafana.
