# 💰 Expense Tracker API

Controle de despesas pessoais com categorias, tipos e resumo mensal.

## 📋 Sobre o Projeto

API para acompanhar receitas e despesas pessoais. Classifica as movimentações por categoria (Alimentação, Transporte, Lazer, etc.), separa por tipo (INCOME/EXPENSE) e gera resumos mensais com saldo total.

## ✨ Funcionalidades

- ✅ Registrar receitas e despesas
- ✅ Classificar por categoria personalizada
- ✅ Filtrar por mês/ano
- ✅ Filtrar por tipo (receita ou despesa)
- ✅ Resumo mensal: total de receitas, despesas e saldo
- ✅ Listar maiores gastos por categoria
- ✅ Editar e excluir lançamentos

## 🔗 Endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| GET/POST | `/api/expenses` | Listar / Criar lançamento |
| GET/PUT/DELETE | `/api/expenses/{id}` | Gerenciar lançamento |
| GET | `/api/expenses/summary` | Resumo mensal |
| GET | `/api/expenses?month=6&year=2025` | Filtrar por mês |
| GET | `/api/expenses?type=EXPENSE` | Filtrar por tipo |

## 🛠️ Tecnologias

- Java 17 · Spring Boot 3.2 · PostgreSQL · Maven · Lombok
