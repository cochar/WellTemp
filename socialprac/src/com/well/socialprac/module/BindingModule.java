package com.well.socialprac.module;

import org.nutz.mvc.annotation.At;

import com.qq.weixin.mp.aes.SHA1;

@At("/binding")
public class BindingModule extends BaseModule {

	@At("/token")
	public String getToken(String signature,String timestamp,String nonce,String echostr) throws Exception{

		//
		// 第三方回复公众平台
		//

		// 需要加密的明文
		String encodingAesKey = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG";
		String token = "pamtest";
//		String timestamp = "1409304348";
//		String nonce = "xxxxxx";
		String appId = "wx0c6fc713a0adfcb2";
		String localSignature = "";
//		String replyMsg = " 中文<xml><ToUserName><![CDATA[oia2TjjewbmiOUlr6X-1crbLOvLw]]></ToUserName><FromUserName><![CDATA[gh_7f083739789a]]></FromUserName><CreateTime>1407743423</CreateTime><MsgType><![CDATA[video]]></MsgType><Video><MediaId><![CDATA[eYJ1MbwPRJtOvIEabaxHs7TX2D-HV71s79GUxqdUkjm6Gs2Ed1KF3ulAOA9H1xG0]]></MediaId><Title><![CDATA[testCallBackReplyVideo]]></Title><Description><![CDATA[testCallBackReplyVideo]]></Description></Video></xml>";
		
		localSignature = SHA1.getSHA1(token, timestamp, nonce, "");
		if(signature!=null&&signature.equals(localSignature))
			return echostr;
		return null;
	}
}
