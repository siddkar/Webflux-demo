package com.item.sale.analyzer.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.item.sale.analyzer.server.model.Item;
import com.item.sale.analyzer.server.model.ItemSaleEvent;
import com.item.sale.analyzer.server.service.ItemSaleService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ItemSalesRestController - provides traditional way of handling reactive calls using webflux.
 * @author Siddharth Kar
 *
 */

@RestController
@RequestMapping("/items")
public class ItemSaleRestController {

	@Autowired
	private ItemSaleService itemSaleService;

	/**
	 * streamItemSale - streams the item sale events
	 * @param id
	 * @return ServerSent events
	 */
	@GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ItemSaleEvent> streamItemSales(@PathVariable String id) {
		return itemSaleService.byId(id).flatMapMany(itemSaleService::streamItemSales);
	}

	/**
	 * all - retieves all items from mongo
	 * @return all the items
	 */
	@GetMapping
	public Flux<Item> all() {
		return itemSaleService.all();
	}

	/**
	 * byId - retieves data of specific item whose id is passed
	 * @return the data of specific id
	 */
	@GetMapping("/{id}")
	public Mono<Item> byId(@PathVariable String id) {
		return itemSaleService.byId(id);
	}

}
