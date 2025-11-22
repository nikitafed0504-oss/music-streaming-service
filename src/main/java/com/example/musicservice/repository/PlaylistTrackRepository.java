package com.example.musicservice.repository;

import com.example.musicservice.model.PlaylistTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistTrackRepository extends JpaRepository<PlaylistTrack, Long> {
    List<PlaylistTrack> findByPlaylistIdOrderByPositionAsc(Long playlistId);
    Optional<PlaylistTrack> findByPlaylistIdAndTrackId(Long playlistId, Long trackId);
    Optional<PlaylistTrack> findByPlaylistIdAndPosition(Long playlistId, Integer position);
    void deleteByPlaylistIdAndTrackId(Long playlistId, Long trackId);

    @Query("SELECT COUNT(pt) FROM PlaylistTrack pt JOIN pt.playlist p WHERE p.user.id = :userId")
    int countTracksByUserId(@Param("userId") Long userId);

    @Query("SELECT COALESCE(SUM(t.duration), 0) FROM PlaylistTrack pt JOIN pt.track t JOIN pt.playlist p WHERE p.user.id = :userId")
    int sumDurationByUserId(@Param("userId") Long userId);

    @Query("SELECT COUNT(DISTINCT pt.track.artist) FROM PlaylistTrack pt JOIN pt.playlist p WHERE p.user.id = :userId")
    int countUniqueArtistsByUserId(@Param("userId") Long userId);
}