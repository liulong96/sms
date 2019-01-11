package sms.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Long on 2019/1/11.
 */
@Component
public class testTask {

    @Scheduled(cron = "0/5 * * * * ? ") // 间隔5秒执行
    public void taskCycle() {
        System.out.println("使用SpringMVC框架配置定时任务");
    }


}