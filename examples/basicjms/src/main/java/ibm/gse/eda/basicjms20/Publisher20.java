package ibm.gse.eda.basicjms20;

import java.util.Random;

import javax.jms.CompletionListener;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.JMSRuntimeException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.google.gson.Gson;

import ibm.gse.eda.basicjms.ApplicationConfig;
import ibm.gse.eda.basicjms.model.ShippingContainer;

public class Publisher20 {

	
	protected static transient ConnectionFactory factory;
    protected transient Connection connection;
    protected transient Session session;
    protected transient MessageProducer producer;
    protected transient Destination destination;
    protected Random random = new Random();
    protected Gson parser = new Gson();
    
    public Publisher20() throws JMSException {
    	factory = new ActiveMQConnectionFactory(ApplicationConfig.brokerUrl());
    	connection = factory.createConnection();
        connection.start();
        // JMS2.0 session mode is the unique parameter for JSE deployment
        session = connection.createSession(Session.AUTO_ACKNOWLEDGE);	
        destination = session.createTopic(ApplicationConfig.topicName);
	     
    }
	    
    public void sendMessageJMS20(String containerID) {
		   String message = createShippingContainerMessage(containerID);      
		   try (JMSContext context = factory.createContext()) {
			   JMSProducer p = context.createProducer();
			   p.setAsync(new CompletionListener() {

				@Override
				public void onCompletion(Message message) {
					// TODO process the acknowledgement from broker
					System.out.println(message.toString());
				}

				@Override
				public void onException(Message message, Exception exception) {
					// TODO implements retries and circuit breaker
					exception.printStackTrace();
				}
				   
			   });
			   p.send(destination, message);
			   // do other work here 
		   } catch (JMSRuntimeException ex) {
			   ex.printStackTrace();
		   }
	      
	}
	    
	/**
	 * Publish to jms topic the Shipping Container     
	 * @param args: the container ids to send
	 * @throws JMSException
	 */
    public static void main(String[] args) throws JMSException {
    	Publisher20 publisher = new Publisher20();
    	for (String cid : args) {
			publisher.sendMessageJMS20(cid);
			System.out.println("Published " + cid);  
        }
    }
	     
	    

	    protected String createShippingContainerMessage(String containerID) {
	    	String[] supportedContainerTypes = ShippingContainer.getSupportedContainerTypes();
			int idx = random.nextInt(supportedContainerTypes.length + 1);
			
	    	ShippingContainer sc = new ShippingContainer(
					containerID,
					supportedContainerTypes[idx],
	    			"New",
	    			"ContainerBrand",
	    			40);
			return parser.toJson(sc);
	    }
}
