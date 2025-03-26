package com.example.expenses.repository;

import com.example.expenses.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User getStepByChatId(Long chatId);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.income = 0.0, u.expense = 0.0")
    void resetAllUserBalances();

    List<User> findAll();

    @Query("SELECT COUNT(u) FROM User u")
    long getTotalUserCount();

    @Query(value = """
            SELECT COALESCE(TO_CHAR(u.created_at, 'YYYY-MM') || ' ' || COUNT(u.chat_id), 'Malumot yuq')
            FROM users u
            GROUP BY TO_CHAR(u.created_at, 'YYYY-MM')
            ORDER BY COUNT(u.chat_id) DESC
            LIMIT 1
            """, nativeQuery = true)
    Optional<String> findMonthWithMostUsers();

    @Query(value = """
            SELECT COALESCE(TO_CHAR(u.created_at, 'YYYY-MM') || ' ' || COUNT(u.chat_id), 'Malumot yuq')
            FROM users u
            GROUP BY TO_CHAR(u.created_at, 'YYYY-MM')
            ORDER BY COUNT(u.chat_id) ASC
            LIMIT 1
            """, nativeQuery = true)
    Optional<String> findMonthWithLeastUsers();
}
