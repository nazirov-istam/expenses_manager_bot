package com.example.expenses.repository;

import com.example.expenses.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User getStepByChatId(Long chatId);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.income = 0.0, u.expense = 0.0, u.totalBalance = 0.0")
    void resetAllUserBalances();


    List<User> findAll();
}
