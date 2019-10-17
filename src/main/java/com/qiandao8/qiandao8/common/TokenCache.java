package com.qiandao8.qiandao8.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author Bert Q
 * ClassName : TokenCache
 * Description 存放Token的缓存
 */
@Component
public class TokenCache {
    /**
     * 缓存初始化容量
     */
    private static int INITIAL_CAPACITY = 100;

    /**
     * 缓存最大容量
     */
    private static int MAXIMUM_SIZE = 500;

    /**
     * 缓存失效时间 单位（秒）
     */
    private static int EXPIRE_DURATION =5;

    /**
     * 使用静态代码块加载配置文件，覆盖原先的默认值
     */
    static {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
        try {
            Properties properties = new Properties();
            properties.load(is);
            INITIAL_CAPACITY = Integer.parseInt(properties.get("TokenCache.INITIAL_CAPACITY").toString());
            MAXIMUM_SIZE = Integer.parseInt(properties.get("TokenCache.MAXIMUM_SIZE").toString());
            EXPIRE_DURATION = Integer.parseInt(properties.get("TokenCache.EXPIRE_DURATION").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Logger logger = LoggerFactory.getLogger(TokenCache.class);

    private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder()
            .initialCapacity(INITIAL_CAPACITY)
            .maximumSize(MAXIMUM_SIZE)
            .expireAfterWrite(EXPIRE_DURATION, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });

    public static void setKey(String key, String value) {
        localCache.put(key, value);
    }

    public static String getValue(String key) {
        String value = null;
        try {
            value = localCache.get(key);
            if ("null".equals(key)) {
                return null;
            }
        } catch (Exception e) {
            logger.error("localCache get Error!");
        }
        return value;
    }

}
