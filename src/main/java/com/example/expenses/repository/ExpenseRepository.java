package com.example.expenses.repository;

import com.example.expenses.model.Expense;
import com.example.expenses.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    Expense findTopByUserOrderByCreatedAtDesc(User user);

    List<Expense> findByUser_ChatIdAndCreatedAtBetween(Long chatId, LocalDateTime startDate, LocalDateTime endDate);
}
