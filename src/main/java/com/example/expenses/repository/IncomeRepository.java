package com.example.expenses.repository;

import com.example.expenses.model.Income;
import com.example.expenses.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IncomeRepository extends CrudRepository<Income, Long> {
    Income findTopByUserOrderByCreatedAtDesc(User user);

    List<Income> findByUser_ChatIdAndCreatedAtBetween(Long chatId, LocalDateTime startDate, LocalDateTime endDate);
}
