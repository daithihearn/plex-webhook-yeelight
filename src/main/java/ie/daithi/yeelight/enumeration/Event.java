package ie.daithi.yeelight.enumeration;

import lombok.Getter;

public enum Event {

    PLAY("media.play"), STOP("media.stop"), PAUSE("media.pause");

    @Getter
    private String value;

    Event (String value) {
        this.value = value;
    }

    public static Event fromValue(String value) {
		for(Event e : values()){
			if( e.getValue().equals(value)){
				return e;
			}
		}
		return null;
    }
}
