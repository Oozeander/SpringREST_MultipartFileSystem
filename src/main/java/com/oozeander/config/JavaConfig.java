package com.oozeander.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@ComponentScan(basePackages = { "com.oozeander.service", "com.oozeander.resource" })
@Import({ RestConfig.class })
public class JavaConfig {
}

@Configuration
@EnableWebMvc
@PropertySource("classpath:resources.properties")
class RestConfig extends WebMvcConfigurationSupport {
	@Bean
	CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSize(20971520); // 20 Mo
		multipartResolver.setMaxInMemorySize(4194304); // 4 Mo
		multipartResolver.setMaxUploadSizePerFile(8388608); // 8 Mo
		return multipartResolver;
	}
}