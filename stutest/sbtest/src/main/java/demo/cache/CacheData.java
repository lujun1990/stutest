package demo.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheData {

    private static Map<String,Object> localStockCache = new ConcurrentHashMap<String,Object>();

    public static Map<String,Object> getLocalStockCache(){
        return localStockCache;
    }
}
