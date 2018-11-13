package ie.daithi.yeelight.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Data
@Component
@SessionScope
public class Response {
    private List<String> data = new ArrayList<String>();

    public void push(String data) {
        this.data.add(data);
    }

    public void reset() {
        this.data = new ArrayList<String>();

    }
}
