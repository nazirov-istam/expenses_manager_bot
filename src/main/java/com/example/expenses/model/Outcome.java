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
@Table(name = ("outcome"))
public class Outcome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Basic
    @Column(name = ("outcome_source"))
    String outcomeSource;
    @Basic
    @Column(name = ("outcome_amount"))
    Double outcomeAmount;
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
