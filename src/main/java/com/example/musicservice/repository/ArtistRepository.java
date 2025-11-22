package com.example.musicservice.repository;

import com.example.musicservice.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    List<Artist> findByGenre(String genre);

    @Query("SELECT a FROM Artist a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Artist> findByNameContainingIgnoreCase(@Param("name") String name);
}