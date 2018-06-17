package com.item.sale.analyzer.client.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ItemSaleEvent - item sale event model class.
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
