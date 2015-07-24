# course-sys-int-systems

This project is meant as a support for the A4M36ISS course. It servers as a simulation of three enterprise systems that will be integrated during course exercises.

## How to run
``mvn clean camel:run``

Supported system properties

| Name           | Default | Description |
| -------------- | ------- | ----------- |
| broker.port    | 61617   | Port on which external JBoss A-MQ is located to access Shipment system |

## Inventory
### Input
* CSV file placed in `target/inbox/inventory`.
* File structure `articleId;amount`

### Output
* CSV file placed in `target/outbox/inventory`.
* File structure `articleId;amountReserved;amountLeft`

### Behaviour
At the startup the inventory is initiated with articles with ids `1..10` and amount that is the triple of id so 15 pices for article with id 5.

If there is enough pieces of a given article than the appropriate amount is reserved and the number of pices left is reduced and reported back. If there is not enough pieces the nothing is reserved and the current amount in inventory is reported.

## Accounting
### Input
* Endpoint exposed at `https://localhost:7070/accounting`, using `POST` method
* Certificate located at `src/main/resources/keystore.jks`
* Client side SSL authentication is required
* Credentials `admin/foo`
* Example of JSON message
```
{
	"id": 1,
	address: {
		"firstName": "Jiri",
		"lastName": "Novak",
		"street": "Purkynova",
		"city": "Brno",
		"zipCode": "602 00"
	},
	"items": [
		{
			"articleId": 10,
			"count": 30,
			"unitPrice": 3
		}
	]
}
```

### Output
* Example of JSON message
```
{
   "invoiceId": 1000001,
   "order":    {
      "id": 1,
      "items": [      {
         "articleId": 10,
         "count": 30,
         "unitPrice": 3
      }],
      "address":       {
         "firstName": "Jiri",
         "lastName": "Novak",
         "street": "Purkynova",
         "city": "Brno",
         "zipCode": "602 00"
      }
   },
   "status": "ISSUED"
}
```

### Behaviour
Accounting either issues invoices (status `ISSUED`) and assigns invoice id as order id incremented by one million.
If the total order price is either zero or less or over 1000 then it rejects invoce (status `INVALID`) and sets invoice id to `-1`.

## Shipment
### Input
* JBoss A-MQ broker running at localhost:${broker.port} using openwire+ssl protocol
* Certificate located at `src/main/resources/keystore.jks`
* Credentials `shipuser/shippwd`
* Queue `SHPMNT.REQ`
* order id is stored in `orderID` JMS header
* Payload is XML sent as a JMS text message, an example
```
<address>
	<firstName>Jiri</firstName>
	<lastName>Novak</lastName>
	<street>Purkynova</street>
	<city>Brno</city>
	<zipCode>602 00</zipCode>
</address>
```

### Output
* Queue `SHPMNT.RESP`
* order id is stored in `orderID` JMS header
* Payload is XML sent as a JMS text message, an example
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<delivery status="OK"/>
```

### Behaviour
If city is set to `Gotham` then status returned is `FAIL` otherwise it is `OK`.

