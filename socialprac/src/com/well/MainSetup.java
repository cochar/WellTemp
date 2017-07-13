package com.well;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import com.well.utils.CacheUtil;

public class MainSetup implements Setup {
	
	public static Ioc ioc;

    public void init(NutConfig nc) {
    	
    	MainSetup.ioc = nc.getIoc();
//    	 CacheManager cacheManager= ioc.get(CacheManager.class);
//    	 Cache cache = cacheManager.getCache("syscache");
    }

    public void destroy(NutConfig nc) {
    }

}
