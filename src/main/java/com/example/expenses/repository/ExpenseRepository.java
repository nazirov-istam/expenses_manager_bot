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

    List<Expense> findAllByUserChatIdAndCreatedAtBetween(Long chatId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT COUNT(e) > 0 FROM Expense e WHERE EXTRACT(YEAR FROM e.createdAt) = :year AND e.user.chatId = :userId")
    boolean existsByYearAndUserId(@Param("year") int year, @Param("userId") Long userId);
}
