package sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sms.sendController.sendSms;

import javax.ws.rs.FormParam;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Long on 2019/1/10.
 */
@Controller
@RequestMapping(value = "/sendIndex")
public class SmsController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/send",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> send( String sign,//签名
                                     String model,//0 通知 1营销
                                    String content,//内容  验证码用code 代替
                                    String tels,//手机号 多个, 分割
                                     String number) {
        Map<String, Object> map = new HashMap<>();
        try {
            sendSms send = new sendSms();
            String s = send.send(sign, model, content, tels, number);
            map.put("status", true);
            map.put("result", s);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", false);
            map.put("result", "");
        }
        return map;
    }

}
