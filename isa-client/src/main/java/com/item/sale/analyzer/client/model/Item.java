package com.item.sale.analyzer.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Item - item model class.
 * @author Siddharth Kar
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Item {
	private String id;
	private String itemName;
	private String category;
}
