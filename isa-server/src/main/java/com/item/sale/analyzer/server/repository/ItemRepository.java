package com.item.sale.analyzer.server.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.item.sale.analyzer.server.model.Item;

/**
 * ItemRespository - implements reactive mongo repository.
 * @author Siddharth Kar
 *
 */

@Repository
public interface ItemRepository extends ReactiveMongoRepository<Item, String>  {

}
