package com.jive.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name="comments")
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User author;

    @Column(nullable=false, length=1000)
    private String content;

    @Column(nullable=false)
    private Instant createdAt = Instant.now();
}