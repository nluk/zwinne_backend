# zwinne_backend

### Import projektu IntelliJ Idea
- File > Open
- Wybieramy folder zwinne_backend
- W prawym dolnym rogu pojawi się komunikat Maven build script found, klinąć import

### Uruchomienie bazy
- Instalujemy dockera oraz docker-compose https://docs.docker.com/get-docker/
- W katalogu /zwinne_backend/docker wywołujemy polecanie ``docker-compose up``
- Pierwsze uruchomienie rozpocznie się od pobierania obrazu MongoDB i MongoExpress

```
Creating network "docker_default" with the default driver
Pulling mongo (mongo:latest)...
latest: Pulling from library/mongo
f22ccc0b8772: ...
```
- Poprawnie uruchomiona baza powinna wyświetlić w konsoli dwa komunikaty:

```
mongo_1         | MongoDB init process complete; ready for start up.
```
```
mongoexpress_1  | Mongo Express server listening at http://0.0.0.0:8081
```

# Najpierw uruchamiamy bazę, potem aplikację - działają niezależnie
- aplikacja nasłuchuje na porcie 8080
- panel administracyjny Mongo Express nasłuchuje na porcie 8081
- baza nasłuchuje na porcie 27017
- konfiguracja aplikacji znajduje się w pliku ``application.yml``
- konfiguracja bazy korzysta z pliku ``.env`` oraz ``init.sh``
