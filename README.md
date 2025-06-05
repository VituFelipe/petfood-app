# Plataforma de Venda de Ração para Animais

## Descrição
## Estrutura
## Pré-requisitos

## Endpoints
Catalog Service (http://localhost:8081):
GET /api/products
POST /api/products
GET /api/products/{id}
Swagger: http://localhost:8081/swagger-ui.html
User Service (http://localhost:8082):
POST /api/users/register
POST /api/users/login
GET /api/users/{id} (autenticado)
PUT /api/users/{id} (autenticado)
Swagger: http://localhost:8082/swagger-ui.html

Log de Commits
2025-06-05: Inicialização do catalog-service com endpoints básicos e Swagger.
2025-06-05: Adicionado user-service com cadastro, login e gerenciamento de clientes.
2025-06-05: Corrigido inicialização do user-service e adicionado logs detalhados