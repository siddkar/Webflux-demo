package com.item.sale.analyzer.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Item - item entity class.
 * @author Siddharth Kar
 *
 */

@Document
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Item {
	@Id
	private String id;
	private String itemName;
	private String category;
}
