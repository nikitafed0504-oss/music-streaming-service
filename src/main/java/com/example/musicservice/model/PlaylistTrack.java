package com.example.musicservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "playlist_tracks", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"playlist_id", "position"}),
        @UniqueConstraint(columnNames = {"playlist_id", "track_id"})
})
public class PlaylistTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id", nullable = false)
    @JsonBackReference("playlist-tracks")
    private Playlist playlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id", nullable = false)
    private Track track;

    @Column(nullable = false)
    private Integer position;
}