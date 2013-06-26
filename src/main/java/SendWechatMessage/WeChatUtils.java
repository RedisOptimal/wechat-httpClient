package SendWechatMessage;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;

public class WeChatUtils {
	
	private static final String USER_AGENT = "Mozilla/5.0";
	
	public static String getToken(String s) {
        try {
            if (StringUtils.isBlank(s))
                return null;
            String[] ss = StringUtils.split(s, "?");
            String[] params = null;
            if (ss.length == 2) {
                if (!StringUtils.isBlank(ss[1]))
                    params = StringUtils.split(ss[1], "&");
            } else if (ss.length == 1) {
                if (!StringUtils.isBlank(ss[0]) && ss[0].indexOf("&") != -1)
                    params = StringUtils.split(ss[0], "&");
            } else {
                return null;
            }
            for (String param : params) {
                if (StringUtils.isBlank(param))
                    continue;
                String[] p = StringUtils.split(param, "=");
                if (null != p && p.length == 2
                        && StringUtils.equalsIgnoreCase(p[0], "token"))
                    return p[1];
            }
        } catch (Exception e) {
            String info =  e.getMessage() ;
            System.err.println(info);
            return null;
        }
        return null;
    }
	
	public static void setLoginHeaders(HttpPost post)
	{
		// add header
		post.setHeader("Host", "mp.weixin.qq.com");
		post.setHeader("User-Agent", USER_AGENT);
		post.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		post.setHeader("Accept-Language", "en-US,en;q=0.5");
		post.setHeader("Accept-Encoding", "gzip, deflate");
		//post.setHeader("Cookie", getCookies());
		post.setHeader("Connection", "keep-alive");
		post.setHeader("Referer", "https://accounts.google.com/ServiceLoginAuth");
		post.setHeader("X-Requested-With", "XMLHttpRequest");
		post.setHeader("Referer", "https://mp.weixin.qq.com/cgi-bin/loginpage?t=wxm2-login&lang=zh_CN");
		post.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	}
	
	public static void setLoginPostParams(List<NameValuePair>loginPostParams)
	{
		loginPostParams.add(new BasicNameValuePair("username", "9239339@qq.com"));
		loginPostParams.add(new BasicNameValuePair("pwd", "8b79f88b2e5a34d6adef4a171c34ac27"));
		loginPostParams.add(new BasicNameValuePair("imgcode", ""));
		loginPostParams.add(new BasicNameValuePair("f", "json"));
	}
	
	public static void setSingleMsgHeaders(HttpPost sendReq, String cookie, String token)
	{
		sendReq.setHeader("Accept", "*/*");
		sendReq.setHeader("Accept-Encoding", "gzip,deflate,sdch");
		sendReq.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
		sendReq.setHeader("Connection", "keep-alive");
		sendReq.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	    sendReq.setHeader("Cookie", cookie);
	    sendReq.setHeader("Host", "mp.weixin.qq.com");
	    sendReq.setHeader("Origin", "https://mp.weixin.qq.com");
	    sendReq.setHeader("Referer", "https://mp.weixin.qq.com/cgi-bin/singlemsgpage?token=" + token + "&fromfakeid=604540040&msgid=&source=&count=20&t=wxm-singlechat&lang=zh_CN");
		sendReq.setHeader("User-Agent", USER_AGENT);
		sendReq.setHeader("X-Requested-With", "XMLHttpRequest");
		sendReq.setHeader("Pragma", "no-cache");
		sendReq.setHeader("Cache-Control", "no-cache");
	}
	
	public static void setSingleMsgPostParams(List<NameValuePair> sendpara, String tofakeid, String token, String msg)
	{
		sendpara.add(new BasicNameValuePair("type", "1"));
		sendpara.add(new BasicNameValuePair("content", msg));
		sendpara.add(new BasicNameValuePair("error", "false"));
		sendpara.add(new BasicNameValuePair("tofakeid", tofakeid));
		sendpara.add(new BasicNameValuePair("token", token));
		sendpara.add(new BasicNameValuePair("ajax", "1"));
	}
}
