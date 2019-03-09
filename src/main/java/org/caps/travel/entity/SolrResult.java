package org.caps.travel.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class SolrResult implements Serializable {

    private String tb_userid;

    private String tb_username;

    private String tb_password;

    private String tb_name;

    private String tb_email;

    private String tb_telephone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tb_birthday;

    private String tb_sex;

    private Integer tb_state;

    private String tb_code;

    /**
     * @return userid
     */
    public String getUserid() {
        return tb_userid;
    }

    /**
     * @param userid
     */
    public void setUserid(String userid) {
        this.tb_userid = userid;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return tb_username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.tb_username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return tb_password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.tb_password = password;
    }

    /**
     * @return name
     */
    public String getName() {
        return tb_name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.tb_name = name;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return tb_email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.tb_email = email;
    }

    /**
     * @return telephone
     */
    public String getTelephone() {
        return tb_telephone;
    }

    /**
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.tb_telephone = telephone;
    }

    /**
     * @return birthday
     */
    public Date getBirthday() {
        return tb_birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.tb_birthday = birthday;
    }

    /**
     * @return sex
     */
    public String getSex() {
        return tb_sex;
    }

    /**
     * @param sex
     */
    public void setSex(String sex) {
        this.tb_sex = sex;
    }

    /**
     * @return state
     */
    public Integer getState() {
        return tb_state;
    }

    /**
     * @param state
     */
    public void setState(Integer state) {
        this.tb_state = state;
    }

    /**
     * @return code
     */
    public String getCode() {
        return tb_code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.tb_code = code;
    }
}