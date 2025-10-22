package com.smartbudget.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    @Id @GeneratedValue
    private Long id;

    private String description;
    private Double amount;
    private String category;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private ExpenseType type;

    private Boolean paid;

    @ManyToOne
    private User user;
}


