package com.well;

import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.filter.CheckSession;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@IocBy(type=ComboIocProvider.class, args={"*js", "ioc/",
    // 这个package下所有带@IocBean注解的类,都会登记上
                        "*anno", "com.well",
                        "*weixin", // 仅需要添加这一行,引用的是org.nutz.plugins.weixin.WeixinIocLoader
                        "*tx", // 事务拦截 aop
                        "*async"}) // 异步执行aop
@Modules(scanPackage=true)
@Ok("json")
@Fail("jsp:jsp.500")
@SetupBy(MainSetup.class)
@Filters(@By(type=CheckSession.class, args={"user", "/QRCode.jsp"}))
public class MainModule {

}