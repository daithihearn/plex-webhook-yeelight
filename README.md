# plex-webhooks-yeelight
A simple API for Plex webhooks for the Yeelight.

## Description
This is a Kotlin and Spring Boot webservice for Plex webhooks to control a yeelight.

## Basic functionality
 - Light will turn on when a movie stops.
 - Light will turn off when a movie starts.
 
 The API definition can be found here http://localhost:8080/swagger-ui.html#/

## Requirements
 - Plex pass subscription
 - Maven
 - Yapi built into you Maven repo
 - Application must be running on the same network as the yeelight
 - `LAN Control` must be enabled on your Yeelight
 
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