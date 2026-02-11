package com.jive.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(
  name="follows",
  uniqueConstraints = @UniqueConstraint(columnNames = {"follower_id", "followee_id"})
)
public class Follow {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="follower_id")
    private User follower;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="followee_id")
    private User followee;

    @Column(nullable=false)
    private Instant createdAt = Instant.now();
}