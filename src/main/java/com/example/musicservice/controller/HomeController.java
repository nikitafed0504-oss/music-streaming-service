package com.example.musicservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return """
               <div style="text-align: center; padding: 50px;">
                 <h1>🎵 Music Streaming Service 🎵</h1>
                 <p>Service is running successfully on port 9090!</p>
                 <div style="margin-top: 30px;">
                   <h3>Available Endpoints:</h3>
                   <ul style="list-style: none; padding: 0;">
                     <li><a href="/health">/health</a> - Health check</li>
                     <li><a href="/api/artists">/api/artists</a> - Artists API</li>
                     <li><a href="/api/albums">/api/albums</a> - Albums API</li>
                     <li><a href="/api/tracks">/api/tracks</a> - Tracks API</li>
                     <li><a href="/api/users">/api/users</a> - Users API</li>
                     <li><a href="/api/playlists">/api/playlists</a> - Playlists API</li>
                     <li><a href="/api/music/operations">/api/music/operations</a> - Business Operations</li>
                   </ul>
                 </div>
               </div>
               """;
    }

    @GetMapping("/health")
    public String health() {
        return """
               {
                 "status": "OK",
                 "service": "Music Streaming API",
                 "timestamp": "%s",
                 "version": "1.0"
               }
               """.formatted(java.time.LocalDateTime.now());
    }
}