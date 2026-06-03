package com.patryck.expense.repository;
import com.patryck.expense.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(String userId);
    List<Expense> findByUserIdAndType(String userId, ExpenseType type);
    List<Expense> findByUserIdAndCategory(String userId, String category);
    List<Expense> findByUserIdAndExpenseDateBetween(String userId, LocalDate from, LocalDate to);
    @Query("SELECT COALESCE(SUM(e.amount),0) FROM Expense e WHERE e.userId=:uid AND e.type=:type")
    BigDecimal sumByUserAndType(@Param("uid") String uid, @Param("type") ExpenseType type);
}
