package ie.daithi.yeelight.controller;

import ie.daithi.yeelight.service.LightService;
import ie.daithi.yeelight.model.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.mollin.yapi.exception.YeelightSocketException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LightController {

	private Logger LOGGER = LoggerFactory.getLogger(LightController.class);

    @Autowired
	private LightService yeelightService;

	@Resource
	private Response errors;

	@Value("${yeelight.livingroom.url}")
	private String yeelightLivingroomUrl;

	@Value("${yeelight.bedroom.url}")
	private String yeelightBedroomUrl;

	@RequestMapping(value = "/toggle/livingroom", method = RequestMethod.GET)
	public RedirectView toggleLivingroomEndpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			yeelightService.toggle(yeelightLivingroomUrl);
		} catch(YeelightSocketException ex) {
			errors.push("Livingroom appears to be powered off.");
		}
		return new RedirectView("/home");
	}

	@RequestMapping(value = "/turnoff/livingroom", method = RequestMethod.GET)
	public RedirectView turnOffLivingroomEndpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			yeelightService.turnOff(yeelightLivingroomUrl);
		} catch(YeelightSocketException ex) {
			errors.push("Livingroom appears to be powered off.");
		}
		return new RedirectView("/home");
	}

	@RequestMapping(value = "/turnon/livingroom", method = RequestMethod.GET)
	public RedirectView turnOnLivingroomEndpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			yeelightService.turnOn(yeelightLivingroomUrl);
		} catch(YeelightSocketException ex) {
			errors.push("Livingroom appears to be powered off.");
		}
		return new RedirectView("/home");
	}

	@RequestMapping(value = "/toggle/bedroom", method = RequestMethod.GET)
	public RedirectView toggleBedroomEndpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			yeelightService.toggle(yeelightBedroomUrl);
		} catch(YeelightSocketException ex) {
			errors.push("Bedroom appears to be powered off.");
		}
		return new RedirectView("/home");
	}

	@RequestMapping(value = "/turnoff/bedroom", method = RequestMethod.GET)
	public RedirectView turnOffBedroomEndpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			yeelightService.turnOff(yeelightBedroomUrl);
		} catch(YeelightSocketException ex) {
			errors.push("Bedroom appears to be powered off.");
		}
		return new RedirectView("/home");
	}

	@RequestMapping(value = "/turnon/bedroom", method = RequestMethod.GET)
	public RedirectView turnOnBedroomEndpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			yeelightService.turnOn(yeelightBedroomUrl);
		} catch(YeelightSocketException ex) {
			errors.push("Bedroom appears to be powered off.");
		}
		return new RedirectView("/home");
	}

	@RequestMapping(value = "/toggle/all", method = RequestMethod.GET)
	public RedirectView toggleAllEndpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			yeelightService.toggle(yeelightBedroomUrl);
		} catch(YeelightSocketException ex) {
			errors.push("Bedroom appears to be powered off.");
		}
		try {
			yeelightService.toggle(yeelightLivingroomUrl);
		} catch(YeelightSocketException ex) {
			errors.push("Livingroom appears to be powered off.");
		}
		return new RedirectView("/home");
	}

	@RequestMapping(value = "/turnoff/all", method = RequestMethod.GET)
	public RedirectView turnOffAllEndpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			yeelightService.turnOff(yeelightBedroomUrl);
		} catch(YeelightSocketException ex) {
			errors.push("Bedroom appears to be powered off.");
		}
		try {
			yeelightService.turnOff(yeelightLivingroomUrl);
		} catch(YeelightSocketException ex) {
			errors.push("Livingroom appears to be powered off.");
		}
		return new RedirectView("/home");
	}

	@RequestMapping(value = "/turnon/all", method = RequestMethod.GET)
	public RedirectView turnOnAllEndpoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			yeelightService.turnOn(yeelightBedroomUrl);
		} catch(YeelightSocketException ex) {
			errors.push("Bedroom appears to be powered off.");
		}
		try {
			yeelightService.turnOn(yeelightLivingroomUrl);
		} catch(YeelightSocketException ex) {
			errors.push("Livingroom appears to be powered off.");
		}
		return new RedirectView("/home");
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("errors", errors.getData());
		errors.reset();
		return "home";
	}
}
