package com.item.sale.analyzer.server.service.impl;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.item.sale.analyzer.server.model.Item;
import com.item.sale.analyzer.server.model.ItemSaleEvent;
import com.item.sale.analyzer.server.repository.ItemRepository;
import com.item.sale.analyzer.server.service.ItemSaleService;
import com.item.sale.analyzer.server.util.CommonUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

/**
 * ItemSaleServiceImpl - implementation of item sale service
 * 
 * @author Siddharth Kar
 */

@Service("itemSaleService")
public class ItemSaleServiceImpl implements ItemSaleService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * streamItemSales - streams unlimited amount of data when a sale event occurs
	 * on an item.
	 */
	@Override
	public Flux<ItemSaleEvent> streamItemSales(Item item) {
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
		Flux<ItemSaleEvent> saleEvent = Flux.fromStream(Stream.generate(
				() -> new ItemSaleEvent(item, new Date(), CommonUtils.randomQuantity(), CommonUtils.randomVendor())));
		return Flux.zip(interval, saleEvent).map(Tuple2::getT2);
	}

	/**
	 * all - get all the items.
	 */
	@Override
	public Flux<Item> all() {
		return itemRepository.findAll();
	}

	/**
	 * byId - get an item by id.
	 */
	@Override
	public Mono<Item> byId(String id) {
		return itemRepository.findById(id);
	}

}
