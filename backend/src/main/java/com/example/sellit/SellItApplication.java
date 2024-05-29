package com.example.sellit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:application.properties", "classpath:application-secret.properties"})
public class SellItApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellItApplication.class, args);
	}

}
