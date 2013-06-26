package SendWechatMessage.beans;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;

public class LoginParams {
	
	private final String url = "http://mp.weixin.qq.com/cgi-bin/login?lang=zh_CN";

	private String msgToSend;
	//token will be return from wechat server when login success
	protected String token;

	//parameters that will be send to the wechat server
	public List<NameValuePair> paramList;
	
	//cookies that will be returned from wechat server if username and pwd is correct
	public List<NameValuePair> cookies;
	
	//getter and setters
	public LoginParams()
	{
		this.paramList = new ArrayList<NameValuePair>();
		this.cookies = new ArrayList<NameValuePair>();
	}

	public String getMsgToSend() {
		return msgToSend;
	}

	public void setMsgToSend(String msgToSend) {
		this.msgToSend = msgToSend;
	}

	public List<NameValuePair> getParamList() {
		return paramList;
	}

	public void setParamList(List<NameValuePair> paramList) {
		this.paramList = paramList;
	}

	public List<NameValuePair> getCookies() {
		return cookies;
	}

	public void setCookies(List<NameValuePair> cookies) {
		this.cookies = cookies;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
