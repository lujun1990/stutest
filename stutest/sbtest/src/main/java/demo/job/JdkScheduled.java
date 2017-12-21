package demo.job;

import demo.service.StockServiceV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JdkScheduled {

    private ScheduledExecutorService scheduExec = null;
    private ScheduledExecutorService scheduExec2 = null;

    @Autowired
    private static StockServiceV1 stockServiceV1;

    public JdkScheduled() {
        //final int threadNum = Runtime.getRuntime().availableProcessors() * 4;
        scheduExec = Executors.newScheduledThreadPool(2);
        scheduExec2 = Executors.newScheduledThreadPool(1);
    }

    public void JdkScheduledStart() {
        //生成数据
        JdkJob jdkJob = new JdkJob();
        scheduExec.scheduleAtFixedRate(jdkJob, 5, 5, TimeUnit.SECONDS);
    }

    public void JdkScheduledSyncStart(){
        //获取位同步的数据，并同步数据
        JdkSyncStockJob jdkSyncStockJob = new JdkSyncStockJob(stockServiceV1);
        scheduExec2.scheduleAtFixedRate(jdkSyncStockJob, 10, 10, TimeUnit.SECONDS);
    }

}
