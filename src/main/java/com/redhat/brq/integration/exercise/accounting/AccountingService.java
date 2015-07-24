package com.redhat.brq.integration.exercise.accounting;

import com.redhat.brq.integration.exercise.model.Order;

public class AccountingService {
	
	public InvoiceIssueReply issueInvoice(Order order) {
		if (order.getTotalPrice() > 0 && order.getTotalPrice() < 1000) {
			return new InvoiceIssueReply(1_000_000 + order.getId(), order, "ISSUED");
		}
		else {
			return new InvoiceIssueReply(-1, order, "INVALID");
		}
	}
	
}
