package com.item.sale.analyzer.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import com.item.sale.analyzer.client.model.Item;
import com.item.sale.analyzer.client.model.ItemSaleEvent;

/**
 * IsaClientApplication - this class acts the client for calling reactive server
 * using WebClient.
 * 
 * @author Siddharth Kar
 *
 */

@SpringBootApplication
public class IsaClientApplication {

	/**
	 * entry point of the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(IsaClientApplication.class, args);
	}

	/**
	 * webClient - return a configured webClient.
	 * 
	 * @return a webClient configured with root uri path.
	 */
	@Bean
	public WebClient webClient() {
		return WebClient.create("http://localhost:8080");
	}

	/**
	 * runner - calls the server via a webClient upon app start-up.
	 * 
	 * @param client
	 * @return a callback which would be applied when the application is loading and
	 *         takes in the args passed to the main method.
	 */
	@Bean
	public CommandLineRunner runnerTraditional(WebClient client) {
		return args -> {
			client.get().uri("/items").exchange().flatMapMany(clientResponse -> clientResponse.bodyToFlux(Item.class))
					.filter(item -> item.getItemName().equalsIgnoreCase("Flux"))
					.subscribe(item -> client.get().uri("/items/{id}/events", item.getId()).exchange()
							.flatMapMany(clientResponse -> clientResponse.bodyToFlux(ItemSaleEvent.class))
							.subscribe(System.out::println));
		};
	}

	@Bean
	public CommandLineRunner runnerFunctionEP(WebClient client) {
		return args -> {
			client.get().uri("/reactive/items").exchange().flatMapMany(clientResponse -> clientResponse.bodyToFlux(Item.class))
					.filter(item -> item.getItemName().equalsIgnoreCase("Flux"))
					.subscribe(item -> client.get().uri("/reactive/items/{id}/events", item.getId()).exchange()
							.flatMapMany(clientResponse -> clientResponse.bodyToFlux(ItemSaleEvent.class))
							.subscribe(System.out::println));
		};
	}
}
