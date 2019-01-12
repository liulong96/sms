package sms.until;


import java.util.UUID;


/**
 *
 */
public class ID {


    public static String getGuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    //取纳秒
    public static Long getNonaSec(){
        //return System.nanoTime();
        return IdWorker.nextId();
    }
}
