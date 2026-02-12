package com.jive.service;

import com.jive.model.Post;
import com.jive.model.User;
import com.jive.repository.PostRepository;
import com.jive.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepo;
    private final UserRepository userRepo;

    public PostService(PostRepository postRepo, UserRepository userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    public List<Post> feed() {
        return postRepo.findAllByOrderByCreatedAtDesc();
    }

    public Post createPost(long userId, String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Post content cannot be empty.");
        }
        User author = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        return postRepo.save(new Post(author, content.trim()));
    }
}