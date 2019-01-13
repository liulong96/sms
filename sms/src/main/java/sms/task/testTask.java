package sms.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sms.controller.SmsController;
import sms.send.Client;

import java.rmi.RemoteException;

/**
 * Created by Long on 2019/1/11.
 */
@Component
public class testTask {

    private Logger logger = LoggerFactory.getLogger(testTask.class);

//    @Scheduled(cron = "0/10 * * * * ? ") // 间隔5秒执行
//    public void taskCycle() throws RemoteException {
//        System.out.println("使用SpringMVC框架配置定时任务");
//        Client client = new Client("7SDK-LHW-0588-QFYLS", "ljct2016");
//        double balance = client.getBalance();
//        logger.info("-------------->" + balance);
//        System.out.println(balance);
//    }


}
