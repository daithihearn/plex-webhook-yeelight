package ie.daithi.yeelight.service

import com.mollin.yapi.YeelightDevice
import com.mollin.yapi.enumeration.YeelightEffect
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class YeelightService(
        @Value("\${yeelight.delay.in.millis}") private val delayInMillis: Int
) : LightService {

    override fun toggle(url: String) {
        // Instantiate your device (with its IP)
        val device = YeelightDevice(url)
        device.toggle()
    }

    override fun turnOn(url: String) {
        // Instantiate your device (with its IP)
        val device = YeelightDevice(url)
        // Switch on the device
        device.setPower(true)
    }

    override fun turnOnFade(url: String) {
        // Instantiate your device (with its IP)
        val device = YeelightDevice(url)
        // Change device color
        device.setRGB(255, 180, 100)
        // Change device brightness
        device.setBrightness(100)
        // Set effect
        device.setEffect(YeelightEffect.SMOOTH)
        // Set duration of effect
        device.setDuration(delayInMillis)
        // Switch on the device
        device.setPower(true)
    }

    override fun turnOff(url: String) {
        // Instantiate your device (with its IP)
        val device = YeelightDevice(url)
        // Switch off the device
        device.setPower(false)
    }

    override fun turnOffFade(url: String) {
        // Instantiate your device (with its IP)
        val device = YeelightDevice(url)
        // Set effect
        device.setEffect(YeelightEffect.SMOOTH)
        // Set duration of effect
        device.setDuration(delayInMillis)
        // Switch off the device
        device.setPower(false)
    }
}