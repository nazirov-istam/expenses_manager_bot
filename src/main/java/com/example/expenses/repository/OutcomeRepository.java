package com.example.expenses.repository;

import com.example.expenses.model.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutcomeRepository extends CrudRepository<Expense, Long> {
}
