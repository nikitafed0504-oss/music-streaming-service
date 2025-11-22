-- src/main/resources/data.sql

-- Артисты
INSERT INTO artists (id, name, genre) VALUES
(1, 'The Beatles', 'Rock'),
(2, 'Taylor Swift', 'Pop'),
(3, 'Miles Davis', 'Jazz'),
(4, 'Mozart', 'Classical'),
(5, 'Queen', 'Rock'),
(6, 'Ed Sheeran', 'Pop'),
(7, 'John Coltrane', 'Jazz');

-- Альбомы
INSERT INTO albums (id, title, release_year, artist_id) VALUES
(1, 'Abbey Road', 1969, 1),
(2, '1989', 2014, 2),
(3, 'Kind of Blue', 1959, 3),
(4, 'The Magic Flute', 1791, 4),
(5, 'A Night at the Opera', 1975, 5),
(6, 'Divide', 2017, 6),
(7, 'A Love Supreme', 1965, 7);

-- Треки
INSERT INTO tracks (id, title, duration, artist_id, album_id) VALUES
(1, 'Come Together', 259, 1, 1),
(2, 'Something', 182, 1, 1),
(3, 'Shake It Off', 219, 2, 2),
(4, 'Blank Space', 231, 2, 2),
(5, 'So What', 562, 3, 3),
(6, 'Freddie Freeloader', 589, 3, 3),
(7, 'Queen of the Night Aria', 180, 4, 4),
(8, 'Der Hölle Rache', 174, 4, 4),
(9, 'Bohemian Rhapsody', 354, 5, 5),
(10, 'Love of My Life', 221, 5, 5),
(11, 'Shape of You', 233, 6, 6),
(12, 'Perfect', 263, 6, 6),
(13, 'Part 1: Acknowledgement', 463, 7, 7),
(14, 'Part 2: Resolution', 448, 7, 7);

-- Пользователи
INSERT INTO users (id, username, email) VALUES
(1, 'musiclover', 'music@example.com'),
(2, 'jazzfan', 'jazz@example.com'),
(3, 'classical_enthusiast', 'classical@example.com'),
(4, 'rockfan', 'rock@example.com');

-- Плейлисты
INSERT INTO playlists (id, name, user_id) VALUES
(1, 'My Favorites', 1),
(2, 'Rock Classics', 1),
(3, 'Jazz Collection', 2),
(4, 'Classical Masterpieces', 3),
(5, 'Workout Mix', 4);

-- Треки в плейлистах
INSERT INTO playlist_tracks (id, playlist_id, track_id, position) VALUES
(1, 1, 1, 0), (2, 1, 3, 1), (3, 1, 9, 2), (4, 1, 11, 3),
(5, 2, 1, 0), (6, 2, 2, 1), (7, 2, 9, 2), (8, 2, 10, 3),
(9, 3, 5, 0), (10, 3, 6, 1), (11, 3, 13, 2), (12, 3, 14, 3),
(13, 4, 7, 0), (14, 4, 8, 1),
(15, 5, 3, 0), (16, 5, 11, 1), (17, 5, 9, 2);