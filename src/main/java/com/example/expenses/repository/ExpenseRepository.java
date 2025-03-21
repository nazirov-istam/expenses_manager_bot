package com.example.expenses.repository;

import com.example.expenses.model.Expense;
import com.example.expenses.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    Expense findTopByUserOrderByCreatedAtDesc(User user);

    @Query("SELECT e FROM Expense e WHERE e.user.chatId = :chatId AND e.createdAt BETWEEN :startDate AND :endDate")
    List<Expense> findAllByUserChatIdAndCreatedAtBetween(@Param("chatId") Long chatId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(e) > 0 FROM Expense e WHERE EXTRACT(YEAR FROM e.createdAt) = :year AND e.user.chatId = :userId")
    boolean existsByYearAndUserId(@Param("year") int year, @Param("userId") Long userId);

    @Query("SELECT COUNT(e) > 0 FROM Expense e WHERE e.user.chatId = :chatId AND e.createdAt BETWEEN :startDate AND :endDate")
    boolean existsByUserChatIdAndCreatedAtBetween(@Param("chatId") Long chatId,
                                                  @Param("startDate") LocalDateTime startDate,
                                                  @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT COALESCE(SUM(e.expenseAmount), 0) FROM Expense e WHERE e.user.chatId = :chatId")
    Long getTotalExpenseByChatId(@Param("chatId") Long chatId);

    @Query("SELECT COUNT(e) FROM Expense e WHERE e.user.chatId = :chatId")
    long countTotalExpense(@Param("chatId") Long chatId);

    @Query("""
                SELECT TO_CHAR(e.createdAt, 'YYYY-MM') AS month, SUM(e.expenseAmount) AS total
                FROM Expense e
                WHERE e.user.chatId = :chatId
                GROUP BY month
                ORDER BY total DESC
                LIMIT 1
            """)
    String findMostExpensiveMonth(@Param("chatId") Long chatId);

    @Query("""
                SELECT TO_CHAR(e.createdAt, 'YYYY-MM') AS month, SUM(e.expenseAmount) AS total
                FROM Expense e
                WHERE e.user.chatId = :chatId
                GROUP BY month
                ORDER BY total ASC
                LIMIT 1
            """)
    String findLeastExpensiveMonth(@Param("chatId") Long chatId);
}
