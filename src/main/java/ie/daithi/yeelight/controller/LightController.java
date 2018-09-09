package ie.daithi.yeelight.controller;

import ie.daithi.yeelight.enumeration.Event;
import ie.daithi.yeelight.service.LightService;
import ie.daithi.yeelight.model.PlexPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LightController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LightController.class);

    @Autowired
    private LightService yeelightService;

    @RequestMapping(value = "/plexEndpoint", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public String plexPostEndpoint(@RequestBody PlexPayload payload ) throws Exception {
        LOGGER.info("Plex posted an event {}", payload.getEvent());

        Event event = Event.fromValue(payload.getEvent());

        if (event == null) {
        	LOGGER.info("Event type {} not supported.", payload.getEvent());
		} else if (Event.PLAY.equals(event) ||Event.RESUME.equals(event)) {
			yeelightService.turnOff();
		} else if (Event.STOP.equals(event) || Event.PAUSE.equals(event)) {
			yeelightService.turnOn();
		}

        return "Success!";
    }
}
