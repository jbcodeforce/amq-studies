# AMQ Studies

[Apache Active MQ](https://activemq.apache.org/) starts as the first open source JMS specification implementation but ahs evolved to a event driven architecture backbone, with peristence, massive scalability and high availability. It has now in two flavors: classic and artemis.

## Characteristics

* Heterogeneous application integration
    * Java,  C/C++, .NET, Perl, PHP, Python, Ruby
* JMS compliant from design
* pub / sub and point-to-point communication style. With Topic, subscription may be *durable* which means they retain a copy of each message sent to the topic until the subscriber consumes them 
* Guarantee the mesage will be delivered once and only once to each consumer of a queue or a durable topic.
* Support the sending and acknowledgement of multiple messages in a single local transaction
* Support XA transaction in Java using JTA
* Message can be persisted in permanent storage, and will survice server crashes and restart.
* Support HTTP/S via REST call, STOMP, MQTT, AMQT
* Provide clustering model where messages can be intelligently load balanced between the servers in the cluster, according to the number of consumers on each node, and whether they are ready for messages

## When to use it

* For point to point communication between Java microservices
* To replace RPC style synchronous calls
    * Systems that rely upon synchronous requests typically have a limited ability to scale because eventually requests will begin to back up, thereby slowing the whole system.
* To adopt more loosely coupling between applications
    * Loosely coupled architectures, on the other hand, exhibit fewer dependencies, making them better at handling unforeseen changes. Not only will a change to one component in the system not ripple across the entire system, but component interaction is also dramatically simplified.
* To implement stateful operation for complex event processing

## High Availability


## JMS Summary

The goal of JMS is to provide a vendor neutral API for messaging in Java. It is an abstraction layer between Java class and Message Oriented Middleware. 

* JMS client: Java application to send and receive messages.
* JMS producer: A client application that creates and sends JMS messages.
* JMS consumer: A client application that receives and processes JMS messages.
* JMS provider: The implementation of the JMS interfaces to integrate with a specific MOM.
* JMS message: carry data and meta data between clients
* JMS domains: styles of messaging: point-to-point and publish/subscribe.
* Administered objects: Preconfigured JMS objects, accessible via JNDI, contain provider configuration to be used by clients.
* Connection factory: Clients use a connection factory to create connections to the JMS provider.
* Destination: An object to which messages are addressed and sent and from which messages are received.

See producer and consumer code under the `examples/basicjms` folder and the [explanation note](basicjms.md).