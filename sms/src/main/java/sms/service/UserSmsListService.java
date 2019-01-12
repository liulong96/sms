package sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sms.dao.user.UserSmsListDao;
import sms.entity.user.UserSmsList;

import java.util.List;

/**
 * Created by Long on 2019/1/11.
 */
@Service
public class UserSmsListService {


    @Autowired
    private UserSmsListDao userSmsListDao;


    public List<UserSmsList> getList(UserSmsList userSmsList) {
        return userSmsListDao.getList(userSmsList);
    }


    public void insert(UserSmsList userSmsList) {
        userSmsListDao.insert(userSmsList);
    }


    public Integer getTotal(Long pId) {
        return userSmsListDao.getTotal(pId);
    }

}
