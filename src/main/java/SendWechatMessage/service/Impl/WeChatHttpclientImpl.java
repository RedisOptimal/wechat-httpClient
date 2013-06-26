package SendWechatMessage.service.Impl;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import org.apache.http.util.EntityUtils;

import SendWechatMessage.WeChatUtils;
import SendWechatMessage.beans.LoginParams;
import SendWechatMessage.beans.LoginRetJson;
import SendWechatMessage.beans.SingleMsgParams;

import com.alibaba.fastjson.JSON;

import SendWechatMessage.service.WeChatHttpclient;
public class WeChatHttpclientImpl implements WeChatHttpclient{

	  private HttpClient client = new DefaultHttpClient();
	  
	  SingleMsgParams singleMsgParams = new SingleMsgParams();
	  LoginParams loginParams = new LoginParams();
	  
//	  public String Message="";
	  
	  public void setSendMessage(String msg)
	  {
		  this.singleMsgParams.setMessage(msg);
	  }
	  
	  public void addReceiver(String ToFakeid)
	  {
		  singleMsgParams.sendTo.add(ToFakeid);
	  }
	  
	  public boolean sendMessage() {
		  
//		WeChatHttpclient http = new WeChatHttpclient();
	    
		int status = -1;
		
		try {
			if( !this.Login(loginParams) )
			{
				//login fail
				System.out.println("Login fail!");
				return false;
			}
		} catch (Exception e)
		{
			System.out.println(e);
		}
		
//		singleMsgParams.setToFakeid("604540040");
		for(String i : singleMsgParams.sendTo) {
			singleMsgParams.setToFakeid(i);
			try {
				status = this.SendSingleMsg(loginParams, singleMsgParams, singleMsgParams.Message);
			} catch (Exception e)
			{
				System.out.println(e);
			}
			if(status!= 200)
			{
				//message send fail
				System.out.println("single message send fail!");
				return false;
			}
		}
	 
		System.out.println("Done");
		return true;
	  }
	  
		public boolean sendMessageToAll() {
			// TODO
			return false;
		}

	  private boolean Login(LoginParams params) throws Exception {
	 
		HttpPost post = new HttpPost(params.getUrl());
	 
		// add header
		WeChatUtils.setLoginHeaders(post);
		
		//set post params
		WeChatUtils.setLoginPostParams(params.paramList);
		
		post.setEntity(new UrlEncodedFormEntity(params.paramList));
		
		HttpResponse response = client.execute(post);
		String ret = EntityUtils.toString(response.getEntity(), "UTF-8");
		LoginRetJson retcode = JSON.parseObject(ret, LoginRetJson.class);
		params.setToken(WeChatUtils.getToken(retcode.getErrMsg()));
		System.out.println("token: " + params.getToken());
	 
		int responseCode = response.getStatusLine().getStatusCode();
		if (responseCode == 200)
		{
			Header[] resHeader = response.getHeaders("Set-Cookie");
			for(Header h :  resHeader)
			{
				HeaderElement[] hEs = h.getElements();
				for (HeaderElement hE : hEs)
				{
					 if(hE.getName().toString().equals("cert"))
						 params.cookies.add(new BasicNameValuePair("cert", hE.getValue()));
					 
					 if(hE.getName().toString().equals("slave_user"))
						 params.cookies.add(new BasicNameValuePair("slave_user", hE.getValue()));
					 
					 if(hE.getName().toString().equals("slave_sid"))
						 params.cookies.add(new BasicNameValuePair("slave_sid", hE.getValue()));
				}
			}
			for(NameValuePair n  :  params.cookies)
				System.out.println(n.getName() + ":" + n.getValue());
			return true;
		}
		return false;
	  }
	 
	  private int SendSingleMsg(LoginParams loginParams, SingleMsgParams singleMsgParams, String msgToSend) 
			  throws Exception {
	 
		  HttpPost request = new HttpPost(singleMsgParams.getMsgurl());
		
	    String cookie = "hasNotifyList=1; hasWarningUser=1; ";
	    for(NameValuePair c : loginParams.cookies)
	    	cookie = cookie + c.getName() + "=" +c.getValue() + "; ";

//	    String postdata = "createtime=13721399310&fromfakeid=604540040&opcode=1&token=" + loginParams.getToken() +"&ajax=1";
	    
//     Set message sending parameters
	    WeChatUtils.setSingleMsgHeaders(request, cookie, loginParams.getToken());
	    
	    WeChatUtils.setSingleMsgPostParams(singleMsgParams.sendpara, singleMsgParams.getToFakeid(), loginParams.getToken(), msgToSend);
		
		request.setEntity(new UrlEncodedFormEntity(singleMsgParams.sendpara));
		
		System.out.println("cookie:   " +  cookie);
		
		HttpResponse response = client.execute(request);
		int responseCode = response.getStatusLine().getStatusCode();
	 
		System.out.println("\nSending 'POST' request to URL : " + singleMsgParams.getMsgurl());
		
		request.releaseConnection();

		return responseCode;
	  }
	
}
