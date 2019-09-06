package ibm.gse.eda.basicjms;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import ibm.gse.eda.basicjms.model.ShippingContainer;

public class Listener implements MessageListener {

	public void onMessage(Message message) {
		try {
			MapMessage map = (MapMessage)message;
			ShippingContainer sc = new ShippingContainer(
					map.getString("containerID"),
					map.getString("type"),
					map.getString("status"),
					map.getString("brand"),
					map.getInt("capacity"));
			System.out.println(sc.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
