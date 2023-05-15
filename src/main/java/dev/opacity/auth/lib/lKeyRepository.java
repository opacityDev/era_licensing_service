package dev.opacity.auth.lib;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
 
public interface lKeyRepository extends JpaRepository<lkey, Integer> {
    // custom query to search to blog post by title or content
    List<lkey> findByContent(String content);
}

