package org.example.duobloom.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import java.time.LocalDateTime;

/** Represents an item in a group wish list. */
@Entity
@Table(name = "wish_items")
@Getter
@Setter
@NoArgsConstructor
public class WishItem {
    /** Precision for the price column. */
    private static final int PRICE_PRECISION = 19;

    /** Primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Group this wish item belongs to. */
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    /** User who created this wish item. */
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    /** Wish item title. */
    private String title;
    /** Wish item description. */
    private String description;

    /** URL link to the item. */
    @Column(name = "link_url")
    private String linkUrl;

    /** Item price. */
    @Column(precision = PRICE_PRECISION, scale = 2)
    private BigDecimal price;

    /** Current status of the wish item. */
    @Enumerated(EnumType.STRING)
    private WishStatus status;

    /** Timestamp of creation. */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
