package com.well.wechat.handler;

import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.weixin.bean.WxInMsg;
import org.nutz.weixin.bean.WxOutMsg;
import org.nutz.weixin.impl.BasicWxHandler;
import org.nutz.weixin.util.Wxs;

@IocBean(create="init", name="wxHandler")
public class DefaultWxHandler extends BasicWxHandler {

@Inject
protected PropertiesProxy conf; // 注入配置信息加载类

public void init() {
    // 将读取 weixin.token/weixin.aes/weixin.appid, 他们通常会写在weixin.properties或从数据库读取.
    configure(conf, "wechat.");

    // 如果你不知道conf是啥, 完全搞不清楚状况,
    // 请将protected PropertiesProxy conf注释掉,configure也注释掉
    // 把下面这行取消注释.
    // token = "1234567890";
}

public WxOutMsg text(WxInMsg msg) {
    if ("1".equals(msg.getContent())) {
        return Wxs.respText("广告法说不能自称第一");
    }
    else if ("2".equals(msg.getContent())) {
        return Wxs.respText("就是这么2");
    }
    return super.text(msg);
}
}
