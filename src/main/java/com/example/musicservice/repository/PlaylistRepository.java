package com.example.musicservice.repository;

import com.example.musicservice.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByUserId(Long userId);

    @Query("SELECT COUNT(p) FROM Playlist p WHERE p.user.id = :userId")
    int countByUserId(@Param("userId") Long userId);
}