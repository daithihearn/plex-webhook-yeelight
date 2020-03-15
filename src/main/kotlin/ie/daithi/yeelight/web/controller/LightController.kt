package ie.daithi.yeelight.web.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.mollin.yapi.exception.YeelightSocketException
import ie.daithi.yeelight.enumeration.Event
import ie.daithi.yeelight.model.PlexPayload
import ie.daithi.yeelight.service.LightService
import ie.daithi.yeelight.web.exceptions.BadGatewayException
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class LightController (
    private val yeelightService: LightService,
    @Value("\${yeelight.url}") private val yeelightUrl: String,
    @Value("#{'\${plex.supported.users}'.split(',')}")
    private var supportedUsers: List<Long?>? = null,
    @Value("#{'\${plex.supported.players}'.split(',')}") private val supportedPlayers: List<String>? = null,
    @Value("#{'\${plex.supported.media}'.split(',')}") private var supportedMedia: MutableList<String?>? = null,
    private val objectMapper: ObjectMapper
) {

    @PostMapping("/toggle")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @Throws(BadGatewayException::class)
    fun toggleLivingroomEndpoint() {

        try {
            yeelightService.toggle(yeelightUrl)
        } catch (ex : YeelightSocketException) {
            throw BadGatewayException(ex.message!!)
        }
    }

    @PostMapping("/turnoff")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @Throws(BadGatewayException::class)
    fun turnOffLivingroomEndpoint() {
        try {
            yeelightService.turnOff(yeelightUrl)
        } catch (ex : YeelightSocketException) {
            throw BadGatewayException(ex.message!!)
        }
    }

    @PostMapping("/turnon")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @Throws(BadGatewayException::class)
    fun turnOnLivingroomEndpoint(request: HttpServletRequest?, response: HttpServletResponse?) {
        try {
            yeelightService.turnOn(yeelightUrl)
        } catch (ex : YeelightSocketException) {
            throw BadGatewayException(ex.message!!)
        }
    }


    @PostMapping(value = ["/plexEndpoint"])
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @Throws(BadGatewayException::class)
    fun plexPostEndpoint(request: MultipartHttpServletRequest, @RequestParam("files") files: Array<MultipartFile?>) {
        logger.info("Files: {}", files.size)
        val payloadString: String = request.getParameter("payload")
        val payload: PlexPayload = objectMapper.readValue(payloadString, PlexPayload::class.java)
        logger.info("Payload: {}", payload)
        val event = Event.fromValue(payload.event)
        if (event == null) {
            logger.info("Event type '{}' not supported.", payload.event)
        } else if (payload.account != null && !supportedUsers!!.contains(payload.account.id)) {
            logger.info("User '{}' is not supported.", payload.account.id)
        } else if (payload.player != null && !supportedPlayers!!.contains(payload.player.uuid)) {
            logger.info("Player '{}' is not supported.", payload.player.uuid)
        } else if (payload.metadata != null && !supportedMedia!!.contains(payload.metadata.type)) {
            logger.info("Media type '{}' is not supported.", payload.metadata.type)
        } else if (Event.PLAY.equals(event) || Event.RESUME.equals(event)) {
            logger.info("Turning light off.")
            yeelightService.turnOffFade(yeelightUrl)
        } else if (Event.STOP.equals(event) || Event.PAUSE.equals(event)) {
            logger.info("Turning light on.")
            yeelightService.turnOnFade(yeelightUrl)
        } else {
            logger.info("None of the above.")
        }
    }

    companion object {
        private val logger: Logger = LogManager.getLogger(LightController)
    }

}