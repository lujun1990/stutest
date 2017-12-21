package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.job.JdkScheduled;

/**
 * 系统启动时初始化数据
 */
public class InitData {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void initRun(){
        logger.info("init data start...");
        JdkScheduled jdkScheduled = new JdkScheduled();
        jdkScheduled.JdkScheduledStart();
        jdkScheduled.JdkScheduledSyncStart();
    }
}
