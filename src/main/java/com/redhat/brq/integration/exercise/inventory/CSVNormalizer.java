package com.redhat.brq.integration.exercise.inventory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.redhat.brq.integration.exercise.model.OrderItem;

public class CSVNormalizer implements Processor {

	@Override
	public void process(Exchange ex) throws Exception {
		@SuppressWarnings("unchecked")
		final List<Map<String, OrderItem>> input = (List<Map<String, OrderItem>>)ex.getIn().getBody();
		ex.getIn().setBody(input.stream().map(x -> x.get(OrderItem.class.getName())).collect(Collectors.toList()));
	}

}
