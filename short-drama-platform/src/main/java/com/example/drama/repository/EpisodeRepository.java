package com.example.drama.repository;

import com.example.drama.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    List<Episode> findByDramaIdOrderByEpisodeNoAsc(Long dramaId);
}
