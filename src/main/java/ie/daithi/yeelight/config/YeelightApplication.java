package ie.daithi.yeelight.config;

import java.util.Stack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication(scanBasePackages = "ie.daithi.yeelight")
@EnableAutoConfiguration
@EntityScan(basePackages= { "ie.daithi.yeelight" })
public class YeelightApplication {

    public static void main(String[] args) {
        SpringApplication.run(YeelightApplication.class, args);
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Stack<String> errors() {
        return new Stack<String>();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Stack<String> responses() {
        return new Stack<String>();
    }

}
