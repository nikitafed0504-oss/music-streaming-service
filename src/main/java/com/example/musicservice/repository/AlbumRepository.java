package com.example.musicservice.repository;

import com.example.musicservice.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByArtistId(Long artistId);
    List<Album> findByReleaseYear(Integer releaseYear);

    @Query("SELECT a FROM Album a WHERE a.releaseYear BETWEEN :startYear AND :endYear")
    List<Album> findByReleaseYearBetween(@Param("startYear") Integer startYear, @Param("endYear") Integer endYear);
}