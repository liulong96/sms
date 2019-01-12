package sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sms.dao.user.UserSmsDao;
import sms.entity.user.UserSms;

import java.util.List;

/**
 * Created by Long on 2019/1/11.
 */
@Service
public class UserSmsService {


    @Autowired
    private UserSmsDao userSmsDao;

    public List<UserSms> getList(UserSms userSms){
        return  userSmsDao.getList(userSms);
    }

}
