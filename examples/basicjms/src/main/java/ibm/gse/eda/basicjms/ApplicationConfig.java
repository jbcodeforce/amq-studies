package ibm.gse.eda.basicjms;

public class ApplicationConfig {

	public static String topicName = System.getenv().getOrDefault("TOPIC_NAME", "containers");
	public static String brokerurl = "tcp://" + System.getenv().getOrDefault("ACTIVEMQ_URL", "localhost:61616");
	
	public static String activeMQUserName() {
		return System.getenv().getOrDefault("ACTIVEMQ_USER", "admin");
	}
	
	public static String activeMQUserPassword() {
		return System.getenv().getOrDefault("ACTIVEMQ_USER_PWD", "admin");
	}
	
	public static String brokerUrl() {
		return brokerurl;
	}
}
