package ie.daithi.yeelight.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "ie.daithi.yeelight")
@EntityScan(basePackages= { "ie.daithi.yeelight" })
public class YeelightApplication {

    public static void main(String[] args) {
        SpringApplication.run(YeelightApplication.class, args);
    }

}
