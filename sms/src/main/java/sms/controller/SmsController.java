package sms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sms.dao.user.UserDao;
import sms.entity.user.UserSms;
import sms.entity.user.UserSmsList;
import sms.sendController.sendSms;
import sms.service.UserSmsListService;
import sms.service.UserSmsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Long on 2019/1/10.
 */
@Controller
@RequestMapping(value = "/sendIndex")
public class SmsController {

    private Logger logger = LoggerFactory.getLogger(SmsController.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/msgSku", method = RequestMethod.GET)
    public String msgSku() {
        return "msgSku";
    }

    @Autowired
    private UserSmsService userSmsService;

    @Autowired
    private UserSmsListService userSmsListService;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public Integer getList() {
        return userDao.getTotal();
    }


    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> send(String sign,//签名
                                    String model,//0 通知 1营销
                                    String content,//内容  验证码用code 代替
                                    String tels,//手机号 多个, 分割
                                    String appkey,//appkey
                                    String number) {
        Map<String, Object> map = new HashMap<>();
        try {
            sendSms send = new sendSms();
            String s = send.send(appkey, sign, model, content, tels, number);
            map.put("status", true);
            map.put("result", s);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", false);
            map.put("result", "");
        }
        return map;
    }

    @RequestMapping(value = "/getMsg", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getMsg(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "limit", defaultValue = "10") int limit) {
        Map<String, Object> map = new HashMap<>();
        try {
            Integer total = userSmsService.getTotal();
            UserSms userSms = new UserSms();
            userSms.setStartRow((page - 1) * 10);
            userSms.setRow(limit);
            List<UserSms> list = userSmsService.getList(userSms);
            map.put("code", 0);
            map.put("count", total);
            map.put("data", list);
            map.put("msg", "");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return map;
        }
    }


    @RequestMapping(value = "/getMsgSku", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getMsgSku(@RequestParam(value = "pId", required = false) Long pId) {
        Map<String, Object> map = new HashMap<>();
        try {
            UserSmsList userSmsList = new UserSmsList();
            userSmsList.setpId(pId);
            List<UserSmsList> list = userSmsListService.getList(userSmsList);
            map.put("code", 0);
            map.put("count", list.size());
            map.put("data", list);
            map.put("msg", "");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return map;
        }
    }


}
