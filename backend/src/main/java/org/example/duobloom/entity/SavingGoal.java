package org.example.duobloom.entity;

import jakarta.persistence.Column;
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

/** Represents a group saving goal. */
@Entity
@Table(name = "saving_goals")
@Getter
@Setter
@NoArgsConstructor
public class SavingGoal {
    /** Primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Group this goal belongs to. */
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    /** Goal title. */
    private String title;

    /** Target amount to save. */
    @Column(name = "target_amount")
    private BigDecimal targetAmount;

    /** Amount saved so far. */
    @Column(name = "current_amount")
    private BigDecimal currentAmount;
    /** Deadline for reaching the goal. */
    private LocalDate deadline;
}
