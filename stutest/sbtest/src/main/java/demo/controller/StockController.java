package demo.controller;

import demo.exception.JsonResult;
import demo.exception.RespCode;
import demo.model.Stock;
import demo.service.StockServiceV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/v1/stock/")
public class StockController {

    @Autowired
    private StockServiceV1 stockServiceV1;

    @RequestMapping("/getStockNoSync")
    public JsonResult getStockNoSync(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        try {
            List<Stock> stockList = stockServiceV1.getStockNoSync();
            jsonResult.setRespCode(RespCode.SUCCESS);
            jsonResult.setResultData(stockList);
        } catch (Exception e) {
            jsonResult.setRespCode(RespCode.REQUEST_ERROR);
        }
        return jsonResult;
    }

    @RequestMapping("/modifyStockState/{pid}")
    public JsonResult modifyStockState(HttpServletRequest request, @PathVariable String pid) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        try {
            Stock stock = stockServiceV1.modifyStockState(Long.valueOf(pid));
            if (stock != null && stock.isSync()) {
                jsonResult.setRespCode(RespCode.SUCCESS);
                jsonResult.setResultData(stock);
            } else {
                jsonResult.setRespCode(RespCode.REQUEST_ERROR);
            }

        } catch (Exception e) {
            jsonResult.setRespCode(RespCode.REQUEST_ERROR);
        }
        return jsonResult;
    }

    @RequestMapping("/remoteGetStockNoSync")
    public JsonResult remoteGetStockNoSync() {
        return stockServiceV1.remoteGetStockNoSync();
    }

    @RequestMapping("/remoteModifyStockState/{pid}")
    public JsonResult remoteModifyStockState(@PathVariable String pid) {
        return stockServiceV1.remoteModifyStockState(Long.valueOf(pid));
    }
}
