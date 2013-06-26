package SendWechatMessage;

import org.junit.Test;

import SendWechatMessage.service.WeChatHttpclient;
import SendWechatMessage.service.Impl.WeChatHttpclientImpl;

public class WeChatTest {
	
	@Test
	public void testSendMsg() throws Exception {
		WeChatHttpclient client = new WeChatHttpclientImpl();
		client.setSendMessage("Test message from JUnit");
		client.addReceiver("604540040");
		client.sendMessage();
	}
	
}
