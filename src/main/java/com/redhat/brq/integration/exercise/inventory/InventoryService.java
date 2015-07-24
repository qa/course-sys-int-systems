package com.redhat.brq.integration.exercise.inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.brq.integration.exercise.model.OrderItem;

public class InventoryService {

	private static final Logger log = LoggerFactory.getLogger(InventoryService.class);
	
	private Map<Long, Integer> inventory = new HashMap<>();

	public InventoryService() {
		IntStream.range(1, 10).forEach(x -> {
			log.info("Adding to inventory article {} amount {}", x, x * 3);
			inventory.put((long) x, x * 3);
		});
	}

	public List<InventoryReply> reserveItems(List<OrderItem> items) {
		List<InventoryReply> ret = items.stream().map(item -> {
			int currentItems = inventory.getOrDefault(item.getArticleId(), 0);
			if (item.getCount() > currentItems) {
				return new InventoryReply(item.getArticleId(), 0, currentItems);
			} else {
				final int newAmount = currentItems - item.getCount();
				inventory.put(item.getArticleId(), newAmount);
				return new InventoryReply(item.getArticleId(), item.getCount(), newAmount);
			}
		}).collect(Collectors.toList());
		log.info("New state of inventory {}", inventory);
		return ret;
	}
}
