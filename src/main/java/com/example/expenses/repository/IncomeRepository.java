package com.example.expenses.repository;

import com.example.expenses.model.Income;
import com.example.expenses.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncomeRepository extends CrudRepository<Income, Long> {
    Income findTopByUserOrderByCreatedAtDesc(User user);

    @Query(value = "SELECT * FROM incomes e WHERE e.user_id = :userId AND DATE_TRUNC('month', i.created_at) = DATE_TRUNC('month', :month)",
            nativeQuery = true)
    List<Income> findAllByUserAndMonth(@Param("userId") Long userId, @Param("month") LocalDate month);
}
