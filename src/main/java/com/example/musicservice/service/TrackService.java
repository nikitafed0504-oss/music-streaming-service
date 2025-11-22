package com.example.musicservice.service;

import com.example.musicservice.model.Track;
import com.example.musicservice.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrackService {
    private final TrackRepository trackRepository;

    public List<Track> findAll() {
        return trackRepository.findAll();
    }

    public Optional<Track> findById(Long id) {
        return trackRepository.findById(id);
    }

    public Track save(Track track) {
        return trackRepository.save(track);
    }

    public Track update(Long id, Track trackDetails) {
        Track track = trackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Track not found with id: " + id));
        track.setTitle(trackDetails.getTitle());
        track.setDuration(trackDetails.getDuration());
        return trackRepository.save(track);
    }

    public void deleteById(Long id) {
        trackRepository.deleteById(id);
    }

    public List<Track> findByArtistId(Long artistId) {
        return trackRepository.findByArtistId(artistId);
    }

    public List<Track> findByAlbumId(Long albumId) {
        return trackRepository.findByAlbumId(albumId);
    }

    public List<Track> findByGenre(String genre) {
        return trackRepository.findByArtistGenre(genre);
    }

    public List<Track> findByDurationRange(Integer minDuration, Integer maxDuration) {
        return trackRepository.findByDurationBetween(minDuration, maxDuration);
    }
}