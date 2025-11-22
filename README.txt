# Music Streaming Service

Сервис для стриминга музыки с возможностью управления артистами, альбомами, треками и плейлистами.

## Основные сущности

- **Artist** - музыкальные исполнители
- **Album** - музыкальные альбомы
- **Track** - отдельные треки
- **User** - пользователи сервиса  
- **Playlist** - плейлисты пользователей
- **PlaylistTrack** - связь треков с плейлистами (с поддержкой порядка)

## Функциональность

### Базовые CRUD операции
- Полное управление артистами, альбомами, треками, пользователями и плейлистами
- Добавление/удаление треков из плейлистов
- Изменение порядка треков в плейлистах

### Бизнес-операции
1. **Создание альбома с треками** - атомарное создание альбома и его треков
2. **Поиск треков по жанру** - получение треков по жанру исполнителя
3. **Статистика пользователя** - информация о плейлистах и общей длительности
4. **Копирование плейлиста** - создание копии плейлиста для другого пользователя
5. **Рекомендации треков** - персональные рекомендации на основе плейлистов

## Технологии

- Java 21, Spring Boot 3.x
- PostgreSQL (реляционная БД)
- JPA/Hibernate
- Maven
- Docker (для запуска БД)

## Запуск проекта

1. Запустите PostgreSQL:
```bash
docker-compose up -d
1. Health Check
Method: GET

URL: http://localhost:9090/health

Description: Проверка работы сервиса

2. Главная страница
Method: GET

URL: http://localhost:9090/

Description: HTML страница со списком endpoints

3. Все артисты
Method: GET

URL: http://localhost:9090/api/artists

Response:

json
[
  {
    "id": 1,
    "name": "The Beatles",
    "genre": "Rock",
    "albums": [...],
    "tracks": [...]
  }
]
4. Артист по ID
Method: GET

URL: http://localhost:9090/api/artists/1

5. Все альбомы
Method: GET

URL: http://localhost:9090/api/albums

Response:

json
[
  {
    "id": 1,
    "title": "Abbey Road",
    "releaseYear": 1969,
    "artist": {...},
    "tracks": [...]
  }
]
6. Альбомы артиста
Method: GET

URL: http://localhost:9090/api/albums/artist/1

7. Все треки
Method: GET

URL: http://localhost:9090/api/tracks

8. Треки артиста
Method: GET

URL: http://localhost:9090/api/tracks/artist/1

9. Треки альбома
Method: GET

URL: http://localhost:9090/api/tracks/album/1

10. Все пользователи
Method: GET

URL: http://localhost:9090/api/users

11. Все плейлисты
Method: GET

URL: http://localhost:9090/api/playlists

12. Плейлисты пользователя
Method: GET

URL: http://localhost:9090/api/playlists/user/1

🚀 Бизнес-операции (GET)
13. Статистика пользователя
Method: GET

URL: http://localhost:9090/api/music/operations/users/1/statistics

Response:

json
{
  "user": {...},
  "playlistCount": 2,
  "totalTracks": 8,
  "totalDuration": 1874,
  "uniqueArtists": 3
}
14. Рекомендации треков
Method: GET

URL: http://localhost:9090/api/music/operations/users/1/recommendations

Response:

json
[
  {
    "id": 4,
    "title": "Blank Space", 
    "duration": 231,
    "artist": {...}
  }
]
15. Поиск музыки
Method: GET

URL: http://localhost:9090/api/music/operations/search?genre=Rock

Параметры:

genre - жанр для поиска

query - текст для поиска артистов

minDuration, maxDuration - диапазон длительности

📝 POST запросы (используйте эти)
16. Добавить трек в плейлист
Method: POST

URL: http://localhost:9090/api/playlists/1/tracks/5

Description: Добавляет трек с ID 5 в плейлист с ID 1

17. Создание альбома с треками (транзакция)
Method: POST

URL: http://localhost:9090/api/music/operations/albums/with-tracks

Body:

json
{
  "album": {
    "title": "New Album 2024",
    "releaseYear": 2024,
    "artist": {
      "id": 1
    }
  },
  "tracks": [
    {
      "title": "New Track 1",
      "duration": 180
    },
    {
      "title": "New Track 2", 
      "duration": 210
    }
  ]
}
18. Копирование плейлиста
Method: POST

URL: http://localhost:9090/api/music/operations/playlists/1/copy

Body:

json
{
  "newPlaylistName": "My Rock Copy",
  "targetUserId": 2
}
🔄 PUT запросы (для обновления)
19. Обновить артиста
Method: PUT

URL: http://localhost:9090/api/artists/1

Body:

json
{
  "name": "The Beatles Updated",
  "genre": "Classic Rock"
}
20. Обновить пользователя
Method: PUT

URL: http://localhost:9090/api/users/1

Body:

json
{
  "username": "updatedUsername",
  "email": "updated@example.com"
}
🗑️ DELETE запросы
21. Удалить трек из плейлиста
Method: DELETE

URL: http://localhost:9090/api/playlists/1/tracks/5

22. Удалить артиста
Method: DELETE

URL: http://localhost:9090/api/artists/1
