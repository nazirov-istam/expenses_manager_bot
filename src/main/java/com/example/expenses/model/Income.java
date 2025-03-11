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
@Table(name = ("incomes"))
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("id"))
    Long id;
    @Basic
    @Column(name = ("income_source"))
    String incomeSource;
    @Basic
    @Column(name = ("income_amount"))
    Double incomeAmount;
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
