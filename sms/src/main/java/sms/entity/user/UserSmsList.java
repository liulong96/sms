package sms.entity.user;

import org.apache.ibatis.type.Alias;

/**
 * Created by Long on 2019/1/11.
 */
@Alias("UserSmsList")
public class UserSmsList {

    private Long id;
    private Long pId;
    private String tel;
    private String createDate;
    private String content;//发送内容

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
