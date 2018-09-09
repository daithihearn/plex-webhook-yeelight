# plex-webhooks-yeelight
A simple API for Plex webhooks for the Yeelight.

## Description
This is a dockerised Spring Boot webservice for Plex webhooks to control a yeelight.

## Basic functionality
 - Light will turn on when a movie stops.
 - Light will turn off when a movie starts.

## Requirements
 - Plex pass subscription
 - Docker
 - Maven
 - Yapi built into you Maven repo
 - Application must be running on the same network as the yeelight
 - Must open a port on your router to allow access from Plex
 - Yeelight must be on `Developer mode`
 
## Running
Docker (recommended):
`docker-compose up --build`

Spring boot:
`./gradlew bootRun`

## References
### Plex Webhooks
https://support.plex.tv/articles/115002267687-webhooks/

### yapi
https://github.com/florian-mollin/yapi