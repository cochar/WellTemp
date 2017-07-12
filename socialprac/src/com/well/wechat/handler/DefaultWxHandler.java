package com.well.wechat.handler;

import java.util.List;
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
    	StringBuilder builder = new StringBuilder(8);  
        for (int i = 0; i < 8; i++) {  
            builder.append((char) (ThreadLocalRandom.current().nextInt(33, 128)));  
        }  
    	String token = builder.toString();
    	@SuppressWarnings("unchecked")
		List<String> tokenList = (List<String>) CacheUtil.get("tokenList");
    	tokenList.add(token);
    	CacheUtil.put("tokenList", tokenList);
        return Wxs.respText("http://summer.ustb.edu.cn/login?token="+token);
    }
    else if ("2".equals(msg.getContent())) {
        return Wxs.respText("发2干啥玩意儿。");
    }
    return super.text(msg);
}
}
