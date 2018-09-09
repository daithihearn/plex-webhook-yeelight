# plex-webhooks-yeelight
A simple API for Plex webhooks for the Yeelight.

## Description
This is a dockerised Spring Boot webservice for Plex webhooks to control a yeelight.

## Prerequisites
 - Plex pass subscription
 - Docker
 - Yapi built into you Maven repo
 
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