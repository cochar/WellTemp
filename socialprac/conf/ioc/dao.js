var ioc = {
        dataSource : {
            type : "com.alibaba.druid.pool.DruidDataSource",
            events : {
                create : "init",
                depose : 'close'
            },
            fields : {
                url : "jdbc:mysql://127.0.0.1:3306/socialp",
                username : "root",
                password : "root",
                //validationQuery : "select 1" , // Oracle的话需要改成 select 1 from dual
                maxActive : 100
            }
        },
        dao : {
            type : "org.nutz.dao.impl.NutDao",
            args : [{refer:"dataSource"}]
        },
        // 直接初始化Ehcache,默认找ehcache.xml文件哦
        cacheManager : {
            type : "net.sf.ehcache.CacheManager",
            factory : "net.sf.ehcache.CacheManager#create"
        }
        /* // 与shiro共享一个ehcache示例的方式
        cacheManager : {
            type : "net.sf.ehcache.CacheManager",
            factory : "net.sf.ehcache.CacheManager#getCacheManager",
            args : ["nutzbook"] // 对应shiro.ini中指定的ehcache.xml中定义的name
        }
         */
};
