package com.example.drama.controller;

import com.example.drama.dto.DramaRequest;
import com.example.drama.dto.EpisodeRequest;
import com.example.drama.entity.Drama;
import com.example.drama.entity.DramaCategory;
import com.example.drama.entity.Episode;
import com.example.drama.service.DramaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dramas")
public class DramaController {

    private final DramaService dramaService;

    public DramaController(DramaService dramaService) {
        this.dramaService = dramaService;
    }

    @GetMapping
    public List<Drama> list(@RequestParam(required = false) DramaCategory category) {
        return dramaService.listDramas(category);
    }

    @GetMapping("/{id}")
    public Drama get(@PathVariable Long id) {
        return dramaService.getDrama(id);
    }

    @PostMapping
    public Drama create(@Valid @RequestBody DramaRequest request) {
        return dramaService.createDrama(request);
    }

    @PutMapping("/{id}")
    public Drama update(@PathVariable Long id, @Valid @RequestBody DramaRequest request) {
        return dramaService.updateDrama(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        dramaService.deleteDrama(id);
    }

    @GetMapping("/{dramaId}/episodes")
    public List<Episode> listEpisodes(@PathVariable Long dramaId) {
        return dramaService.listEpisodes(dramaId);
    }

    @PostMapping("/{dramaId}/episodes")
    public Episode addEpisode(@PathVariable Long dramaId, @Valid @RequestBody EpisodeRequest request) {
        return dramaService.addEpisode(dramaId, request);
    }

    @PostMapping("/{dramaId}/episodes/{episodeId}/play")
    public Map<String, Object> play(@PathVariable Long dramaId, @PathVariable Long episodeId) {
        return dramaService.playEpisode(dramaId, episodeId);
    }
}
