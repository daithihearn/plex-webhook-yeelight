package ie.daithi.yeelight.model

/**
 * {
 * "local": true,
 * "publicAddress": "200.200.200.200",
 * "title": "Plex Web (Safari)",
 * "uuid": "r6yfkdnfggbh2bdnvkffwbms"
 * }
 */
data class Player(
    val local: Boolean? = null,
    val publicAddress: String? = null,
    val title: String? = null,
    val uuid: String? = null
)