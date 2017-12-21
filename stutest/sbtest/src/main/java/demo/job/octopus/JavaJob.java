package demo.job.octopus;

import com.vip.saturn.job.AbstractSaturnJavaJob;
import com.vip.saturn.job.SaturnJobExecutionContext;
import com.vip.saturn.job.SaturnJobReturn;

public class JavaJob extends AbstractSaturnJavaJob {

    @Override
    public SaturnJobReturn handleJavaJob(String jobName, Integer integer, String shardParam,
            SaturnJobExecutionContext saturnJobExecutionContext) throws InterruptedException {
        String stocksStr = "job:" + jobName + "分片：" + shardParam + "执行完成！";
        return new SaturnJobReturn(stocksStr);
    }
}
