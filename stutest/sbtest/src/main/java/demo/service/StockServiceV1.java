package demo.service;

import demo.cache.CacheData;
import demo.exception.BaseException;
import demo.exception.JsonResult;
import demo.exception.RespCode;
import demo.model.Stock;
import demo.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StockServiceV1 {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.application.remoteGetStockNoSyncUrl}")
    private String remoteGetStockNoSyncUrl;

    @Value("${spring.application.remoteModifyStockStateUrl}")
    private String remoteModifyStockStateUrl;

    public List<Stock> getStockNoSync() {
        Map<String, Object> stocks = CacheData.getLocalStockCache();
        Stock stock = null;
        List<Stock> stockList = new ArrayList<Stock>(stocks.size());

        for (Map.Entry entry : stocks.entrySet()) {
            stock = (Stock) entry.getValue();
            if (!stock.isSync()) {
                stockList.add(stock);
            }
        }
        return stockList;
    }

    public Stock modifyStockState(Long pid) {
        Map<String, Object> stocks = CacheData.getLocalStockCache();
        Stock stock = null;

        for (Map.Entry entry : stocks.entrySet()) {
            stock = (Stock) entry.getValue();
            if (!stock.isSync() && stock.getId().equals(pid)) {
                stock.setSync(true);
                logger.info("同步进货数据编号：" + pid + " 成功！时间：" + TimeUtil.ymdHms2str());
            }
        }
        return stock;
    }

    public JsonResult remoteGetStockNoSync() {
        JsonResult jsonResult = null;
        try {
            jsonResult = restTemplate.getForObject(remoteGetStockNoSyncUrl, JsonResult.class);
        } catch (Exception e) {
            throw new BaseException(RespCode.REQUEST_ERROR);
        }
        return jsonResult;
    }

    public JsonResult remoteModifyStockState(Long pid) {
        JsonResult jsonResult = null;
        try {
            jsonResult = restTemplate.getForObject(remoteModifyStockStateUrl + pid, JsonResult.class);
        } catch (Exception e) {
            throw new BaseException(RespCode.REQUEST_ERROR);
        }
        return jsonResult;
    }
}
