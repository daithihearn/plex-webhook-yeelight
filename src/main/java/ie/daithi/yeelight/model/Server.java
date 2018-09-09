package ie.daithi.yeelight.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * {
 *   "title": "Office",
 *   "uuid": "54664a3d8acc39983675640ec9ce00b70af9cc36"
 * }
 */
@Data
@EqualsAndHashCode
public class Server {
    private String title;
    private String uuid;
}
