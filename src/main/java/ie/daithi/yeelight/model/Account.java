package ie.daithi.yeelight.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * {
 *   "id": 1,
 *   "thumb": "https://plex.tv/users/1022b120ffbaa/avatar?c=1465525047",
 *   "title": "elan"
 * }
 */

@Data
@EqualsAndHashCode
public class Account {
    private Long id;
    private String thumb;
    private String title;
}
