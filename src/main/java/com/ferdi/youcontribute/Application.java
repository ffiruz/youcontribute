package com.ferdi.youcontribute;

import com.ferdi.youcontribute.service.GithubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling //Spring ayağa kalkarken ,Schedule işlemi yapılcağını bildiriyoruz.Spring bunu bilecek.
// Normalde spring "main" threadi üzerinden ayağa kalkıyor.Artık Async olarak ayağa kalkabilecek.
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
