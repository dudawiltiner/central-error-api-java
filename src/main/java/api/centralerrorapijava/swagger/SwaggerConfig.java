package api.centralerrorapijava.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Error Centre API Java",
                "In modern projects it is increasingly common to use architectures based on services or microservices. In these complex environments, errors can arise in different layers of the application (backend, frontend, mobile, desktop) and even in different services. Therefore, it is very important for developers to be able to centralize all error logs in one place, from which they can monitor and make better decisions. In this project I implement a Rest API to centralize application error logs.",
                "1.0",
                "Termos de serviço",
                null,
                "Licença",
                "URL da licença",
                new ArrayList<>()
        );

    }
}