package com.example.drama.service;

import com.example.drama.dto.DramaRequest;
import com.example.drama.dto.EpisodeRequest;
import com.example.drama.entity.Drama;
import com.example.drama.entity.DramaCategory;
import com.example.drama.entity.Episode;
import com.example.drama.repository.DramaRepository;
import com.example.drama.repository.EpisodeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DramaService {

    private final DramaRepository dramaRepository;
    private final EpisodeRepository episodeRepository;

    public DramaService(DramaRepository dramaRepository, EpisodeRepository episodeRepository) {
        this.dramaRepository = dramaRepository;
        this.episodeRepository = episodeRepository;
    }

    public List<Drama> listDramas(DramaCategory category) {
        if (category == null) {
            return dramaRepository.findAll();
        }
        return dramaRepository.findByCategory(category);
    }

    public Drama getDrama(Long id) {
        return dramaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("短剧不存在: " + id));
    }

    @Transactional
    public Drama createDrama(DramaRequest request) {
        Drama drama = new Drama();
        fillDrama(drama, request);
        return dramaRepository.save(drama);
    }

    @Transactional
    public Drama updateDrama(Long id, DramaRequest request) {
        Drama drama = getDrama(id);
        fillDrama(drama, request);
        return dramaRepository.save(drama);
    }

    @Transactional
    public void deleteDrama(Long id) {
        if (!dramaRepository.existsById(id)) {
            throw new EntityNotFoundException("短剧不存在: " + id);
        }
        dramaRepository.deleteById(id);
    }

    @Transactional
    public Episode addEpisode(Long dramaId, EpisodeRequest request) {
        Drama drama = getDrama(dramaId);
        Episode episode = new Episode();
        episode.setDrama(drama);
        episode.setEpisodeNo(request.getEpisodeNo());
        episode.setTitle(request.getTitle());
        episode.setDurationSeconds(request.getDurationSeconds());
        Episode saved = episodeRepository.save(episode);

        drama.setTotalEpisodes(episodeRepository.findByDramaIdOrderByEpisodeNoAsc(dramaId).size());
        dramaRepository.save(drama);
        return saved;
    }

    public List<Episode> listEpisodes(Long dramaId) {
        getDrama(dramaId);
        return episodeRepository.findByDramaIdOrderByEpisodeNoAsc(dramaId);
    }

    @Transactional
    public Map<String, Object> playEpisode(Long dramaId, Long episodeId) {
        getDrama(dramaId);
        Episode episode = episodeRepository.findById(episodeId)
                .filter(e -> e.getDrama().getId().equals(dramaId))
                .orElseThrow(() -> new EntityNotFoundException("剧集不存在: " + episodeId));

        episode.setPlayCount(episode.getPlayCount() + 1);
        episodeRepository.save(episode);

        Drama drama = episode.getDrama();
        drama.setTotalPlays(drama.getTotalPlays() + 1);
        dramaRepository.save(drama);

        Map<String, Object> data = new HashMap<>();
        data.put("dramaId", dramaId);
        data.put("episodeId", episodeId);
        data.put("episodePlayCount", episode.getPlayCount());
        data.put("dramaTotalPlays", drama.getTotalPlays());
        return data;
    }

    public Map<String, Object> dashboard() {
        List<Drama> dramas = dramaRepository.findAll();
        long publishedCount = dramas.stream().filter(d -> d.getStatus().name().equals("PUBLISHED")).count();
        long totalPlays = dramas.stream().mapToLong(Drama::getTotalPlays).sum();

        Map<String, Object> map = new HashMap<>();
        map.put("totalDramaCount", dramas.size());
        map.put("publishedDramaCount", publishedCount);
        map.put("totalPlayCount", totalPlays);
        return map;
    }

    private void fillDrama(Drama drama, DramaRequest request) {
        drama.setTitle(request.getTitle());
        drama.setCategory(request.getCategory());
        drama.setDescription(request.getDescription());
        drama.setStatus(request.getStatus());
    }
}
