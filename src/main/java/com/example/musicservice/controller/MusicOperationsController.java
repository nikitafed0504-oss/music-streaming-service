package com.example.musicservice.controller;

import com.example.musicservice.model.Album;
import com.example.musicservice.model.Playlist;
import com.example.musicservice.model.Track;
import com.example.musicservice.service.MusicStreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/music/operations")
@RequiredArgsConstructor
public class MusicOperationsController {
    private final MusicStreamingService musicStreamingService;

    // Бизнес-операция 1: Создание альбома с треками
    @PostMapping("/albums/with-tracks")
    public ResponseEntity<Album> createAlbumWithTracks(@RequestBody AlbumWithTracksRequest request) {
        try {
            Album album = musicStreamingService.createAlbumWithTracks(request.album(), request.tracks());
            return new ResponseEntity<>(album, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Бизнес-операция 2: Статистика пользователя
    @GetMapping("/users/{userId}/statistics")
    public ResponseEntity<MusicStreamingService.UserStatistics> getUserStatistics(@PathVariable Long userId) {
        try {
            MusicStreamingService.UserStatistics statistics = musicStreamingService.getUserStatistics(userId);
            return new ResponseEntity<>(statistics, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Бизнес-операция 3: Копирование плейлиста
    @PostMapping("/playlists/{playlistId}/copy")
    public ResponseEntity<Playlist> copyPlaylist(
            @PathVariable Long playlistId,
            @RequestBody CopyPlaylistRequest request) {
        try {
            Playlist newPlaylist = musicStreamingService.copyPlaylist(
                    playlistId, request.newPlaylistName(), request.targetUserId());
            return new ResponseEntity<>(newPlaylist, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Бизнес-операция 4: Рекомендации треков
    @GetMapping("/users/{userId}/recommendations")
    public List<Track> getRecommendedTracks(@PathVariable Long userId) {
        return musicStreamingService.getRecommendedTracks(userId);
    }

    // Бизнес-операция 5: Поиск музыки
    @GetMapping("/search")
    public MusicStreamingService.MusicSearchResult searchMusic(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer minDuration,
            @RequestParam(required = false) Integer maxDuration) {
        return musicStreamingService.searchMusic(query, genre, minDuration, maxDuration);
    }

    // DTO классы для запросов
    public record AlbumWithTracksRequest(Album album, List<Track> tracks) {}
    public record CopyPlaylistRequest(String newPlaylistName, Long targetUserId) {}
}