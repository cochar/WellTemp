package com.well.socialprac;

import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@IocBy(type=ComboIocProvider.class, args={"*js", "ioc/",
    // 这个package下所有带@IocBean注解的类,都会登记上
                        "*anno", "net.wendal.nutzbook",
                        "*tx", // 事务拦截 aop
                        "*async"}) // 异步执行aop
@Modules(scanPackage=true)
@Fail("jsp:jsp.500")
@SetupBy(MainSetup.class)
public class MainModule {

}
