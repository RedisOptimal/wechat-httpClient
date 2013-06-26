package SendWechatMessage.beans;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

public class SingleMsgParams {
	
	private final String msgurl = "https://mp.weixin.qq.com/cgi-bin/singlesend?t=ajax-response&lang=zh_CN";
	
	//id that identifies users
	public String toFakeid;
	
	//post parameters that will send to wechat server
	public List<NameValuePair> sendpara;
	
	//message that will be send out
	public String Message;
	
	//whom to send
	public List<String> sendTo;
	
	public SingleMsgParams()
	{
		this.sendpara = new ArrayList<NameValuePair>();
		this.sendTo = new ArrayList<String>();
	}
	
	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public List<String> getSendTo() {
		return sendTo;
	}

	public void setSendTo(ArrayList<String> sendTo) {
		this.sendTo = sendTo;
	}



	public String getToFakeid() {
		return toFakeid;
	}

	public void setToFakeid(String toFakeid) {
		this.toFakeid = toFakeid;
	}

	public List<NameValuePair> getSendpara() {
		return sendpara;
	}

	public void setSendpara(List<NameValuePair> sendpara) {
		this.sendpara = sendpara;
	}

	public  String getMsgurl() {
		return msgurl;
	}
	
}
