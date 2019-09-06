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
    	factory.createContext(ApplicationConfig.activeMQUserName(), ApplicationConfig.activeMQUserPassword());
    	connection = factory.createConnection();
        connection.start();
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
	
    public static void main(String[] args) throws JMSException {
    	Consumer consumer = new Consumer();
    	Destination destination = consumer.getSession().createTopic(ApplicationConfig.topicName);
    	MessageConsumer messageConsumer = consumer.getSession().createConsumer(destination);
    	messageConsumer.setMessageListener(new Listener());
    }
	
}
