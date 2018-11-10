package ie.daithi.yeelight.service;

public interface LightService {

	void toggle(String url) throws Exception;
	void turnOn(String url) throws Exception;
	void turnOff(String url) throws Exception;

	void turnOnFade(String yeelightUrl) throws Exception;

	void turnOffFade(String yeelightUrl) throws Exception;
}
