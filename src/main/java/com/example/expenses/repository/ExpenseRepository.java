package com.example.expenses.repository;

import com.example.expenses.model.Expense;
import com.example.expenses.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    Expense findByUser(User user);
}
