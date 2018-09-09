package ie.daithi.yeelight.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * {
 *      "librarySectionType": "artist",
 *      "ratingKey": "1936545",
 *      "key": "/library/metadata/1936545",
 *      "parentRatingKey": "1936544",
 *      "grandparentRatingKey": "1936543",
 *      "guid": "com.plexapp.agents.plexmusic://gracenote/track/7572499-91016293BE6BF7F1AB2F848F736E74E5/7572500-3CBAE310D4F3E66C285E104A1458B272?lang=en",
 *      "librarySectionID": 1224,
 *      "type": "track",
 *      "title": "Love The One You're With",
 *      "grandparentKey": "/library/metadata/1936543",
 *      "parentKey": "/library/metadata/1936544",
 *      "grandparentTitle": "Stephen Stills",
 *      "parentTitle": "Stephen Stills",
 *      "summary": "",
 *      "index": 1,
 *      "parentIndex": 1,
 *      "ratingCount": 6794,
 *      "thumb": "/library/metadata/1936544/thumb/1432897518",
 *      "art": "/library/metadata/1936543/art/1485951497",
 *      "parentThumb": "/library/metadata/1936544/thumb/1432897518",
 *      "grandparentThumb": "/library/metadata/1936543/thumb/1485951497",
 *      "grandparentArt": "/library/metadata/1936543/art/1485951497",
 *      "addedAt": 1000396126,
 *      "updatedAt": 1432897518
 * }
 */

@Data
@EqualsAndHashCode
public class Metadata {
    private String librarySectionType;
    private String ratingKey;
    private String key;
    private String parentRatingKey;
    private String grandparentRatingKey;
    private String guid;
    private Long librarySectionID;
    private String type;
    private String title;
    private String grandparentKey;
    private String parentKey;
    private String grandparentTitle;
    private String parentTitle;
    private String summary;
    private Long index;
    private Long parentIndex;
    private Long ratingCount;
    private String thumb;
    private String art;
    private String parentThumb;
    private String grandparentThumb;
    private String grandparentArt;
    private Long addedAt;
    private Long updatedAt;
    
}
