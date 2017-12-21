package demo.job;

import demo.Application;
import demo.cache.CacheData;
import demo.model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JdkJob implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String batchNum = null;

    private Map<String, Object> localStockCache = CacheData.getLocalStockCache();

    @Override
    public void run() {
        logger.info("开始生成数据...");
        batchNum = UUID.randomUUID().toString();
        logger.info("生成新的批次号：" + batchNum);
        for (int i = 0; i < 10; i++) {
            localStockCache.put("stock-[" + batchNum + "]-" + i, new Stock(Long.valueOf(i), 100L, "admin", new Date(), null, false));
        }
        logger.info("批次号：" + batchNum + " 生成数据成功！当前数据总量为：" + localStockCache.size() + "完成时间：" + demo.util.TimeUtil.ymdHms2str() + "\n");
    }
}
