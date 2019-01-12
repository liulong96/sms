package sms.dao.user;

import sms.entity.user.UserSms;

import java.util.List;

/**
 * Created by Long on 2019/1/11.
 */
public interface UserSmsDao {

    public List<UserSms> getList(UserSms userSms);

    Integer getTotal();

}
