package io.swagger.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-02T17:01:50.367Z")

@Configuration
public class SwaggerDocumentationConfig {

	ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("no link API")
				.description("Move your app forward with the nolink API")
				.license("")
				.licenseUrl("http://www.nolink.cc")
				.termsOfServiceUrl("")
				.version("1.0.0")
				//.contact(new Contact("a", "b", "c"))
				.build();
	}

	@Bean
	public Docket customImplementation() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("io.swagger.api"))
				.paths(PathSelectors.any())
				.build()
				//.directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
				//.directModelSubstitute(org.joda.time.DateTime.class, java.util.Date.class)
				;
	}
}
