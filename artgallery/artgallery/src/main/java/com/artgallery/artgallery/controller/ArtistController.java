package com.artgallery.artgallery.controller;

import com.artgallery.artgallery.model.Artist;
import com.artgallery.artgallery.repository.ArtistRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
@CrossOrigin(origins = {
    "http://127.0.0.1:5500",
    "http://localhost:5500"
})
public class ArtistController {

    private final ArtistRepository artistRepository;

    public ArtistController(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @PostMapping
    public Artist createArtist(@RequestBody Artist artist) {
        return artistRepository.save(artist);
    }

    @GetMapping
    public List<Artist> getArtists() {
        return artistRepository.findAll();
    }
}