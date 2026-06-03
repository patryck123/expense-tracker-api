package com.patryck.expense.controller;
import com.patryck.expense.entity.*;
import com.patryck.expense.repository.ExpenseRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
@RestController @RequestMapping("/api/expenses") @RequiredArgsConstructor
@Tag(name = "Despesas", description = "Controle de gastos e receitas pessoais")
public class ExpenseController {
    private final ExpenseRepository repo;
    @PostMapping public ResponseEntity<Expense> create(@Valid @RequestBody Expense e) { return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(e)); }
    @GetMapping("/user/{userId}") public ResponseEntity<List<Expense>> byUser(@PathVariable String userId) { return ResponseEntity.ok(repo.findByUserId(userId)); }
    @GetMapping("/user/{userId}/type/{type}") public ResponseEntity<List<Expense>> byType(@PathVariable String userId, @PathVariable ExpenseType type) { return ResponseEntity.ok(repo.findByUserIdAndType(userId, type)); }
    @GetMapping("/user/{userId}/category/{cat}") public ResponseEntity<List<Expense>> byCategory(@PathVariable String userId, @PathVariable String cat) { return ResponseEntity.ok(repo.findByUserIdAndCategory(userId, cat)); }
    @GetMapping("/user/{userId}/period") @Operation(summary = "Gastos por período")
    public ResponseEntity<List<Expense>> byPeriod(@PathVariable String userId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return ResponseEntity.ok(repo.findByUserIdAndExpenseDateBetween(userId, from, to));
    }
    @GetMapping("/user/{userId}/summary") @Operation(summary = "Resumo financeiro do usuário")
    public ResponseEntity<Map<String, BigDecimal>> summary(@PathVariable String userId) {
        BigDecimal income = repo.sumByUserAndType(userId, ExpenseType.INCOME);
        BigDecimal expenses = repo.sumByUserAndType(userId, ExpenseType.EXPENSE);
        return ResponseEntity.ok(Map.of("income", income, "expenses", expenses, "balance", income.subtract(expenses)));
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { repo.deleteById(id); return ResponseEntity.noContent().build(); }
}
