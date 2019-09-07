package ibm.gse.eda.basicjms;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;


/**
 * Basic ActiveMQ consumer of text message
 * @author jerome boyer
 *
 */
public class Consumer {

    private transient Connection connection;
    private transient Session session;
    private transient ActiveMQConnectionFactory factory;
    
    public Consumer() throws JMSException {
    	/* factory = new ActiveMQConnectionFactory(
    			ApplicationConfig.activeMQUserName(),
    			ApplicationConfig.activeMQUserPassword(),
    			ApplicationConfig.brokerUrl());
    	 */
    	factory = new ActiveMQConnectionFactory(
     			ApplicationConfig.brokerUrl());
    	// factory.createContext(ApplicationConfig.activeMQUserName(), ApplicationConfig.activeMQUserPassword());
    	connection = factory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }
    
    public void close() throws JMSException {
        if (connection != null) {
            connection.close();
        }
    }    
    
	public Session getSession() {
		return session;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
    public static void main(String[] args) {
    	System.out.println("Start consumer... v0.0.1");
    	Consumer consumer = null;
		try {
			consumer = new Consumer();
		
			Destination destination = consumer.getSession().createTopic(ApplicationConfig.topicName);
			MessageConsumer messageConsumer = consumer.getSession().createConsumer(destination);
			messageConsumer.setMessageListener(new Listener());
			consumer.getConnection().start();
			try{  
			   while(!Thread.currentThread().isInterrupted()){  
			       Thread.sleep(2000);    
			   }  
			}
			catch(InterruptedException e){  

			}
		} catch (JMSException e) {
			e.printStackTrace();
			if (consumer != null) {
				try {
					consumer.close();
				} catch (JMSException e1) {
					e1.printStackTrace();
				}
			}
		}
    
    }
	
}
