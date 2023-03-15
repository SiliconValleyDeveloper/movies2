package org.neo4j.movies2;

import org.neo4j.driver.Driver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Movies2Application {

	public static void main(String[] args) {
		SpringApplication.run(Movies2Application.class, args);
	}
	@Bean
	public CommandLineRunner run(MovieService service){
		return args ->{
			System.out.printf("Hello Graph World (%d nodes)%n",service.nodeCount("Song") );
		};
	}





}
