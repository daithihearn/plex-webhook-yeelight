package ie.daithi.yeelight.enumeration

enum class Event(private val value: String) {
    PLAY("media.play"), RESUME("media.resume"), STOP("media.stop"), PAUSE("media.pause");

    companion object {
        fun fromValue(value: String?): Event? {
            for (e in values()) {
                if (e.value.equals(value)) {
                    return e
                }
            }
            return null
        }
    }

}