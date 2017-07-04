package com.well.socialprac.module;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class BaseModule {
	/** 注入同名的一个ioc对象 */
    @Inject
    protected Dao dao;

}
