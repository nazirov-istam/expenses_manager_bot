package com.example.expenses.repository;

import com.example.expenses.model.Income;
import com.example.expenses.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IncomeRepository extends CrudRepository<Income, Long> {
    Income findTopByUserOrderByCreatedAtDesc(User user);

    List<Income> findAllByUserChatIdAndCreatedAtBetween(Long chatId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT COUNT(i) > 0 FROM Income i WHERE EXTRACT(YEAR FROM i.createdAt) = :year AND i.user.chatId = :userId")
    boolean existsByYearAndUserId(@Param("year") int year, @Param("userId") Long userId);
}
