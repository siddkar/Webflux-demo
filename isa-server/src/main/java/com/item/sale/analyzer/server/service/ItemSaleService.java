package com.item.sale.analyzer.server.service;

import com.item.sale.analyzer.server.model.Item;
import com.item.sale.analyzer.server.model.ItemSaleEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ItemSaleService - defines the contract
 * 
 * @author Siddharth Kar
 */

public interface ItemSaleService {

	public Flux<ItemSaleEvent> streamItemSales(Item item);

	public Flux<Item> all();

	public Mono<Item> byId(String id);

}
