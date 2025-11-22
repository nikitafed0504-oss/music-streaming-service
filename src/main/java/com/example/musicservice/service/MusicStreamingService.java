package com.example.musicservice.service;

import com.example.musicservice.model.*;
import com.example.musicservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicStreamingService {
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final TrackRepository trackRepository;
    private final UserRepository userRepository;
    private final PlaylistRepository playlistRepository;
    private final PlaylistTrackRepository playlistTrackRepository;

    // Бизнес-операция 1: Создание альбома с треками в одной транзакции
    @Transactional
    public Album createAlbumWithTracks(Album album, List<Track> tracks) {
        Artist artist = artistRepository.findById(album.getArtist().getId())
                .orElseThrow(() -> new RuntimeException("Artist not found with id: " + album.getArtist().getId()));

        album.setArtist(artist);
        Album savedAlbum = albumRepository.save(album);

        for (Track track : tracks) {
            track.setAlbum(savedAlbum);
            track.setArtist(artist);
            trackRepository.save(track);
        }

        return albumRepository.findById(savedAlbum.getId()).orElseThrow();
    }

    // Бизнес-операция 2: Получение статистики пользователя
    @Transactional(readOnly = true)
    public UserStatistics getUserStatistics(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        int playlistCount = userRepository.countPlaylistsByUserId(userId);
        int totalTracksInPlaylists = playlistTrackRepository.countTracksByUserId(userId);
        int totalPlaylistDuration = playlistTrackRepository.sumDurationByUserId(userId);
        int uniqueArtistsCount = playlistTrackRepository.countUniqueArtistsByUserId(userId);

        return new UserStatistics(user, playlistCount, totalTracksInPlaylists, totalPlaylistDuration, uniqueArtistsCount);
    }

    // Бизнес-операция 3: Копирование плейлиста
    @Transactional
    public Playlist copyPlaylist(Long sourcePlaylistId, String newPlaylistName, Long targetUserId) {
        Playlist sourcePlaylist = playlistRepository.findById(sourcePlaylistId)
                .orElseThrow(() -> new RuntimeException("Source playlist not found"));

        User targetUser = userRepository.findById(targetUserId)
                .orElseThrow(() -> new RuntimeException("Target user not found"));

        Playlist newPlaylist = new Playlist();
        newPlaylist.setName(newPlaylistName);
        newPlaylist.setUser(targetUser);
        Playlist savedPlaylist = playlistRepository.save(newPlaylist);

        List<PlaylistTrack> sourceTracks = playlistTrackRepository.findByPlaylistIdOrderByPositionAsc(sourcePlaylistId);
        for (int i = 0; i < sourceTracks.size(); i++) {
            PlaylistTrack sourceTrack = sourceTracks.get(i);

            PlaylistTrack newPlaylistTrack = new PlaylistTrack();
            newPlaylistTrack.setPlaylist(savedPlaylist);
            newPlaylistTrack.setTrack(sourceTrack.getTrack());
            newPlaylistTrack.setPosition(i);
            playlistTrackRepository.save(newPlaylistTrack);
        }

        return playlistRepository.findById(savedPlaylist.getId()).orElseThrow();
    }

    // Бизнес-операция 4: Получение рекомендованных треков
    @Transactional(readOnly = true)
    public List<Track> getRecommendedTracks(Long userId) {
        return trackRepository.findRecommendedTracksByUserId(userId);
    }

    // Бизнес-операция 5: Поиск музыки по различным критериям
    @Transactional(readOnly = true)
    public MusicSearchResult searchMusic(String query, String genre, Integer minDuration, Integer maxDuration) {
        List<Artist> artists = artistRepository.findByNameContainingIgnoreCase(query);
        List<Track> tracksByGenre = trackRepository.findByArtistGenre(genre);
        List<Track> tracksByDuration = trackRepository.findByDurationBetween(minDuration, maxDuration);

        return new MusicSearchResult(artists, tracksByGenre, tracksByDuration);
    }

    // DTO классы для бизнес-операций
    public record UserStatistics(User user, int playlistCount, int totalTracks, int totalDuration, int uniqueArtists) {}

    public record MusicSearchResult(List<Artist> artists, List<Track> tracksByGenre, List<Track> tracksByDuration) {}
}