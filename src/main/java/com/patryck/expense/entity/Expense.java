package com.patryck.expense.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity @Table(name = "expenses") @Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Expense {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank @Column(nullable = false) private String description;
    @NotNull @Positive @Column(nullable = false, precision = 10, scale = 2) private BigDecimal amount;
    @NotBlank @Column(nullable = false) private String category;
    @NotBlank @Column(nullable = false) private String userId;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private ExpenseType type;
    @Column(nullable = false) private LocalDate expenseDate;
    private String notes;
    @Column(nullable = false, updatable = false) private LocalDateTime createdAt;
    @PrePersist protected void onCreate() { createdAt = LocalDateTime.now(); if (expenseDate == null) expenseDate = LocalDate.now(); }
}
