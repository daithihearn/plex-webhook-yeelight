package ie.daithi.yeelight.controller;

import ie.daithi.yeelight.enumeration.Event;
import ie.daithi.yeelight.service.LightService;
import ie.daithi.yeelight.model.PlexPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/")
public class LightController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LightController.class);

    @Autowired
    private LightService yeelightService;

	@Value("#{'${plex.supported.users}'.split(',')}")
	private List<Long> supportedUsers;

	@Value("#{'${plex.supported.players}'.split(',')}")
	private List<String> supportedPlayers;

	@Value("#{'${plex.supported.media}'.split(',')}")
	private List<String> supportedMedia;

	@RequestMapping(value = "/plexEndpoint", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public String plexPostEndpoint(@RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file) throws Exception {
        LOGGER.info("Plex posted an event {}", file);
/*
        Event event = Event.fromValue(payload.getEvent());

        if (event == null) {
        	LOGGER.info("Event type '{}' not supported.", payload.getEvent());
		} else if (payload.getAccount() != null && !supportedUsers.contains(payload.getAccount().getId())) {
			LOGGER.info("User '{}' is not supported.", payload.getAccount().getId());
		} else if (payload.getPlayer() != null && !supportedPlayers.contains(payload.getPlayer().getUuid())) {
			LOGGER.info("Player '{}' is not supported.", payload.getPlayer().getUuid());
		} else if (payload.getMetadata() != null && !supportedMedia.contains(payload.getMetadata().getType())) {
			LOGGER.info("Media type '{}' is not supported.", payload.getMetadata().getType());
		} else if (Event.PLAY.equals(event) ||Event.RESUME.equals(event)) {
			yeelightService.turnOff();
		} else if (Event.STOP.equals(event) || Event.PAUSE.equals(event)) {
			yeelightService.turnOn();
		}
*/
        return "Success!";
    }
}
