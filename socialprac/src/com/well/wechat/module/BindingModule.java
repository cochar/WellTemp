package com.well.wechat.module;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.weixin.spi.WxHandler;
import org.nutz.weixin.util.Wxs;

import com.qq.weixin.mp.aes.SHA1;
import com.well.BaseModule;

@At("/binding")
@IocBean
public class BindingModule extends BaseModule {

//	@At("/token")
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
	
	/*
    wxHandler是被动请求的主要处理类, 里面写的1234567890就是"接口配置信息"里面提到的"token",
    */
	@Inject
    protected WxHandler wxHandler;

    @At // 拼起来的全路径就是 /weixin/msgin
    @Filters()
    public View msgin(HttpServletRequest req) throws IOException {
        return Wxs.handle(wxHandler, req, "default"); // 最后面的default,可以不写,只是个标识符.
    }
}
