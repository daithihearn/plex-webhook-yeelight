package ie.daithi.yeelight.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.mollin.yapi.YeelightDevice;

@Service
public class YeelightService implements LightService {

	@Value("${yeelight.url}")
	private String yeelightUrl;

    @Override
    public void turnOn() throws Exception {

        // Instantiate your device (with its IP)
        YeelightDevice device = new YeelightDevice(yeelightUrl);
        // Switch on the device
        device.setPower(true);
        // Change device color
        device.setRGB(255, 180, 100);
        // Change device brightness
        device.setBrightness(100);

    }

	@Override
	public void turnOff() throws Exception {

		// Instantiate your device (with its IP)
		YeelightDevice device = new YeelightDevice(yeelightUrl);
		// Switch off the device
		device.setPower(false);

	}
}
