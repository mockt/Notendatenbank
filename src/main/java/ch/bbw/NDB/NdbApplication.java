package ch.bbw.NDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NdbApplication {
	private ConfigurableApplicationContext context;


	public static void main(String[] args) {
		SpringApplication.run(NdbApplication.class, args);
	}

	public void stop(){
		context.close();
	}
}
