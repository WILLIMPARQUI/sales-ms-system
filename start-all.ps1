Write-Host "======================================" -ForegroundColor Cyan
Write-Host " INICIANDO SALES MS SYSTEM COMPLETO" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan

$root = "D:\PROYECTO DE DISTRIBUIDAS\sales-ms-system-feature-u1-sales-ms-base"

Write-Host "Iniciando MySQL..." -ForegroundColor Yellow
docker start mysql-nuevo-proyecto
docker start mysql-auth-service
docker start mysql-customer-service
docker start mysql-inventory-service
docker start mysql-order-service
docker start mysql-payment-service

Write-Host "Iniciando Kafka..." -ForegroundColor Yellow
docker compose -f "$root\kafka\compose-kafka.yml" up -d

Write-Host "Iniciando Observabilidad..." -ForegroundColor Yellow
docker compose -f "$root\obs\compose-obs.yml" up -d

function Start-ServiceWindow {
    param(
        [string]$Name,
        [string]$Path
    )

    Write-Host "Iniciando $Name ..." -ForegroundColor Green

    Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$Path'; mvn spring-boot:run"
}

Start-Sleep -Seconds 8

Start-ServiceWindow "CONFIG-SERVER" "$root\infra\config-server"
Start-Sleep -Seconds 8

Start-ServiceWindow "REGISTRY-SERVER" "$root\infra\registry-server"
Start-Sleep -Seconds 12

Start-ServiceWindow "GATEWAY" "$root\infra\gateway"
Start-Sleep -Seconds 6

Start-ServiceWindow "PRODUCT-SERVICE" "$root\services\product-service"
Start-Sleep -Seconds 5

Start-ServiceWindow "AUTH-SERVICE" "$root\services\auth-service"
Start-Sleep -Seconds 5

Start-ServiceWindow "CUSTOMER-SERVICE" "$root\services\customer-service"
Start-Sleep -Seconds 5

Start-ServiceWindow "INVENTORY-SERVICE" "$root\services\inventory-service"
Start-Sleep -Seconds 5

Start-ServiceWindow "ORDER-SERVICE" "$root\services\order-service"
Start-Sleep -Seconds 5

Start-ServiceWindow "PAYMENT-SERVICE" "$root\services\payment-service"

Write-Host "======================================" -ForegroundColor Cyan
Write-Host " SISTEMA INICIADO" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan
Write-Host "Eureka:     http://localhost:7081"
Write-Host "Gateway:    http://localhost:7091"
Write-Host "Kafka UI:   http://localhost:8090"
Write-Host "Prometheus: http://localhost:9090"
Write-Host "Grafana:    http://localhost:3000"
Write-Host "Product:    http://localhost:8082/actuator/prometheus"
