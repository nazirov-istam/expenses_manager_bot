package com.example.expenses.repository;

import com.example.expenses.model.Expense;
import com.example.expenses.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    Expense findTopByUserOrderByCreatedAtDesc(User user);

    @Query(value = "SELECT * FROM expenses e WHERE e.user_id = :userId AND DATE_TRUNC('month', e.created_at) = DATE_TRUNC('month', :month)",
            nativeQuery = true)
    List<Expense> findAllByUserAndMonth(@Param("userId") Long userId, @Param("month") LocalDate month);
}
