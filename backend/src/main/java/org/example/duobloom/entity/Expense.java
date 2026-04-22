package org.example.duobloom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Represents a group expense. */
@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
public class Expense {
    /** Primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Expense amount. */
    private BigDecimal amount;
    /** Expense description. */
    private String description;
    /** Date of the expense. */
    private LocalDate expenseDate;

    /** Group this expense belongs to. */
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    /** User who made the expense. */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /** Category of the expense. */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
