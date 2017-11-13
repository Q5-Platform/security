package com.codingapi.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**  swagger-ui.html
 *
 * Created by houcunlu on 2017/7/28.
 */
@Configuration
@EnableSwagger2
// /swagger-ui.html
public class Swagger2 {

    @Bean
    public Docket config() {
        return new Docket(DocumentationType.SWAGGER_2)
                //.paths(PathSelectors.ant("/project/*"))
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lorne.security.controller"))
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("权限系统文档")
                .contact(new Contact("侯存路", "www.baidu.com", "544845389.qq.com"))
                .build();
    }





}
