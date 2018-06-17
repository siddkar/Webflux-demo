package com.item.sale.analyzer.server;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.item.sale.analyzer.server.model.Item;
import com.item.sale.analyzer.server.model.ItemSaleEvent;
import com.item.sale.analyzer.server.repository.ItemRepository;
import com.item.sale.analyzer.server.service.ItemSaleService;
import com.item.sale.analyzer.server.util.CommonUtils;

/**
 * IsaServerApplication - entry point to the server application.
 * 
 * @author Siddharth Kar
 */

@SpringBootApplication(scanBasePackages = "com.item.sale.analyzer.server")
public class IsaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsaServerApplication.class, args);
	}

	/**
	 * writeItems - save some dummy data to mongodb on application startup.
	 * 
	 * @param itemRepository
	 * @return a callback which would be applied when the application is loading and
	 *         takes in the args passed to the main method.
	 */
	@Bean
	public CommandLineRunner writeItems(ItemRepository itemRepository) {
		return args -> {
			itemRepository.deleteAll().subscribe(null, null,
					() -> Stream.of("Flux", "Mono", "Spring", "Webflux", "Boot", "Server", "Client", "Event").map(
							itemName -> new Item(UUID.randomUUID().toString(), itemName, CommonUtils.randomCategory()))
							.forEach(item -> itemRepository.save(item).subscribe(System.out::println)));
		};
	}

	/**
	 * routes - this is the new approach of functional reactive end-points using
	 * Webflux.
	 * 
	 * @param ItemSaleService
	 *            service
	 * @return ServerSent event responses
	 */
	@Bean
	public RouterFunction<ServerResponse> routes(ItemSaleService service) {
		return RouterFunctions
				.route(RequestPredicates.GET("/reactive/items"),
						request -> ServerResponse.ok().body(service.all(), Item.class))
				.andRoute(RequestPredicates.GET("/reactive/items/{id}"),
						request -> ServerResponse.ok().body(service.byId(request.pathVariable("id")), Item.class))
				.andRoute(RequestPredicates.GET("/reactive/items/{id}/events"),
						request -> ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(
								service.byId(request.pathVariable("id")).flatMapMany(service::streamItemSales),
								ItemSaleEvent.class));
	}
}
