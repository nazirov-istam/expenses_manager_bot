package com.example.expenses.repository;

import com.example.expenses.model.Expense;
import com.example.expenses.model.Income;
import com.example.expenses.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends CrudRepository<Income, Long> {
    Income findTopByUserOrderByCreatedAtDesc(User user);

}
