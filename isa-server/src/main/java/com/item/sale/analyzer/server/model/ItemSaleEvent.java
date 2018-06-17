package com.item.sale.analyzer.server.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ItemSaleEvent - item sale event pojo class.
 * @author Siddharth Kar
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ItemSaleEvent {
	private Item item;
	private Date date;
	private int quantity;
	private String vendor;
}
