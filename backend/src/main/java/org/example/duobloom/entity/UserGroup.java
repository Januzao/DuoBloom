package org.example.duobloom.entity;

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

/** Represents a user's membership in a group. */
@Entity
@Table(name = "user_groups")
@Getter
@Setter
@NoArgsConstructor
public class UserGroup {
    /** Primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The user. */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /** The group. */
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    /** The user's role in the group. */
    @Enumerated(EnumType.STRING)
    private Role role;
}
