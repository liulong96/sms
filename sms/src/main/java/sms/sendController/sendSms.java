package sms.sendController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sms.dao.user.UserDao;
import sms.entity.user.UserSms;
import sms.entity.user.UserSmsList;
import sms.send.SmsUtil;
import sms.service.UserSmsListService;
import sms.service.UserSmsService;
import sms.until.ID;
import sms.until.SpringContextUtil;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Long on 2019/1/10.
 * 发送短信
 */
@Controller
@Path(value = "/send")
public class sendSms {

    private Logger logger = LoggerFactory.getLogger(sendSms.class);

    private UserSmsService userSmsService = SpringContextUtil.getBean(UserSmsService.class);

    private UserSmsListService userSmsListService = SpringContextUtil.getBean(UserSmsListService.class);


    @POST
    @Path("/code")
    @Produces("application/json;charset=utf-8")
    public String send(@FormParam(value = "appkey") String appkey,
                       @FormParam(value = "sign") String sign,//签名
                       @FormParam(value = "model") String model,//0 通知 1营销
                       @FormParam(value = "content") String content,//内容  验证码用code 代替
                       @FormParam(value = "tel") String tels,//手机号 多个, 分割
                       @FormParam(value = "number") String number) throws Exception {//验证码生成的位数
        logger.info("---------->签名:" + sign);
        logger.info("---------->0 通知 1营销:" + model);
        logger.info("---------->内容:" + content);
        logger.info("---------->手机号多个,分割:" + tels);
        logger.info("---------->验证码生成的位数:" + number);
        //判断key
        Map<String, Object> map = checkKey(appkey);
        Boolean flag = (Boolean) map.get("flag");
        if (flag) {
            return "appkey无权限";
        }
        //判断总数
        String totalSum = (String) map.get("total");
        Integer totalSku = userSmsListService.getTotal((Long) map.get("id"));
        if (totalSku >= Integer.parseInt(totalSum)) {
            return "【" + map.get("name") + "】发送余额不足请充值";
        }
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
                //Integer sendNotice = 1;
                if (sendNotice == -1) {
                    jsonObject.put("status", false);
                } else {
                    jsonObject.put("status", true);
                    //记录发送信息
                    recordSed(map, tel[i], sendContent);
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

    private void recordSed(Map<String, Object> map, String tel, String content) {
        Long id = (Long) map.get("id");
        UserSmsList userSmsList = new UserSmsList();
        userSmsList.setId(ID.getNonaSec());
        userSmsList.setpId(id);
        userSmsList.setTel(tel);
        userSmsList.setContent(content);
        userSmsListService.insert(userSmsList);
    }

    private Map<String, Object> checkKey(String appkey) {
        Map<String, Object> map = new HashMap<>();
        map.put("flag", true);
        UserSms userSms = new UserSms();
        userSms.setStatus(0);//启用
        userSms.setAppKey(appkey);
        List<UserSms> list = userSmsService.getList(userSms);
        if (list.size() > 0) {
            map.put("flag", false);
            map.put("id", list.get(0).getId());
            map.put("total", list.get(0).getTotal());
            map.put("name", list.get(0).getName());
        }
        return map;
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
