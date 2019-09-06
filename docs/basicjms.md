# Basic JMS examples

Some example of the JMS consumer and producer API and implementation considerations.

## Pre-requisites

Be sure to have access to Active MQ artemis broker, in docker or on a cluster.

## The JMS APIs

The basic api can be summarized in the following diagram from `codenotfound.com`

![JMS API programming model](images/jms-api-programming-model.png)

* ConnectionFactory is used to create JMS connection to the provider. It needs the broker URL, username and password.
* Connection is the encapsulation to the TCP/IP socket between the client and the provider. Always close connection to avoid congestion on the Provider side.
* A session is a single-threaded context for producing and consuming messages. A session provides a transactional context with which to group a set of sends and receives into an atomic unit of work.
* A MessageProducer is an object that is created by a session and used for sending messages to a destination
* A MessageConsumer is an object that is created by a session and used for receiving messages
* Destination is a queue or a topic

## Producing text message

