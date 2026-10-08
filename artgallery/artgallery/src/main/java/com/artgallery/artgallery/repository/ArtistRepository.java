package com.artgallery.artgallery.repository;

import com.artgallery.artgallery.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}