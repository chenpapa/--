package com.example.drama.controller;

import com.example.drama.service.DramaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DramaService dramaService;

    public DashboardController(DramaService dramaService) {
        this.dramaService = dramaService;
    }

    @GetMapping("/overview")
    public Map<String, Object> overview() {
        return dramaService.dashboard();
    }
}
