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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class LightController {

	private Logger LOGGER = LoggerFactory.getLogger(LightController.class);

    @Autowired
    private LightService yeelightService;

	@Value("${yeelight.livingroom.url}")
	private String yeelightLivingroomUrl;

	@Value("${yeelight.bedroom.url}")
	private String yeelightBedroomUrl;

	@Value("#{'${plex.supported.users}'.split(',')}")
	private List<Long> supportedUsers;

	@Value("#{'${plex.supported.players}'.split(',')}")
	private List<String> supportedPlayers;

	@Value("#{'${plex.supported.media}'.split(',')}")
	private List<String> supportedMedia;

	@Autowired
	private ObjectMapper jacksonObjectMapper;

	@RequestMapping(value = "/toggle/livingroom", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void toggleLivingroomEndpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		yeelightService.toggle(yeelightLivingroomUrl);
		response.sendRedirect("/success");
	}

	@RequestMapping(value = "/turnoff/livingroom", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void turnOffLivingroomEndpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		yeelightService.turnOff(yeelightLivingroomUrl);
		response.sendRedirect("/success");
	}

	@RequestMapping(value = "/turnon/livingroom", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void turnOnLivingroomEndpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		yeelightService.turnOn(yeelightLivingroomUrl);
		response.sendRedirect("/success");
	}

	@RequestMapping(value = "/toggle/bedroom", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void toggleBedroomEndpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		yeelightService.toggle(yeelightBedroomUrl);
		response.sendRedirect("/success");
	}

	@RequestMapping(value = "/turnoff/bedroom", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void turnOffBedroomEndpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		yeelightService.turnOff(yeelightBedroomUrl);
		response.sendRedirect("/success");
	}

	@RequestMapping(value = "/turnon/bedroom", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void turnOnBedroomEndpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		yeelightService.turnOn(yeelightBedroomUrl);
		response.sendRedirect("/success");
	}

	@RequestMapping(value = "/toggle/all", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void toggleAllEndpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		yeelightService.toggle(yeelightBedroomUrl);
		yeelightService.toggle(yeelightLivingroomUrl);
		response.sendRedirect("/success");
	}

	@RequestMapping(value = "/turnoff/all", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void turnOffAllEndpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		yeelightService.turnOff(yeelightBedroomUrl);
		yeelightService.turnOff(yeelightLivingroomUrl);
		response.sendRedirect("/success");
	}

	@RequestMapping(value = "/turnon/all", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void turnOnAllEndpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		yeelightService.turnOn(yeelightBedroomUrl);
		yeelightService.turnOn(yeelightLivingroomUrl);
		response.sendRedirect("/success");
	}

	@RequestMapping(value = "/plexEndpoint", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void plexPostEndpoint(MultipartHttpServletRequest request, HttpServletResponse response, @RequestParam("files") MultipartFile[] files ) throws Exception {

		LOGGER.info("Files: {}", files.length);

		String payloadString = request.getParameter("payload");
		PlexPayload payload = jacksonObjectMapper.readValue(payloadString, PlexPayload.class);

		LOGGER.info("Payload: {}", payload);

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
			LOGGER.info("Turning light off.");
			yeelightService.turnOffFade(yeelightLivingroomUrl);
		} else if (Event.STOP.equals(event) || Event.PAUSE.equals(event)) {
			LOGGER.info("Turning light on.");
			yeelightService.turnOnFade(yeelightLivingroomUrl);
		} else {
			LOGGER.info("None of the above.");
		}

		response.sendRedirect("/success");
    }

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
    public String successEndpoint() {
		return "Success!";
	}
}
