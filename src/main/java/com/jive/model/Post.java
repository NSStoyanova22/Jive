package com.jive.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name="posts")
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User author;

    @Column(nullable=false, length=2000)
    private String content;

    @Column(nullable=false)
    private Instant createdAt = Instant.now();

    public Post() {}

    public Post(User author, String content) {
        this.author = author;
        this.content = content;
    }

    public Long getId() { return id; }
    public User getAuthor() { return author; }
    public String getContent() { return content; }
    public Instant getCreatedAt() { return createdAt; }

    public void setAuthor(User author) { this.author = author; }
    public void setContent(String content) { this.content = content; }
}