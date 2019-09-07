package ibm.gse.eda.basicjms;

import java.util.Random;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMapMessage;

import ibm.gse.eda.basicjms.model.ShippingContainer;

/**
 * Active MQ publisher
 * Send message about new shipping container. 
 * The program arguments are the container id
 * 
 * @author jerome boyer
 *
 */
public class Publisher {
	
    protected static transient ConnectionFactory factory;
    protected transient Connection connection;
    protected transient Session session;
    protected transient MessageProducer producer;
    protected Random random = new Random();
    
    public Publisher() throws JMSException {
    	factory = new ActiveMQConnectionFactory(ApplicationConfig.brokerUrl());
    	connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        producer = session.createProducer(null);
        
    }
    
    public void close() throws JMSException {
        if (connection != null) {
            connection.close();
        }
    }
    
    public static void main(String[] args) throws JMSException {
    	Publisher publisher = new Publisher();
    	for (String cid : args) {
    			publisher.sendMessage(cid);
    			System.out.println("Published " + cid);  
        }
        publisher.close();
    }

    protected void sendMessage(String containerID) throws JMSException {
        
        Destination destination = session.createTopic(ApplicationConfig.topicName);
        Message message = createShippingContainerMessage(containerID, session);
        System.out.println("Sending: " + ((ActiveMQMapMessage)message).getContentMap() + " on destination: " + destination);
        producer.send(destination, message);
    }

    protected Message createShippingContainerMessage(String containerID, Session session) throws JMSException {
		MapMessage message = session.createMapMessage();
		message.setString("containerID",containerID);
		String[] supportedContainerTypes = ShippingContainer.getSupportedContainerTypes();
		int idx = random.nextInt(supportedContainerTypes.length + 1);
		message.setString("type", supportedContainerTypes[idx]);
		message.setString("status", "New");
		message.setString("brand", "ContainerBrand");
		message.setInt("capacity", 40);
		return message;
    }

}
