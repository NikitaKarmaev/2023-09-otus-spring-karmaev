package ru.otus.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.course.config.AppConfig;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class ApplicationHW {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationHW.class, args);
	}
}