package com.artgallery.artgallery.controller;

import com.artgallery.artgallery.model.Artwork;
import com.artgallery.artgallery.repository.ArtworkRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artworks")
@CrossOrigin
public class ArtworkController {

    private final ArtworkRepository artworkRepository;

    public ArtworkController(ArtworkRepository artworkRepository) {
        this.artworkRepository = artworkRepository;
    }

    @GetMapping
    public List<Artwork> getAvailableArtworks() {
        return artworkRepository.findByPublishedTrue();
    }

    @GetMapping("/{id}")
    public Artwork getArtworkById(@PathVariable Long id) {
        return artworkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artwork not found"));
    }
}