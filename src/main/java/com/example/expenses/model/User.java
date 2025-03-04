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
@Table(name = ("bot_user"))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @Column(name = ("chat_id"))
    Long chatId;
    @Basic
    @Column(name = ("first_name"))
    String firstName;
    @Basic
    @Column(name = ("last_name"))
    String lastName;
    @Basic
    @Column(name = ("success"))
    private String success;
    @Basic
    @Column(name = ("phone"))
    String phone;

    private String step;
    private String language;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;
}
