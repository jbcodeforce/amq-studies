# AMQ Deployments

## Run Active MQ Artemis local using docker

Download tar file from [https://activemq.apache.org/components/artemis/download/](https://activemq.apache.org/components/artemis/download/). Unzip and then set the $ARTEMIS_HOME environment variable to the folder containing artemis. (e.g. < somewhere>/apache-artemis-2.10.0)

Clone the official docker files for ubuntu or centos from this github: [https://github.com/apache/activemq-artemis](https://github.com/apache/activemq-artemis).

The do the steps described in this repository README. I built the centos image:

```
docker build -f ./docker/Dockerfile-centos -t artemis-centos .
```
Start with the command:
```
docker run -it -p 61616:61616 -p 8161:8161 -v $ARTEMIS_HOME/instance:/var/lib/artemis-instance artemis-centos 


Or you can run the broker in the background using:

   "/var/lib/artemis-instance/bin/artemis-service" start

     _        _               _
    / \  ____| |_  ___ __  __(_) _____
   / _ \|  _ \ __|/ _ \  \/  | |/  __/
  / ___ \ | \/ |_/  __/ |\/| | |\___ \
 /_/   \_\|   \__\____|_|  |_|_|/___ /
 Apache ActiveMQ Artemis 2.10.0


2019-09-06 16:03:45,113 INFO  [org.apache.activemq.artemis.integration.bootstrap] AMQ101000: Starting ActiveMQ Artemis Server

```


Access the ActiveMQ admin-console as usual. Just invoke `http://localhost:8161/` artemis/artemis

The artemis management console looks like:

![Artemis Console](images/artemis-console.png)

## Running on Openshift

You may follow the instruction for the last AMQ release: at the time of writing 
Create an application using one of the oc template: openshift/amq-broker-71-basic

```
oc new-app amq-broker-71-basic -p AMQ_PROTOCOL=openwire,amqp,stomp,mqtt -p AMQ_USER=amquser -pAMQ_PASSWORD=amqpassword -p AMQ_QUEUES=example
```

which create brokers with the different protocols.

See the AMQP, reactive library [rhea project](https://github.com/amqp/rhea) with different JavaScripts examples.

[Red Hat Openshift Application Runtime(https://developers.redhat.com/products/rhoar/overview)