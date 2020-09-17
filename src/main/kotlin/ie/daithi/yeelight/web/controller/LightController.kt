package ie.daithi.yeelight.web.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.mollin.yapi.exception.YeelightSocketException
import ie.daithi.yeelight.enumeration.Event
import ie.daithi.yeelight.model.PlexPayload
import ie.daithi.yeelight.service.LightService
import ie.daithi.yeelight.web.exceptions.BadGatewayException
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
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
@Api(tags = ["Yeelight"], description = "These endpoints perform operations on a Yeelight")
class LightController (
        private val yeelightService: LightService,
        @Value("\${yeelight.host}") private val yeelightHost: String,
        @Value("#{'\${plex.supported.users}'.split(',')}") private var supportedUsers: List<Long>,
        @Value("#{'\${plex.supported.players}'.split(',')}") private val supportedPlayers: List<String>,
        @Value("#{'\${plex.supported.media}'.split(',')}") private val supportedMedia: MutableList<String?>,
        private val objectMapper: ObjectMapper
) {

    @PostMapping("/toggle")
    @ResponseStatus(value = HttpStatus.OK)
    @Throws(BadGatewayException::class)
    @ApiOperation(value = "Toggle", notes = "Toggles the light on and off")
    @ApiResponses(
            ApiResponse(code = 200, message = "Opertation was completed successfully"),
            ApiResponse(code = 502, message = "An issue occurred when calling the Yeelight.")
    )
    fun toggleLivingroomEndpoint() {
        try {
            yeelightService.toggle(yeelightHost)
        } catch (ex : YeelightSocketException) {
            throw BadGatewayException(ex.message!!)
        }
    }

    @PostMapping("/turnoff")
    @ResponseStatus(value = HttpStatus.OK)
    @Throws(BadGatewayException::class)
    @ApiOperation(value = "Turn Off", notes = "Turns the light off")
    @ApiResponses(
            ApiResponse(code = 200, message = "Opertation was completed successfully"),
            ApiResponse(code = 502, message = "An issue occurred when calling the Yeelight.")
    )
    fun turnOffLivingroomEndpoint() {
        try {
            yeelightService.turnOff(yeelightHost)
        } catch (ex : YeelightSocketException) {
            throw BadGatewayException(ex.message!!)
        }
    }

    @PostMapping("/turnon")
    @ResponseStatus(value = HttpStatus.OK)
    @Throws(BadGatewayException::class)
    @ApiOperation(value = "Turn On", notes = "Turns the light on")
    @ApiResponses(
            ApiResponse(code = 200, message = "Opertation was completed successfully"),
            ApiResponse(code = 502, message = "An issue occurred when calling the Yeelight.")
    )
    fun turnOnLivingroomEndpoint(request: HttpServletRequest?, response: HttpServletResponse?) {
        try {
            yeelightService.turnOn(yeelightHost)
        } catch (ex : YeelightSocketException) {
            throw BadGatewayException(ex.message!!)
        }
    }


    @PostMapping(value = ["/plexEndpoint"])
    @ResponseStatus(value = HttpStatus.OK)
    @Throws(BadGatewayException::class)
    @ApiOperation(value = "Plex Endpoint", notes = "Listens for calls from a Plex Media Server web hook. It parses the " +
            "payload and acts upon it. For the whitelisted players, media types and users: " +
            "\\n \\t 1. On a PLAY or RESUME event the light will fade out " +
            "\\n \\t 2. On a STOP or PAUSE event the light will fade in ")
    @ApiResponses(
            ApiResponse(code = 200, message = "Opertation was completed successfully"),
            ApiResponse(code = 502, message = "An issue occurred when calling the Yeelight.")
    )
    fun plexPostEndpoint(request: MultipartHttpServletRequest, @RequestParam("files") files: Array<MultipartFile?>) {

        val payloadString: String = request.getParameter("payload")
        val payload: PlexPayload = objectMapper.readValue(payloadString, PlexPayload::class.java)

        if(logger.isDebugEnabled)
            logger.debug("Webhook request: $payloadString")

        val event = Event.fromValue(payload.event)
        if (event == null) {
            if (logger.isDebugEnabled) logger.debug("Unsupported event: ${payload.event}")
        } else if (payload.account != null && !supportedUsers.contains(payload.account.id)) {
            if (logger.isDebugEnabled) logger.debug("User ${payload.account.id} is not supported.")
        } else if (payload.player != null && !supportedPlayers.contains(payload.player.uuid)) {
            if (logger.isDebugEnabled) logger.debug("Player '${payload.player.uuid}' is not supported.")
        } else if (payload.metadata != null && !supportedMedia.contains(payload.metadata.type)) {
            if (logger.isDebugEnabled) logger.debug("Media type '${payload.metadata.type}' is not supported.")
        } else if (Event.PLAY == event || Event.RESUME == event) {
            logger.info("Turning light off.")
            yeelightService.turnOffFade(yeelightHost)
        } else if (Event.STOP == event || Event.PAUSE == event) {
            logger.info("Turning light on.")
            yeelightService.turnOnFade(yeelightHost)
        } else {
            if (logger.isDebugEnabled) logger.debug("None of the above.")
        }
    }

    companion object {
        private val logger: Logger = LogManager.getLogger(LightController)
    }

}