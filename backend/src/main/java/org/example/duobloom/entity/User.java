package org.example.duobloom.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

/** Represents an application user. */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    /** Primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Unique email address. */
    @Column(unique = true, nullable = false)
    private String email;

    /** Display name. */
    private String username;

    /** Hashed password. */
    @Column(name = "password_hash")
    private String passwordHash;

    /** Timestamp of account creation. */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    /** Group memberships for this user. */
    @OneToMany(mappedBy = "user")
    private Set<UserGroup> userGroups;
}
