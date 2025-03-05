package com.example.expenses.model;

import com.example.expenses.enums.Language;
import com.example.expenses.enums.Steps;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@Validated
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = ("bot_user"))
public class User {
    @Id
    @Column(name = ("chat_id"))
    Long chatId;
    @Basic
    @Column(name = ("first_name"))
    String firstname;
    @Basic
    @Column(name = ("last_name"))
    String lastname;
    @Basic
    @Column(name = ("username"))
    String username;
    @Basic
    @Column(name = ("income"))
    Double income;
    @Basic
    @Column(name = ("outcome"))
    Double outcome;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = ("step"))
    private Steps step;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = ("language"))
    private Language language;
    @Basic
    @Column(name = ("created_at"))
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Income> incomes;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Outcome> outcomes;
}
