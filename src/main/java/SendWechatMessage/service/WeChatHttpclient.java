package SendWechatMessage.service;

public interface WeChatHttpclient {
	
	public boolean sendMessage();
	
	public boolean sendMessageToAll();
	
	public void setSendMessage(String msg);
	
	public void addReceiver(String ToFakeid);
}
