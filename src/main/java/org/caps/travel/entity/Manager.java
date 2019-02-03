package org.caps.travel.entity;

import javax.persistence.*;

@Table(name = "manager")
public class Manager {
    @Id
    private String managerid;

    private String name;

    private String password;

    private Integer flag;

    /**
     * @return managerid
     */
    public String getManagerid() {
        return managerid;
    }

    /**
     * @param managerid
     */
    public void setManagerid(String managerid) {
        this.managerid = managerid;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return flag
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * @param flag
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}