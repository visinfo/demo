package com.upday.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    public static final String ENDPOINTS[] = { 
            "/v2/api-docs", 
            "/swagger-resources/**", 
            "/swagger-ui.html**",
            "/webjars/**", 
            "favicon.ico" 
    };

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.ant("/news/**"))
                    .build()
                    .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                     .title("News Service ")
                     .description("New Service by Upday")
                     .version("v1.0")
                     .license("Demo")
                     .contact(new Contact("Demo", "", "neerajawr89@gmail.com"))
                   .build();
    }


}
