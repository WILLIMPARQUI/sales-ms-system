# EXPLICACIÓN PARA EXPOSICIÓN - SALES MS SYSTEM

Buenos días, mi proyecto se llama Sales MS System. Es un sistema de ventas distribuido desarrollado con arquitectura de microservicios usando Java, Spring Boot y Spring Cloud.

El objetivo principal del proyecto es demostrar cómo se puede dividir un sistema de ventas en varios servicios independientes, donde cada uno cumple una responsabilidad específica. Por ejemplo, existe un servicio para productos, otro para clientes, otro para pedidos, inventario, pagos y autenticación.

La arquitectura cuenta con un Config Server para centralizar configuraciones, Eureka Server para registrar y descubrir los microservicios, y un API Gateway que funciona como punto de entrada principal para todas las peticiones del sistema.

En este proyecto también se implementó comunicación asíncrona con Apache Kafka. El flujo principal inicia cuando se crea un pedido desde Postman usando el Gateway. El Order Service publica un evento llamado order-created en Kafka. Luego, Inventory Service escucha ese evento y simula la reserva del stock. Al mismo tiempo, Payment Service también escucha el evento, procesa el pago y publica un nuevo evento llamado payment-completed. Finalmente, Order Service escucha ese evento y actualiza el estado del pedido como pagado.

Además, cada microservicio trabaja con su propia base de datos MySQL, lo cual representa independencia de datos. También se agregó monitoreo con Actuator, Micrometer, Prometheus y Grafana, permitiendo visualizar el estado de los servicios y sus métricas.

En resumen, el proyecto demuestra una arquitectura moderna de microservicios con descubrimiento de servicios, gateway, bases de datos separadas, mensajería con Kafka y observabilidad.
