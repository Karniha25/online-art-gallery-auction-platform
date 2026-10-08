package com.artgallery.artgallery.repository;

import com.artgallery.artgallery.model.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtworkRepository extends JpaRepository<Artwork, Long> {

    List<Artwork> findByPublishedTrue();
}