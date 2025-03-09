package com.example.expenses.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Builder
@Validated
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = ("expense"))
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("id"))
    Long id;
    @Basic
    @Column(name = ("expense_source"))
    String expenseSource;
    @Basic
    @Column(name = ("expense_amount"))
    Double expenseAmount;
    @Basic
    @Column(name = ("description"))
    String description;
    @Basic
    @Column(name = ("created_at"))
    LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = ("user_id"), nullable = false)
    private User user;
}
