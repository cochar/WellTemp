package com.well.wechat.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.weixin.bean.WxInMsg;
import org.nutz.weixin.bean.WxOutMsg;
import org.nutz.weixin.impl.BasicWxHandler;
import org.nutz.weixin.util.Wxs;

import com.well.utils.CacheUtil;

@IocBean(create="init", name="wxHandler")
public class DefaultWxHandler extends BasicWxHandler {

@Inject
protected PropertiesProxy conf; // 注入配置信息加载类

public static List<String> tokenListGlobal = new ArrayList<String>();

public void init() {
    // 将读取 weixin.token/weixin.aes/weixin.appid, 他们通常会写在weixin.properties或从数据库读取.
    configure(conf, "weixin.");

    // 如果你不知道conf是啥, 完全搞不清楚状况,
    // 请将protected PropertiesProxy conf注释掉,configure也注释掉
    // 把下面这行取消注释.
    // token = "1234567890";
}

public WxOutMsg text(WxInMsg msg) {
    if ("社会实践".equals(msg.getContent())) {
    	String token = tokenGen(8);
    	tokenListGlobal.add(token);
//    	cacheSave(token);
        return Wxs.respText("http://cancerce1l.ngrok.wendal.cn/socialprac/toLogin?token="+token);
    }
    else if ("2".equals(msg.getContent())) {
        return Wxs.respText("发2干啥玩意儿。");
    }
    return super.text(msg);
}

//33-128的任意字符
//private String tokenGen(){
//	StringBuilder builder = new StringBuilder(8);  
//    for (int i = 0; i < 8; i++) {  
//        builder.append((char) (ThreadLocalRandom.current().nextInt(33, 128)));  
//    }  
//	String token = builder.toString().toLowerCase();
//	return token;
//}

private void cacheSave(String token){
	@SuppressWarnings("unchecked")
	List<String> tokenList = (List<String>) CacheUtil.get("tokenList");
	if(tokenList==null)
		tokenList = new ArrayList<String>();
	tokenList.add(token);
	CacheUtil.put("tokenList", tokenList);
}

public String tokenGen(int length) {  
    
    String val = "";  
    Random random = new Random();  
      
    //参数length，表示生成几位随机数  
    for(int i = 0; i < length; i++) {  
          
        String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
        //输出字母还是数字  
        if( "char".equalsIgnoreCase(charOrNum) ) {  
            //输出是大写字母还是小写字母  
            int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
            val += (char)(random.nextInt(26) + temp);  
        } else if( "num".equalsIgnoreCase(charOrNum) ) {  
            val += String.valueOf(random.nextInt(10));  
        }  
    }  
    return val.toLowerCase();  
}  
}
