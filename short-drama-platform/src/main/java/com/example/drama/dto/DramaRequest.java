package com.example.drama.dto;

import com.example.drama.entity.DramaCategory;
import com.example.drama.entity.DramaStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DramaRequest {

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotNull
    private DramaCategory category;

    @Size(max = 500)
    private String description;

    @NotNull
    private DramaStatus status;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public DramaCategory getCategory() { return category; }
    public void setCategory(DramaCategory category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public DramaStatus getStatus() { return status; }
    public void setStatus(DramaStatus status) { this.status = status; }
}
