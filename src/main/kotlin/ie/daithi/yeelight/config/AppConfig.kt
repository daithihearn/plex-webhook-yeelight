package ie.daithi.yeelight.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@ComponentScan(basePackages = ["ie.daithi.yeelight"])
@EnableSwagger2
class AppConfig () {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfo( "Yeelight API",
                "A RESTFul API for controlling a yeelight",
                "1.0.0",
                "blah",
                Contact("Daithi Hearn","https://github.com/daithihearn", "daithi.hearn@gmail.com"),
                "", "", Collections.emptyList())
    }

}