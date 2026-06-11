Write-Host "======================================" -ForegroundColor Cyan
Write-Host " VALIDANDO SALES MS SYSTEM" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan

function Test-Url {
    param(
        [string]$Name,
        [string]$Url
    )

    try {
        $response = Invoke-WebRequest -Uri $Url -UseBasicParsing -TimeoutSec 5
        Write-Host "[OK] $Name -> $Url" -ForegroundColor Green
    } catch {
        Write-Host "[ERROR] $Name -> $Url" -ForegroundColor Red
    }
}

Test-Url "Eureka Registry" "http://localhost:7081"
Test-Url "Gateway Health" "http://localhost:7091/actuator/health"
Test-Url "Product Service Health" "http://localhost:8082/actuator/health"
Test-Url "Auth Service Health" "http://localhost:8081/actuator/health"
Test-Url "Customer Service Health" "http://localhost:8083/actuator/health"
Test-Url "Inventory Service Health" "http://localhost:8084/actuator/health"
Test-Url "Order Service Health" "http://localhost:8085/actuator/health"
Test-Url "Payment Service Health" "http://localhost:8086/actuator/health"
Test-Url "Kafka UI" "http://localhost:8090"
Test-Url "Prometheus" "http://localhost:9090"
Test-Url "Grafana" "http://localhost:3000"

Write-Host ""
Write-Host "Probando flujo por Gateway..." -ForegroundColor Yellow

try {
    $result = Invoke-RestMethod -Method POST -Uri "http://localhost:7091/api/v1/orders/demo"
    Write-Host "[OK] Pedido enviado por Gateway correctamente" -ForegroundColor Green
    $result | ConvertTo-Json -Depth 5
} catch {
    Write-Host "[ERROR] No se pudo enviar pedido por Gateway" -ForegroundColor Red
    Write-Host $_.Exception.Message -ForegroundColor Red
}

Write-Host ""
Write-Host "======================================" -ForegroundColor Cyan
Write-Host " VALIDACION FINALIZADA" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan
