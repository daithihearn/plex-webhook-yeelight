package ie.daithi.yeelight.service;

import com.mollin.yapi.enumeration.YeelightEffect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.mollin.yapi.YeelightDevice;

@Service
public class YeelightService implements LightService {

	@Value("${yeelight.delay.in.millis}")
	private int delayInMillis;

	@Override
	public void toggle(String yeelightUrl) throws Exception {
		// Instantiate your device (with its IP)
		YeelightDevice device = new YeelightDevice(yeelightUrl);
		device.toggle();
	}

	@Override
    public void turnOn(String yeelightUrl) throws Exception {

        // Instantiate your device (with its IP)
        YeelightDevice device = new YeelightDevice(yeelightUrl);
        // Change device color
        device.setRGB(255, 180, 100);
        // Change device brightness
        device.setBrightness(100);
		// Set effect
		device.setEffect(YeelightEffect.SMOOTH);
		// Set duration of effect
		device.setDuration(delayInMillis);
		// Switch on the device
		device.setPower(true);

    }

	@Override
	public void turnOff(String yeelightUrl) throws Exception {

		// Instantiate your device (with its IP)
		YeelightDevice device = new YeelightDevice(yeelightUrl);
		// Set effect
		device.setEffect(YeelightEffect.SMOOTH);
		// Set duration of effect
		device.setDuration(delayInMillis);
		// Switch off the device
		device.setPower(false);

	}
}
