package ie.daithi.yeelight.service

interface LightService {
    fun toggle(url: String)
    fun turnOn(url: String)
    fun turnOff(url: String)
    fun turnOnFade(url: String)
    fun turnOffFade(url: String)
}