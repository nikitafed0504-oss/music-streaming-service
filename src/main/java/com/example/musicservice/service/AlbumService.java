package com.example.musicservice.service;

import com.example.musicservice.model.Album;
import com.example.musicservice.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;

    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    public Optional<Album> findById(Long id) {
        return albumRepository.findById(id);
    }

    public Album save(Album album) {
        return albumRepository.save(album);
    }

    public Album update(Long id, Album albumDetails) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Album not found with id: " + id));
        album.setTitle(albumDetails.getTitle());
        album.setReleaseYear(albumDetails.getReleaseYear());
        return albumRepository.save(album);
    }

    public void deleteById(Long id) {
        albumRepository.deleteById(id);
    }

    public List<Album> findByArtistId(Long artistId) {
        return albumRepository.findByArtistId(artistId);
    }

    public List<Album> findByReleaseYearRange(Integer startYear, Integer endYear) {
        return albumRepository.findByReleaseYearBetween(startYear, endYear);
    }
}