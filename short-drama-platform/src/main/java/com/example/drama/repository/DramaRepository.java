package com.example.drama.repository;

import com.example.drama.entity.Drama;
import com.example.drama.entity.DramaCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DramaRepository extends JpaRepository<Drama, Long> {
    List<Drama> findByCategory(DramaCategory category);
}
