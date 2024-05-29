package org.softwaregr5.dantulootravel.dantulootravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class DantulooTravelApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
		SpringApplication.run(DantulooTravelApplication.class, args);
		System.out.println("Dantuloo Travel Application Started");
	}

}
