package com.example.expenses.repository;

import com.example.expenses.model.Outcome;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutcomeRepository extends CrudRepository<Outcome, Long> {
}
