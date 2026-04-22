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

/** Represents a user group. */
@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
public class Group {
    /** Primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Group name. */
    private String name;

    /** Shareable link URL. */
    @Column(name = "link_url")
    private String linkUrl;

    /** Unique invite code. */
    @Column(unique = true, name = "invite_code")
    private String inviteCode;

    /** Timestamp of creation. */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    /** Members of this group. */
    @OneToMany(mappedBy = "group")
    private Set<UserGroup> userGroups;
}
