 1. Artists (Артисты)
Создать артиста
text
POST http://localhost:9090/api/artists
Content-Type: application/json

{
  "name": "Queen",
  "genre": "Rock"
}
Создать второго артиста
text
POST http://localhost:9090/api/artists
Content-Type: application/json

{
  "name": "Michael Jackson",
  "genre": "Pop"
}
Получить всех артистов
text
GET http://localhost:9090/api/artists
Получить артиста по ID
text
GET http://localhost:9090/api/artists/1
Обновить артиста
text
PUT http://localhost:9090/api/artists/1
Content-Type: application/json

{
  "name": "Queen Updated",
  "genre": "Classic Rock"
}
Удалить артиста
text
DELETE http://localhost:9090/api/artists/2
 2. Albums (Альбомы)
Создать альбом
text
POST http://localhost:9090/api/albums
Content-Type: application/json

{
  "title": "A Night at the Opera",
  "releaseYear": 1975
}
Создать второй альбом
text
POST http://localhost:9090/api/albums
Content-Type: application/json

{
  "title": "Thriller",
  "releaseYear": 1982
}
Получить все альбомы
text
GET http://localhost:9090/api/albums
Получить альбом по ID
text
GET http://localhost:9090/api/albums/1
Обновить альбом
text
PUT http://localhost:9090/api/albums/1
Content-Type: application/json

{
  "title": "A Night at the Opera (Remastered)",
  "releaseYear": 1975
}
Удалить альбом
text
DELETE http://localhost:9090/api/albums/2
3. Users (Пользователи)
Создать пользователя
text
POST http://localhost:9090/api/users
Content-Type: application/json

{
  "username": "john_doe",
  "email": "john@example.com"
}
Создать второго пользователя
text
POST http://localhost:9090/api/users
Content-Type: application/json

{
  "username": "jane_smith",
  "email": "jane@example.com"
}
Получить всех пользователей
text
GET http://localhost:9090/api/users
Получить пользователя по ID
text
GET http://localhost:9090/api/users/1
Обновить пользователя
text
PUT http://localhost:9090/api/users/1
Content-Type: application/json

{
  "username": "john_doe_updated",
  "email": "john.updated@example.com"
}
Удалить пользователя
text
DELETE http://localhost:9090/api/users/2
🎵 4. Tracks (Треки)
Создать трек (с артистом и альбомом)
text
POST http://localhost:9090/api/tracks
Content-Type: application/json

{
  "title": "Bohemian Rhapsody",
  "duration": 355,
  "artist": {
    "id": 1
  },
  "album": {
    "id": 1
  }
}
Создать второй трек
text
POST http://localhost:9090/api/tracks
Content-Type: application/json

{
  "title": "Billie Jean",
  "duration": 294,
  "artist": {
    "id": 2
  },
  "album": {
    "id": 2
  }
}
Получить все треки
text
GET http://localhost:9090/api/tracks
Получить трек по ID
text
GET http://localhost:9090/api/tracks/1
Получить треки по артисту
text
GET http://localhost:9090/api/tracks/artist/1
Получить треки по альбому
text
GET http://localhost:9090/api/tracks/album/1
Обновить трек
text
PUT http://localhost:9090/api/tracks/1
Content-Type: application/json

{
  "title": "Bohemian Rhapsody (Extended)",
  "duration": 420,
  "artist": {
    "id": 1
  },
  "album": {
    "id": 1
  }
}
Удалить трек
text
DELETE http://localhost:9090/api/tracks/2
📝 5. Playlists (Плейлисты)
Создать плейлист (с пользователем)
text
POST http://localhost:9090/api/playlists
Content-Type: application/json

{
  "name": "My Favorites",
  "user": {
    "id": 1
  }
}
Создать второй плейлист
text
POST http://localhost:9090/api/playlists
Content-Type: application/json

{
  "name": "Rock Classics",
  "user": {
    "id": 1
  }
}
Получить все плейлисты
text
GET http://localhost:9090/api/playlists
Получить плейлист по ID
text
GET http://localhost:9090/api/playlists/1
Получить плейлисты пользователя
text
GET http://localhost:9090/api/playlists/user/1
Обновить плейлист
text
PUT http://localhost:9090/api/playlists/1
Content-Type: application/json

{
  "name": "My Ultimate Favorites"
}
Удалить плейлист
text
DELETE http://localhost:9090/api/playlists/2
 6. Playlist Tracks (Треки в плейлистах)
Добавить трек в плейлист
text
POST http://localhost:9090/api/playlists/1/tracks/1
Добавить второй трек в плейлист
text
POST http://localhost:9090/api/playlists/1/tracks/2
Переупорядочить треки в плейлисте
text
PUT http://localhost:9090/api/playlists/1/tracks/2/reorder?newPosition=0
Удалить трек из плейлиста
text
DELETE http://localhost:9090/api/playlists/1/tracks/1

URL: http://localhost:9090/api/artists/1
бизнес операции
GET /api/tracks/genre/{genre} - треки по жанру

GET /api/users/{userId}/stats - статистика пользователя

GET /api/tracks/search?query={query} - поиск треков

POST /api/playlists/{playlistId}/tracks/{trackId} - добавить трек в плейлист

DELETE /api/playlists/{playlistId}/tracks/{trackId} - удалить трек из плейлиста

PUT /api/playlists/{playlistId}/tracks/{trackId}/reorder - изменить порядок треков
