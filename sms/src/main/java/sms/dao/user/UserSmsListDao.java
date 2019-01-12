package sms.dao.user;

import org.apache.ibatis.annotations.Param;
import sms.entity.user.UserSms;
import sms.entity.user.UserSmsList;

import java.util.List;

/**
 * Created by Long on 2019/1/11.
 */
public interface UserSmsListDao {

    public List<UserSmsList> getList(UserSmsList userSmsList);

    int insert(UserSmsList userSmsList);

    Integer getTotal(@Param(value = "pId") Long pId);

}
