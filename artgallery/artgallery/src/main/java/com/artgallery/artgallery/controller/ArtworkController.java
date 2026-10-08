package com.artgallery.artgallery.controller;

import com.artgallery.artgallery.model.Artwork;
import com.artgallery.artgallery.repository.ArtworkRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artworks")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ArtworkController {

    private final ArtworkRepository artworkRepository;

    public ArtworkController(ArtworkRepository artworkRepository) {
        this.artworkRepository = artworkRepository;
    }

    // Get all artworks
    @GetMapping
    public List<Artwork> getAllArtworks() {
        return artworkRepository.findAll();
    }

    // Get artwork by ID
    @GetMapping("/{id}")
    public Artwork getArtworkById(@PathVariable Long id) {
        return artworkRepository.findById(id).orElse(null);
    }

    // Add a new artwork
    @PostMapping
    public Artwork addArtwork(@RequestBody Artwork artwork) {
        return artworkRepository.save(artwork);
    }

    // Update an artwork
    @PutMapping("/{id}")
    public Artwork updateArtwork(
            @PathVariable Long id,
            @RequestBody Artwork artwork) {

        Artwork existingArtwork =
                artworkRepository.findById(id).orElse(null);

        if (existingArtwork == null) {
            return null;
        }

        existingArtwork.setTitle(artwork.getTitle());
        existingArtwork.setDescription(artwork.getDescription());
        existingArtwork.setImageUrl(artwork.getImageUrl());
        existingArtwork.setPrice(artwork.getPrice());
        existingArtwork.setArtistName(artwork.getArtistName());
        existingArtwork.setAuctionStatus(artwork.getAuctionStatus());

        return artworkRepository.save(existingArtwork);
    }

    // Delete an artwork
    @DeleteMapping("/{id}")
    public String deleteArtwork(@PathVariable Long id) {

        if (!artworkRepository.existsById(id)) {
            return "Artwork not found";
        }

        artworkRepository.deleteById(id);
        return "Artwork deleted successfully";
    }
}