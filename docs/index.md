# AMQ Studies

[Apache Active MQ](https://activemq.apache.org/) started in 2004 as the first open source JMS specification implementation but has evolved to a event driven architecture backbone, with peristence, massive scalability and high availability. It is a big product with a lot of features.
It is now in two flavors: classic and artemis. Artemis is the new architecture.

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

## Architecture

Here is a diagram of Artemis architecture:

![Artemis architecture](images/artemis-arch.png)

* Each broker server is a Java POJO.
* Use persistent journal to persist messages on disk. It is possible to plug a JDBC and remote database. (This experimental)
* Client applications can interact with any protocol which are mapped to Core protocol. JMS API has a provider that uses the core client library.
* Broker always just deals with core API interactions.
* The normal stand-alone messaging broker configuration comprises a core messaging broker and a number of protocol managers that provide support for the various protoco

### Clustering

The goal is to group servers so they can share the message processing. Each server within the cluster manages its own messages and handles its own connections. Cluster is formed by each node declaring cluster connections to other nodes in the `broker.xml` configuration file. 
Cluster connections allow messages to flow between the nodes of the cluster to balance load.

![]()

A single node can belong to multiple clusters simultaneously. 

There are a set of interesting features to consider:

* Server discovery: each broker shares their connection configuration with client or other server so they can discover them, without explicit knowledge of their static end point. To establish the first connection, clients or servers use dynamic discovery using UDP multicast, or the JGroups.
* To support discovery, each server define a **broadcast group**, which is the means by which a server broadcasts connectors information over the network. **Connector** defines how the client or server can connect to this broker.

```

```

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