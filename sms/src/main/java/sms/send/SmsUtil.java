package sms.send;

/**
 * Created by Yan on 2017/12/15.
 */
public class SmsUtil {

    private static Client client = null;

    public static Integer sendNotice(String sign, String model, String content, String tel) {
        int i = -1;
        try {
//            //签名
//            if (sign == null || sign.equals("")) {
//                sign = "【掌买科技】";
//            }else {
//                sign = "【"+sign+"】";
//            }
            StringBuffer string =new StringBuffer();
            string.append("【");
            string.append(sign);
            string.append("】");
            if(null!=content && content!=""){
                boolean rflag=content.contains("【");
                if(rflag){
                    content = content.replace("【","");
                }
                boolean lflag=content.contains("】");
                if(lflag){
                    content =  content.replace("】","");
                }
            }
            //手机号
            String[] telArr = tel.split(",");
            //0 通知 1营销
            if (model.equals("1")) {
                //内容
                content = string + content + "退订回N";
                client = new Client("7SDK-LHW-0588-RBRPR", "794323");
            } else {
                //内容
                content = string + content;
                client = new Client("7SDK-LHW-0588-RBRPP", "577181");
            }
            System.out.println("发送内容为：" + content);
            i = client.sendSMS(telArr, content, "", 5);// 带扩展码
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public static void main(String[] args) {
        int i = sendNotice(null, "0", "验证码：666666，您正在进行申请登录。", "15316021669");
        System.out.println(i);
    }
}
