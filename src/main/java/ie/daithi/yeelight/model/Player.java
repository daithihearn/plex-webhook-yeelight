package ie.daithi.yeelight.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  {
 *      "local": true,
 *      "publicAddress": "200.200.200.200",
 *      "title": "Plex Web (Safari)",
 *      "uuid": "r6yfkdnfggbh2bdnvkffwbms"
 *  }
 */

@Data
@EqualsAndHashCode
public class Player {
	private Boolean local;
	private String publicAddress;
	private String title;
	private String uuid;
}
