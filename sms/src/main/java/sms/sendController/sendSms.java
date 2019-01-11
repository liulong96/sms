package sms.sendController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import sms.send.SmsUtil;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by Long on 2019/1/10.
 * 发送短信
 */
@Controller
@Path(value = "/send")
public class sendSms {

    private Logger logger = LoggerFactory.getLogger(sendSms.class);

    @POST
    @Path("/code")
    @Produces("application/json;charset=utf-8")
    public String send(@FormParam(value = "sign") String sign,//签名
                       @FormParam(value = "model") String model,//0 通知 1营销
                       @FormParam(value = "content") String content,//内容  验证码用code 代替
                       @FormParam(value = "tel") String tels,//手机号 多个, 分割
                       @FormParam(value = "number") String number) throws Exception {//验证码生成的位数
        logger.info("---------->签名:" + sign);
        logger.info("---------->0 通知 1营销:" + model);
        logger.info("---------->内容:" + content);
        logger.info("---------->手机号多个,分割:" + tels);
        logger.info("---------->验证码生成的位数:" + number);
        JSONObject json = new JSONObject();
        try {
            JSONArray jsonArray = new JSONArray();
            String[] tel = tels.split(",");
            for (int i = 0; i < tel.length; i++) {
                JSONObject jsonObject = new JSONObject();
                //验证码
                Integer code = getCode(Integer.parseInt(number));
                String sendContent = content.replaceAll("code", String.valueOf(code));
                Integer sendNotice = SmsUtil.sendNotice(sign, model, sendContent, tel[i]);
                if (sendNotice == -1) {
                    jsonObject.put("status", false);
                } else {
                    jsonObject.put("status", true);
                }
                jsonObject.put("tel", tel[i]);
                jsonObject.put("code", String.valueOf(code));
                jsonArray.add(jsonObject);
            }
            json.put("result", jsonArray);
            json.put("status", true);
            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
            json.put("result", "短信异常");
            json.put("status", false);
            return json.toString();
        }
    }

    //digitNumber 验证码 位数
    public Integer getCode(Integer digitNumber) {
        return (int) ((Math.random() * 9 + 1) * number(digitNumber));
    }

    public Integer number(Integer number) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(1);
        for (int i = 1; i < number; i++) {
            stringBuffer.append(0);
        }
        return Integer.parseInt(stringBuffer.toString());
    }


}
