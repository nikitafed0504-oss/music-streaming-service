package com.example.musicservice.repository;

import com.example.musicservice.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
    List<Track> findByArtistId(Long artistId);
    List<Track> findByAlbumId(Long albumId);

    @Query("SELECT t FROM Track t WHERE t.artist.genre = :genre")
    List<Track> findByArtistGenre(@Param("genre") String genre);

    @Query("SELECT t FROM Track t WHERE t.duration BETWEEN :minDuration AND :maxDuration")
    List<Track> findByDurationBetween(@Param("minDuration") Integer minDuration, @Param("maxDuration") Integer maxDuration);

    @Query("SELECT DISTINCT t FROM Track t WHERE t.artist.genre IN (" +
            "SELECT DISTINCT t2.artist.genre FROM PlaylistTrack pt " +
            "JOIN pt.track t2 JOIN pt.playlist p " +
            "WHERE p.user.id = :userId) " +
            "AND t.id NOT IN (" +
            "SELECT t3.id FROM PlaylistTrack pt2 " +
            "JOIN pt2.track t3 JOIN pt2.playlist p2 " +
            "WHERE p2.user.id = :userId)")
    List<Track> findRecommendedTracksByUserId(@Param("userId") Long userId);
}