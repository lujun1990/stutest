package demo.job;

import demo.cache.CacheData;
import demo.exception.JsonResult;
import demo.model.Stock;
import demo.service.StockServiceV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

public class JdkSyncStockJob implements Runnable{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private StockServiceV1 stockServiceV1;

    public JdkSyncStockJob(StockServiceV1 stockServiceV1){
        this.stockServiceV1 = stockServiceV1;
    }

    @Override
    public void run() {
        logger.info("开始获取数据...");
        JsonResult jsonResult = stockServiceV1.remoteGetStockNoSync();
        Map<String, Object> stocks = CacheData.getLocalStockCache();

        logger.info("jsonResult: "+jsonResult);
        logger.info("开始同步数据...");
        if(!StringUtils.isEmpty(jsonResult) && jsonResult.getResultData()!=null){
            List<Stock> stockList = (List<Stock>)jsonResult.getResultData();
            Stock stock = null;
            for(Stock s : stockList){
                for(Map.Entry entry :stocks.entrySet()){
                    stock = (Stock)entry.getValue();
                    if(s == stock){
                        //stock.setSync(true);
                        stockServiceV1.remoteModifyStockState(stock.getId());
                    }
                }
            }
        }
    }

}
